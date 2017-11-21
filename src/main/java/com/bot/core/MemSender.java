package com.bot.core;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Victor on 21.11.2017.
 */
public class MemSender {

    public static SendPhoto createMemToSend(Message message, String f_id){

        SendPhoto msg = new SendPhoto()
                .setChatId(message.getChatId())
                .setPhoto(f_id);
        return msg;
    }


}