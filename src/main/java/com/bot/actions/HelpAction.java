package com.bot.actions;

import org.telegram.telegrambots.api.objects.Message;

/**
 * Created by Victor on 21.11.2017.
 */
public class HelpAction implements Action {
    public String action(Message message) {
        return "/help - Получить помощь по использованию\n"
         + "Что бы добавить мем в мою базу, просто киньте мне его \n" +
                "/givememem - Присылает вам рандомный мем из моей базы\n" +
                "/addPseudoToMe 'псевдоним' - Добавляет псевдоним, который бот использует для общения с вами (их много " +
                "и выбираются они рандомно) ПИСАТЬ БЕЗ КОВЫЧЕК\n" +
                "/addPseudoToOther 'имя друга' 'пседвоним' - Назвите вашего друга смешным псевдонимом что бы он охуел)))00)\n" +
                "/showAllPseudos - Показать пользвателей и их превдонимы ";
    }
}
