package ru.asocial.games.catacombs;

public interface IMessageService {

    void writeMessage(String tag, String message);

    void close();

}
