package com.bot.data;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor on 22.11.2017.
 */
public class JedisConnector {

    private static Jedis jedis;
    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
    private static  int userCount;

    public JedisConnector() {
        jedis = new Jedis(redisHost,redisPort);
        if(jedis.get("usercount")==null){
            userCount=0;
        }else {
            userCount = Integer.parseInt(jedis.get("usercount"));
        }
    }


    public static Jedis getJedis() {
        return jedis;
    }

    public static void setJedis(Jedis jedis) {
        jedis = jedis;
    }

    public static void addUserToDB(String userId, String userName){
        if (jedis.exists(userId))
            return;
        jedis.set(userId,userName);
        jedis.set("usercount",String.valueOf(++userCount));
        addUserIdToBd(userId);
    }
    public static String  getUserNamefromBd(String user_id){
        return jedis.get(user_id);
    }

    public static void addPseudoToUser(String userID_userName, String pseudo){
        jedis.lpush(userID_userName, pseudo);
    }


    public static String getUserPseudo(String userID_userName){
        List<String> pseudos = jedis.lrange(userID_userName,0,100);
        int index = (int)(Math.random()*pseudos.size());
        if(index==pseudos.size()){
            index = index - 1;
        }
        return pseudos.get(index);
    }


    public static List<String> getAllUserPseudos(String userID_userName){
        return jedis.lrange(userID_userName,0,100);
    }


    public static String print(){

        StringBuilder result = new StringBuilder();
        List<String> ids = getAllIds();
        List<String> userNames = new ArrayList<String>();

        for(int i = 0; i<ids.size();i++){
            userNames.add(getUserNamefromBd(ids.get(i)));
        }
        List<String> allUserPseudos;
        for(int i = 0; i< ids.size(); i++){
            result.append("\n");
            result.append(userNames.get(i));
            result.append("\n");
            allUserPseudos = getAllUserPseudos(ids.get(i)+userNames.get(i));
            //System.err.println(allUserPseudos);
            for(int j = 0; j<allUserPseudos.size(); j++){
                result.append(allUserPseudos.get(j));
                result.append("\n");
            }
            result.append("----------------------");
        }

        return result.toString();


    }



    private static void addUserIdToBd(String id){
        jedis.lpush("ids",id);
    }

    public static List<String> getAllIds(){
        return jedis.lrange("ids",0,1000);
    }



}
