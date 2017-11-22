package com.bot.core;

import com.bot.data.FileSaver;
import com.bot.data.JedisConnector;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




import java.util.List;

/**
 * Created by Victor on 21.11.2017.
 */
public class MemchikBot extends TelegramLongPollingBot {


    private MessageBuilder messageBuilder;
    private QuerryProcessor querryProcessor;
    private JedisConnector jedisConnector;

    public MemchikBot(JedisConnector jedisConnector) {
        this.querryProcessor = new QuerryProcessor();
        this.jedisConnector = jedisConnector;
        this.messageBuilder = new MessageBuilder(jedisConnector);
    }



    public void onUpdateReceived(Update update) {

        User user = update.getMessage().getFrom();

        if(!jedisConnector.getJedis().exists(String.valueOf(user.getId()))){
            jedisConnector.addUserToDB(String.valueOf(user.getId()),user.getFirstName());
            jedisConnector.addPseudoToUser(String.valueOf(user.getId())+user.getFirstName(),"Свежее мясо");
        }

        if(update.getMessage().hasPhoto()){
            List<PhotoSize> photos = update.getMessage().getPhoto();
            String f_id = photos.get(0).getFileId();
            FileSaver.saveMeMId(f_id);

        }else if(!update.getMessage().getText().contains("/")){
            SendMessage  message = messageBuilder.buildMessage(update.getMessage(),false); // Create a message  object
            sendTextMessage(message);
            loging(update.getMessage(),message.getText());
        }
        else if(update.getMessage().getText().equals("/givememem")){
            SendPhoto mem =querryProcessor.QuerryReply(update.getMessage());
            sendMem(mem);
            loging(update.getMessage(), mem.toString());
        }else {
            SendMessage message = messageBuilder.buildMessage(update.getMessage(), true);
            sendTextMessage(message);
            loging(update.getMessage(),message.getText());
        }
        user = null;

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

    private void loging(Message message, String bot_answer){
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + message.getFrom().getFirstName() + " " + message.getFrom().getLastName()
                + ". (id = " + message.getFrom().getId() + ") \n Text - " +message.getText());
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }



    public String getBotUsername() {
        return "lviv_mem_bot";
    }

    @Override
    public String getBotToken() {
        return "474677975:AAGOk5Z78As45zFuThvtF7AVwdWaI-Dju5M";
    }
}
