package ru.geekbrains.jdk.chat.client;

import ru.geekbrains.jdk.chat.server.Chat;
import ru.geekbrains.jdk.chat.server.ServerWindow;

import javax.swing.*;
import java.awt.*;

public class ClientWindow extends JFrame {

    private Chat serverWindow;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JButton btnSend, btnLogin;
    private JTextField tfLogin;
    private JTextField tfMessage;
    private String login;

    /**
     * Constructor
     */
    public ClientWindow(Chat serverWindow) {
        this.serverWindow = serverWindow;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Unknown user");
        setResizable(false);

        //region pnlLogin

        JPanel pnlLogin = new JPanel(new GridLayout(1, 2));

        tfLogin = new JTextField();
        tfLogin.addActionListener(e -> logIn());

        btnLogin = new JButton("Login");
        btnLogin.setToolTipText("Введите имя");
        btnLogin.addActionListener(e -> logIn());

        pnlLogin.add(tfLogin);
        pnlLogin.add(btnLogin);


        add(pnlLogin, BorderLayout.NORTH);

        //endregion

        //region pnlMessage

        JPanel pnlMessage = new JPanel(new GridLayout(1,2));

        tfMessage = new JTextField();
        tfMessage.setEnabled(false);
        tfMessage.addActionListener(e -> sendMessage());

        btnSend = new JButton("Send");
        btnSend.setToolTipText("Нажмите, чтобы отослать сообщение");
        btnSend.setEnabled(false);
        btnSend.addActionListener(e -> sendMessage());

        pnlMessage.add(tfMessage);
        pnlMessage.add(btnSend);


        add(pnlMessage, BorderLayout.SOUTH);

        //endregion

        setVisible(true);
    }

    /**
     * Авторизует пользователя
     */
    private void logIn() {
        if (!tfLogin.getText().equals("")) {
            login = tfLogin.getText();
            this.setTitle(tfLogin.getText());
            tfMessage.setEnabled(true);
            btnSend.setEnabled(true);
        }
    }

    /**
     * Отправляет сообщение
     */
    private void sendMessage() {
        if (!serverWindow.isAvailable()) {
            JOptionPane.showMessageDialog(this, "Сервер не доступен!");
        } else {
            if (!tfMessage.getText().equals("")) {
                serverWindow.chat(String.format("%s: %s", login, tfMessage.getText()));
            }
        }
    }
}
