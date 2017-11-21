package com.bot.core;

import com.bot.data.FileSaver;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Victor on 21.11.2017.
 */
public class MemchikBot extends TelegramLongPollingBot {


    private MessageBuilder messageBuilder;
    private QuerryProcessor querryProcessor;

    public MemchikBot() {
        this.messageBuilder = new MessageBuilder();
        this.querryProcessor = new QuerryProcessor();
    }



    public void onUpdateReceived(Update update) {

        if(update.getMessage().hasPhoto()){
            List<PhotoSize> photos = update.getMessage().getPhoto();
            String f_id = photos.get(0).getFileId();
            FileSaver.saveMeMId(f_id);

        }else if(!update.getMessage().getText().contains("/")){
            SendMessage  message = messageBuilder.buildMessage(update.getMessage(),false); // Create a message  object
            sendTextMessage(message);
        }
        else if(update.getMessage().getText().equals("/givememem")){
            sendMem(querryProcessor.QuerryReply(update.getMessage()));
        }else {
            SendMessage message = messageBuilder.buildMessage(update.getMessage(), true);
            sendTextMessage(message);
        }

    }

    public boolean sendTextMessage(SendMessage message){
        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Something went wrong with sending message");
            return false;
        }
        return true;
    }

    public boolean sendMem(SendPhoto sendPhoto){
        try {
            sendPhoto(sendPhoto); // Call method to send the photo with caption
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }



    public String getBotUsername() {
        return "lviv_mem_bot";
    }

    @Override
    public String getBotToken() {
        return "474677975:AAGOk5Z78As45zFuThvtF7AVwdWaI-Dju5M";
    }
}
