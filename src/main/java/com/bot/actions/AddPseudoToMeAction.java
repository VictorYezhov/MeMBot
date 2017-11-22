package com.bot.actions;

import com.bot.data.JedisConnector;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Victor on 21.11.2017.
 */
public class AddPseudoToMeAction implements Action {
    public String action(Message message) {

        String[] messageParts = message.getText().split(" ");
        StringBuffer pseud = new StringBuffer("");
        for (int i = 1; i < messageParts.length; i++) {
            pseud.append(messageParts[i]);
            pseud.append(" ");
        }

        JedisConnector.addPseudoToUser(message.getFrom().getId() + message.getFrom().getFirstName(), pseud.toString());
        return message.getFrom().getFirstName().toUpperCase() + " ты добавил себе псевдоним " + pseud.toString();


    }
}
