package Messanger.Server;

import Messanger.Model.User;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServerWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 400;
    private static final String WINDOW_TITLE = "Chat server";
    private static final String PATH = "server resources/";
    private static final String NAME_FILE = PATH + "log-server.txt";

    private List<User> users;

    private boolean isServerWorking;

    private JButton btnStart, btnStop;
    private JPanel buttonPanel;
    private JTextArea jtaTextArea;
    public ServerWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);

        createJTATextArea();
        createControlButtonPanel();

        add(jtaTextArea);
        add(buttonPanel, BorderLayout.SOUTH);

        isServerWorking = false;
        users = new ArrayList<>();

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    System.out.println("Сервер запущен.");
                    rewriteJTAAllMessage("Сервер запущен.\n");
                } else {
                    isServerWorking = true;
                    System.out.println("Сервер запущен: " + new Date().toString());
                    writeLog("Сервер запущен:");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isServerWorking) {
                    isServerWorking = false;
                    System.out.println("Сервер отключен: " + new Date().toString());
                    writeLog("Сервер отключен:");
                } else {
                    System.out.println("Сервер не запущен.");
                    rewriteJTAAllMessage("Сервер не запущен.\n");
                }
            }
        });

        setVisible(true);
    }

    private void createJTATextArea() {
        jtaTextArea = new JTextArea();
        jtaTextArea.setLineWrap(true); // Перенос строк
        jtaTextArea.setWrapStyleWord(true); // Задает стиль переноса строки (при значение true переносит целиком слово, а
        // не по букве
        jtaTextArea.setEditable(false); // Запрещает вводить текст
        jtaTextArea.setFocusable(false); // Скрывает корретку ввода
    }

    private void createControlButtonPanel() {
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        buttonPanel = new JPanel(new GridLayout(1, 2));

        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
    }

    public void writeLog(String message) {
        rewriteJTAAllMessage(message + " " + new Date().toString() + "\n");

        try {
            File serverDir = new File(PATH);
            if (!serverDir.exists()) {
                serverDir.mkdir();
            }

            File file = new File(NAME_FILE);

            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(message + " " + new Date().toString() + "\n");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessage(User user, String message) {
        try {
            File file = new File(PATH + user.getIPadres() + "#" + user.getPort() + ".txt");
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(user.getLogin() + ":" + message);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readMessage(User user) {
        String messages = "";

        try (FileReader fileReader = new FileReader(PATH + user.getIPadres() +
                "#" + user.getPort() + ".txt")) {

            int c;
            while ((c = fileReader.read()) != -1) {
                messages += (char) c;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return messages;
    }

    public void createLogChatIfNot(User user) {
        File file = new File(PATH + user.getIPadres() + "#" + user.getPort() + ".txt");

        if (!file.exists()) {
            try {

                file.createNewFile();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void rewriteJTAAllMessage(String message) {
        jtaTextArea.setText(jtaTextArea.getText() + message);
    }

    public Document getJtaTextAreaDocument() {
        return jtaTextArea.getDocument();
    }

    public void addUserToList(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean getServerStatus() {
        return isServerWorking;
    }
}
