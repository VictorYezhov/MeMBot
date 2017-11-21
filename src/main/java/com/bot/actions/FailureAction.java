package com.bot.actions;

/**
 * Created by Victor on 21.11.2017.
 */
public class FailureAction implements Action {
    public String action() {
        return "Такого запроса я не умею обрабатывать";
    }
}
