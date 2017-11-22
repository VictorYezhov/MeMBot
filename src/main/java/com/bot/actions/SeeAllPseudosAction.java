package com.bot.actions;

import com.bot.data.JedisConnector;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Victor on 21.11.2017.
 */
public class SeeAllPseudosAction implements Action {

    public String action(Message message) {
        return JedisConnector.print();
    }


}
