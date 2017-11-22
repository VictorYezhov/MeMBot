package com.bot.core;


import com.bot.actions.Action;
import com.bot.data.JedisConnector;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

/**
 * Created by Victor on 21.11.2017.
 */
public class  MessageBuilder {

    private InformationAnalizator informationAnalizator;
    private JedisConnector jedisConnector;

    public MessageBuilder(JedisConnector jedisConnector) {
        this.informationAnalizator = new InformationAnalizator();
        this.jedisConnector = jedisConnector;
    }

    public SendMessage buildMessage(Message incomingInfo, boolean type){

        String textOfMessage; // Text  what will be sended to user

        SendMessage messageToUser = new SendMessage();// Initialize message

        messageToUser.setChatId(incomingInfo.getChatId());

        User user = incomingInfo.getFrom();
        System.out.println(user.getFirstName());// User initialization

        if(type) {
            textOfMessage = informationAnalizator.analizeQuery(incomingInfo.getText()).action(incomingInfo);
        }else {
            textOfMessage = informationAnalizator.analizeUsersText(incomingInfo.getText());
        }
        textOfMessage = "Привет " + jedisConnector.getUserPseudo(String.valueOf(user.getId())+user.getFirstName())+" \n "+ textOfMessage;
        messageToUser.setText(textOfMessage);//Initialization message with text


        return messageToUser;

    }











}
