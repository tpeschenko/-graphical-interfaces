package ru.geekbrains.jdk.chat.server;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame implements Chat{

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JTextArea taMessages;
    private JButton btnStart, btnStop;
    private boolean status;
    private List<String> messages;
    private File logFile;
    private IOController ioController;

    /**
     * Constructor
     */
    public ServerWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Server is stopped");
        setResizable(false);
        status = false;
        messages = new ArrayList<>();
        taMessages = new JTextArea();
        logFile = new File("./log.txt");
        ioController = new IOControllerDefault();


        //region pnlAdmin

        JPanel pnlAdmin = new JPanel(new GridLayout(1, 2));

        btnStart = new JButton("Start");
        btnStart.addActionListener(e -> startServer());

        btnStop = new JButton("Stop");
        btnStop.addActionListener(e -> stopServer());

        pnlAdmin.add(btnStart);
        pnlAdmin.add(btnStop);

        add(pnlAdmin, BorderLayout.SOUTH);

        //endregion

        JScrollPane scrollPane = new JScrollPane(taMessages);
        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Проверяет запущен ли сервер чата
     *
     * @return результат проверки
     */
    @Override
    public boolean isAvailable() {
        return status;
    }

    /**
     * Обрабатывает сообщение клиента
     *
     * @param message сообщение в чат
     */
    @Override
    public void chat(String message) {
        messages.add(message);
        taMessages.append(message);
        taMessages.append("\n");
        ioController.writeFile(messages, logFile);


    }

    /**
     * Запускает сервер чата
     */
    private void startServer() {
        this.status = true;
        this.setTitle("Server is running");
        if (logFile.exists()) loadMessages();

    }

    /**
     * Останавливает сервер
     */
    private void stopServer() {
        this.status = false;
        this.setTitle("Server is stopped");

    }

    /**
     * Обнавляет чат
     */
    private void loadMessages() {
        messages = ioController.readFile(logFile);
            for (int i = 0; i < messages.size(); i++) {
                taMessages.append(messages.get(i));
                taMessages.append("\n");
            }

        revalidate();
    }
}
