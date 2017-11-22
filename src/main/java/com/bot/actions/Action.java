package com.bot.actions;

import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Victor on 21.11.2017.
 */
public interface Action {

    String action(Message message);
}
