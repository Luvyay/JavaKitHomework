package Messanger.Client;

import Messanger.Controller.ServerController;
import Messanger.Model.User;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUI extends JFrame {
    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 400;
    private static final String WINDOW_TITLE = "Chat client";

    private String messageBeforeConnect;
    private boolean isConnecting = false;

    private ServerController serverController;
    private User user;
    private JTextField jtfLogin, jtfIPadres, jtfPort, jtfMessage;
    private JPasswordField jtfPassword;
    private JTextArea jtaAllMessage;
    private JButton btnConnect, btnSendMessage;
    private JPanel serverConnectPanel, messageSendPanel;

    public ClientGUI(ServerController serverController) {
        this.serverController = serverController;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle(WINDOW_TITLE);
        setLocationRelativeTo(null);
        setResizable(false);

        createServerConnectPanel();
        createJTAllMessage();
        createMessageSendPanel();

        add(serverConnectPanel, "North");
        add(jtaAllMessage);
        add(messageSendPanel, "South");

        setListenServer();
        setListenPressBtnConnect();
        setListenSendMessage();

        setVisible(true);
    }

    private void createJTAllMessage() {
        jtaAllMessage = new JTextArea();
        jtaAllMessage.setLineWrap(true);
        jtaAllMessage.setWrapStyleWord(true);
        jtaAllMessage.setEditable(false);
        jtaAllMessage.setFocusable(false);
    }

    private void createServerConnectPanel() {
        jtfIPadres = new JTextField();
        jtfPort = new JTextField();
        jtfLogin = new JTextField();
        jtfPassword = new JPasswordField();
        btnConnect = new JButton("Login");
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(jtfIPadres);
        inputPanel.add(jtfPort);
        inputPanel.add(jtfLogin);
        inputPanel.add(jtfPassword);
        serverConnectPanel = new JPanel(new BorderLayout());
        serverConnectPanel.add(inputPanel);
        serverConnectPanel.add(btnConnect, "East");
    }

    private void createMessageSendPanel() {
        jtfMessage = new JTextField();
        btnSendMessage = new JButton("Send");
        messageSendPanel = new JPanel(new BorderLayout());
        messageSendPanel.add(jtfMessage);
        messageSendPanel.add(btnSendMessage, "East");
    }

    private void setListenServer() {
        serverController.getJtaTextAreaDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                if (isConnecting) {
                    jtaAllMessage.setText(messageBeforeConnect + serverController.readMessage(user));
                }
            }

            public void removeUpdate(DocumentEvent e) {
                if (isConnecting) {
                    jtaAllMessage.setText(messageBeforeConnect + serverController.readMessage(user));
                }
            }

            public void changedUpdate(DocumentEvent e) {
                if (isConnecting) {
                    jtaAllMessage.setText(messageBeforeConnect + serverController.readMessage(user));
                }
            }
        });
    }

    private void setListenPressBtnConnect() {
        btnConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (serverController.checkLogin(jtfLogin.getText()) && serverController.getServerStatus()) {
                    connect();
                } else if (!serverController.getServerStatus()) {
                    jtaAllMessage.setText(jtaAllMessage.getText() + "Сервер не запущен!\n");
                } else {
                    jtaAllMessage.setText(jtaAllMessage.getText() + "Данный логин занят!\n");
                }
            }
        });
    }

    private void connect() {
        isConnecting = true;
        user = new User(jtfLogin.getText(), jtfPassword.getText(), jtfIPadres.getText(), jtfPort.getText());
        serverConnectPanel.setVisible(false);
        serverController.addUser(user);
        setTitle(getTitle() + " " + user.getIPadres() + "#" + user.getPort());
        jtaAllMessage.setText(jtaAllMessage.getText() + "Вы успешно подключились\n");
        messageBeforeConnect = jtaAllMessage.getText() + "\n\n";
        serverController.createLogChatIfNot(user);
        serverController.writeLog(user.getIPadres() + "#" + user.getPort() + " " +
                user.getLogin() + " подключился к беседе");
    }

    private void setListenSendMessage() {
        Action action = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (!jtfMessage.getText().equals("") && isConnecting) {
                    serverController.writeMessage(user, jtfMessage.getText() + "\n");
                    serverController.writeLog(user.getIPadres() + "#" + user.getPort() + " " +
                            user.getLogin() + ": " + jtfMessage.getText());
                    jtfMessage.setText("");
                } else {
                    jtfMessage.setText("");
                }
            }
        };
        btnSendMessage.addActionListener(action);
        jtfMessage.addActionListener(action);
    }
}