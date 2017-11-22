package com.bot.actions;

import com.bot.data.JedisConnector;
import org.telegram.telegrambots.api.objects.Message;

import java.util.List;

/**
 * Created by Victor on 21.11.2017.
 */
public class AddPseudoToOtherAction implements Action {
    public String action(Message message) {

        String[] messageParts = message.getText().split(" ");

        StringBuffer pseud= new StringBuffer("");
        if(messageParts.length > 3){
            for (int i=2; i<messageParts.length; i++){
                pseud.append(messageParts[i]);
                pseud.append(" ");
            }
            List<String> ids = JedisConnector.getAllIds();

            for(int i = 0; i<ids.size();i++){
                if(JedisConnector.getUserNamefromBd(ids.get(i)).equalsIgnoreCase(messageParts[1])) {
                    JedisConnector.addPseudoToUser(ids.get(i)+JedisConnector.getUserNamefromBd(ids.get(i)),pseud.toString());
                }
            }


            //NamesJoukes.addJoukToName(messageParts[1].toUpperCase(),pseud.toString());
            //System.out.println(message.getFrom().getFirstName().toUpperCase()+" added " + messageParts[1]);

            return message.getFrom().getFirstName()+" ты добавил для "+ messageParts[1]+" псевдоним "+ pseud.toString();

        }else if(messageParts.length!=3){
            return "Укажи блять правильно кому и какой псевдоним";
        }


        if(false){
            return "Такого имени нет, в базе данных";
        }




        List<String> ids = JedisConnector.getAllIds();

        for(int i = 0; i<ids.size();i++){
            if(JedisConnector.getUserNamefromBd(ids.get(i)).equalsIgnoreCase(messageParts[1])) {
                JedisConnector.addPseudoToUser(ids.get(i)+JedisConnector.getUserNamefromBd(ids.get(i)),messageParts[2]);
            }
        }
       // System.out.println(message.getFrom().getFirstName().toUpperCase()+" added " + messageParts[1]);


        return message.getFrom().getFirstName()+" ты добавил для "+ messageParts[1]+" псевдоним "+ messageParts[2];
    }
}
