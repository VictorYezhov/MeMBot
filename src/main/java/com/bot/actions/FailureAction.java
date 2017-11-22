package com.bot.actions;

import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Victor on 21.11.2017.
 */
public class FailureAction implements Action {
    public String action(Message message) {
        return "Такого запроса я не умею обрабатывать";
    }
}
