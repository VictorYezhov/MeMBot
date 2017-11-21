package com.bot.core;

import com.bot.actions.Action;
import com.bot.actions.FailureAction;
import com.bot.data.Quarries;

/**
 * Created by Victor on 21.11.2017.
 */
public class InformationAnalizator {

    public String analizeUsersText(String  incomingText){

        String answerToUser;
        answerToUser = analizeContent(incomingText);
        return answerToUser;
    }


    public Action analizeQuery(String query){
        for (Quarries q: Quarries.values()) {
            if(query.equals(q.getExplanations())){
               return q.getAction();
            }
        }
        return new FailureAction();
    }




    private String analizeContent(String text){
        return "Analize user text";
    }





}
