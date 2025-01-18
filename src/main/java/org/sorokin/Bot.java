package org.sorokin;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

public class Bot extends TelegramLongPollingBot {
    final private String BOT_TOKEN = "5994129964:AAE3D-Co5qjmpapCPkwbOM4X6-pNCJFKTmw";
    final private String BOT_NAME = "MyTestSpeaking2Bot";
    Storage storage;

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    Bot()
    {
        storage = new Storage();
        initKeyboard();
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public String extractDigits(String src) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < src.length(); i++) {
            char c = src.charAt(i);
            if (Character.isDigit(c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    @Override
    public void onUpdateReceived(Update update) {
        try{
            if(update.hasMessage() && update.getMessage().hasText())
            {
                //Извлекаем из объекта сообщение  пользователяя
                Message inMess = update.getMessage();
                //Достаем из inMess id чата пользователя
                String chatId = inMess.getChatId().toString();
                //Получаем текст сообщения пользователя, отправляем в написанный нами обработчик
                String response = parseMessage(inMess.getText());
                //Создаем объект класса SendMessage - наш будущий ответ пользователю
                SendMessage outMess = new SendMessage();

                //Добавляем в наше сообщение id чата а также наш ответ
                outMess.setChatId(chatId);
                outMess.setText(response);
                outMess.setReplyMarkup(replyKeyboardMarkup);

                //Отправка в чат
                execute(outMess);
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public String parseMessage(String textMsg) {
        String response;

        //Сравниваем текст пользователя с нашими командами, на основе этого формируем ответ
        if(textMsg.equals("/start"))
            response = "Риточка, любимая, привет! Целую и обнимаю тебя! Я твой любимый муж и буду узнавать как у тебя дела.";
        else if(textMsg.equals("Вопрос №1")) {
            storage.ClearStorage();
            storage.InitStorage();
            response = storage.getQuote(0);
        } else if(textMsg.equals("Вопрос №2")) {
            storage.ClearStorage();
            storage.InitStorage();
            response = storage.getQuote(1);
        } else if(textMsg.equals("Вопрос №3")) {
            storage.ClearStorage();
            storage.InitStorage();
            response = storage.getQuote(2);
        } else if(textMsg.equals("Нежности №1 от мужа")) {
            storage.ClearStorage();
            storage.InitStorage();
            response = storage.getQuote(3);
        } else if(textMsg.equals("Нежности №2 от мужа")) {
            storage.ClearStorage();
            storage.InitStorage();
            response = storage.getQuote(4);
        } else if(textMsg.equals("Главный вопрос")) {
            storage.ClearStorage();
            storage.InitStorage();
            response = storage.getQuote(5);
        } else if(textMsg.equals("/get")) {
            storage.ClearStorage();
            storage.InitStorage();
            response = storage.getRandQuote();
        } else
            response = "Сообщение не распознано";
        return response;
    }

    void initKeyboard()
    {
        //Создаем объект будущей клавиатуры и выставляем нужные настройки
        replyKeyboardMarkup.setResizeKeyboard(true); //подгоняем размер
        replyKeyboardMarkup.setOneTimeKeyboard(false); //скрываем после использования

        //Создаем список с рядами кнопок
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        //Создаем один ряд кнопок и добавляем его в список
        KeyboardRow keyboardRow = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardRow keyboardRow3 = new KeyboardRow();
        keyboardRows.add(keyboardRow);
        //Добавляем одну кнопку с текстом "Просвяти" наш ряд
        keyboardRow.add(new KeyboardButton("Вопрос №1"));
        keyboardRow.add(new KeyboardButton("Вопрос №2"));

        keyboardRows.add(keyboardRow2);
        keyboardRow2.add(new KeyboardButton("Вопрос №3"));
        keyboardRow2.add(new KeyboardButton("Нежности №1 от мужа"));

        keyboardRows.add(keyboardRow3);
        keyboardRow3.add(new KeyboardButton("Нежности №2 от мужа"));
        keyboardRow3.add(new KeyboardButton("Главный вопрос"));
        //добавляем лист с одним рядом кнопок в главный объект
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }
}
