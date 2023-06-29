package org.sorokin;

import java.util.ArrayList;

public class Storage {
    private ArrayList<String> quoteList;

    public Storage()
    {
        quoteList = new ArrayList<>();
    }

    public void ClearStorage() {
        quoteList.clear();
    }

    public void InitStorage() {
        quoteList.add("Риточка, любимая, привет. Как у тебя дела? \n\nТвой любимый муж.");
        quoteList.add("Любовь моя, ты покушала?\n\nТвой любимый муж.");
        quoteList.add("Сладенькая моя, как у тебя настроение?\n\nТвой любимый муж.");
    }

    public String getRandQuote()
    {
        //получаем случайное значение в интервале от 0 до самого большого индекса
        int randValue = (int)(Math.random() * quoteList.size());
        //Из коллекции получаем цитату со случайным индексом и возвращаем ее
        return quoteList.get(randValue);
    }
}
