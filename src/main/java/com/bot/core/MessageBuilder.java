package com.bot.core;


import com.bot.actions.Action;
import com.bot.data.NamesJoukes;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

/**
 * Created by Victor on 21.11.2017.
 */
public class  MessageBuilder {

    private InformationAnalizator informationAnalizator;

    public MessageBuilder() {
        this.informationAnalizator = new InformationAnalizator();
    }

    public SendMessage buildMessage(Message incomingInfo, boolean type){

        String textOfMessage; // Text  what will be sended to user

        SendMessage messageToUser = new SendMessage();// Initialize message

        messageToUser.setChatId(incomingInfo.getChatId());

        User user = incomingInfo.getFrom();
        System.out.println(user.getFirstName());// User initialization

        if(type) {
            textOfMessage = informationAnalizator.analizeQuery(incomingInfo.getText()).action();
        }else {
            textOfMessage = informationAnalizator.analizeUsersText(incomingInfo.getText());
        }
        textOfMessage = "Привет " + getRelatedToNameText(user.getFirstName().toUpperCase())+" \n "+ textOfMessage;
        messageToUser.setText(textOfMessage);//Initialization message with text


        return messageToUser;

    }



    private String getRelatedToNameText(String name){

        for (String n: NamesJoukes.getNamesJouks().keySet()) {
            if(name.equalsIgnoreCase(n)){
                return NamesJoukes.getNamesJouks().get(n).get((int)(Math.random()* NamesJoukes.getNamesJouks().get(n).size()-1));
            }
        }

        NamesJoukes.addName(name);
        NamesJoukes.addJoukToName(name,"Новенький писюн");
        return "Твоего имени нет, в моей базе данных шуток, я добавлю твое имя, смерд)";
    }










}
