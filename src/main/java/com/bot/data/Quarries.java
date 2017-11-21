package com.bot.data;
/**
 * Created by Victor on 21.11.2017.
 */
import com.bot.actions.*;

/**
 * Created by Victor on 21.11.2017.
 */
public enum Quarries {

    HELP("/help",  new HelpAction()),
    GIVEMEMEM("/givememem", new GiveMeMeMAction()),
    ADDPSEUDOTOME("/addPseudoToMe", new AddPseudoToMeAction()),
    ADDPSEUDOTOOTHER("/addPseudoToOther", new AddPseudoToOtherAction()),
    SEEALLPSEUDOS("/showAllPseudos", new SeeAllPseudosAction()),
    START("/start", new HelpAction());

    private String explanations;
    private Action action;

    Quarries(String explanations, Action action) {
        this.explanations = explanations;
        this.action =action;
    }


    public String getExplanations() {
        return explanations;
    }

    public void setExplanations(String explanations) {
        this.explanations = explanations;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}

