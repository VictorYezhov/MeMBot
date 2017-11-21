package com.bot.actions;

import com.bot.data.NamesJoukes;

/**
 * Created by Victor on 21.11.2017.
 */
public class SeeAllPseudosAction implements Action {

    public String action() {
        return NamesJoukes.print();
    }


}
