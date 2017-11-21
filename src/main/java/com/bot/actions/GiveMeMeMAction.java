package com.bot.actions;

import com.bot.core.MemchikBot;
import com.bot.data.FileSaver;

/**
 * Created by Victor on 21.11.2017.
 */
public class GiveMeMeMAction implements Action {

    public String action() {

        return FileSaver.getRandomId();
    }
}
