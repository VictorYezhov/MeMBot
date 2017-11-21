package com.bot.core;

import com.bot.actions.Action;
import com.bot.actions.GiveMeMeMAction;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Victor on 21.11.2017.
 */
public class QuerryProcessor {

    private InformationAnalizator informationAnalizator;

    public QuerryProcessor() {
        this.informationAnalizator = new InformationAnalizator();
    }

    public SendPhoto QuerryReply(Message incomingInfo){

        if(informationAnalizator.analizeQuery(incomingInfo.getText()) instanceof GiveMeMeMAction){
            Action action = informationAnalizator.analizeQuery(incomingInfo.getText());
            return MemSender.createMemToSend(incomingInfo, action.action());
        }else {
            informationAnalizator.analizeQuery(incomingInfo.getText());
        }



        return null;

    }




}
