package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername(){
        return "SheduleNotebook";
    }

    @Override
    public String getBotToken() {
        return "6601506396:AAGpIwFMLHC2ca6UZWr6V1WHDBsJu2OfPsE";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();
        var id = user.getId();

        System.out.println(user.getFirstName()+" wrote [" + msg.getText() + "] with id: " + user.getId());
        sendTextToUser(id, msg.getText());

    }
    public void sendTextToUser(Long who, String what){
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString()) //Who are we sending a message to
                .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }


}
