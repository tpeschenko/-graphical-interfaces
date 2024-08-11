package ru.geekbrains.jdk.chat;

import ru.geekbrains.jdk.chat.client.ClientWindow;
import ru.geekbrains.jdk.chat.server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        new ClientWindow(serverWindow);
        new ClientWindow(serverWindow);
    }
}
