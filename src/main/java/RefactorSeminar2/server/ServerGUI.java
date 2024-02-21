package RefactorSeminar2.server;


import RefactorSeminar2.server.common.Server;
import RefactorSeminar2.server.common.ServerRepositoryInterface;
import RefactorSeminar2.server.common.ServerViewInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ServerViewInterface {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private Server server;
    private ServerRepositoryInterface serverRepository = new ServerRepository();

    private JButton btnStart, btnStop;
    private JTextArea log;

    public ServerGUI() {
        setting();

        createPanel();

        setVisible(true);
    }

    private void setting() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        server = new Server(this, serverRepository);
    }

    @Override
    public void appendLog(String text) {
        log.append(text + "\n");
    }

    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getWork()) {
                    appendLog("Сервер уже был запущен");
                } else {
                    server.setWork(true);
                    appendLog("Сервер запущен!");
                }
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getWork()) {
                    server.setWork(false);
                    while (!server.getClientList().isEmpty()) {
                        int size = server.getClientList().size();
                        server.disconnectUser(server.getClientList().get(size - 1));
                    }
                    appendLog("Сервер остановлен!");
                } else {
                    appendLog("Сервер уже был остановлен");
                }
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);

        return panel;
    }

    public Server getConnection() {
        return server;
    }
}
