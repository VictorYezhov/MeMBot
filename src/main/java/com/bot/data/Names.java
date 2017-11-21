package com.bot.data;

/**
 * Created by Victor on 21.11.2017.
 */
public enum Names {

    SASHA(" ака 47 ака ГУФ акакий "), TOLIK("Еболик"), SOFIA("Розбещена монашка️"),
    MISHA("Чех - питух"), VICTOR("Верховый лидер"), MARIIA("Машка - кума"),
    VERONIKA("Мисс пунктуальность"), TANIYA("Парламентер"), NIKITA(" человек - АНЕКДОТ");


    private String textFor;

    Names(String textFor) {
        this.textFor = textFor;
    }

    public String getTextFor() {
        return textFor;
    }

    public void setTextFor(String textFor) {
        this.textFor = textFor;
    }
}
