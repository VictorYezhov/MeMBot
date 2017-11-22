package com.bot.actions;

import com.bot.core.MemchikBot;
import com.bot.data.FileSaver;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Victor on 21.11.2017.
 */
public class GiveMeMeMAction implements Action {

    public String action(Message message) {

        return FileSaver.getRandomId();
    }
}
