package com.bot.core;

import com.bot.data.JedisConnector;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * Created by Victor on 21.11.2017.
 */


public class Main {

    public static void main(String[] args){

        // Initialize Api Context
        ApiContextInitializer.init();
        JedisConnector jedisConnector = new JedisConnector();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        //Register our bot
        try {
            botsApi.registerBot(new MemchikBot(jedisConnector));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }



    }


}
