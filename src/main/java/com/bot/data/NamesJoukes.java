package com.bot.data;

import java.util.*;

/**
 * Created by Victor on 21.11.2017.
 */
public class NamesJoukes {

    private static Map<String, List<String>>  namesJouks;

    static  {
        namesJouks = new HashMap<String, List<String>>();
        initializeByDefault();
    }

    public static Map<String, List<String>> getNamesJouks() {
        return namesJouks;
    }

    public static void setNamesJouks(Map<String, List<String>> names) {
        namesJouks = names;
    }

    public static  void addName(String name){
        namesJouks.put(name.toUpperCase(), new ArrayList<String>());
    }

    public static void addJoukToName(String name,String jouke){
        List<String> joukes = namesJouks.get(name);
        joukes = new ArrayList<String>(joukes);
        joukes.add(jouke);
        namesJouks.put(name,joukes);
    }

    private static void initializeByDefault(){
        for (Names n: Names.values()) {
            namesJouks.put(n.name(), Arrays.asList(n.getTextFor()));
        }
    }


    public  static String print() {
        String result="";
        String joukes="";
        for(String person : namesJouks.keySet()){

            result =result +" "+person;
            for (String j : namesJouks.get(person)){
                joukes= joukes+" "+ j;
            }
            result = result +"\n"+joukes;
            joukes = " ";
            result+="\n";

        }
        return result;
    }
}
