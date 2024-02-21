package RefactorSeminar2.client.common;

import RefactorSeminar2.server.common.Server;

public class Client {
    private ClientViewInterface clientViewInterface;
    private Server server;
    private boolean connected;
    private String name;

    public Client(ClientViewInterface clientViewInterface, Server server) {
        this.clientViewInterface = clientViewInterface;
        this.server = server;
    }

    public boolean connectToServer(String name) {
        this.name = name;

        if (server.connectUser(this)) {
            showOnWindow("Вы успешно подключились!\n");
            connected = true;
            String log = server.getHistory();
            if (log != null) {
                showOnWindow(log);
            }

            return true;
        } else {
            showOnWindow("Подключение не удалось");
            return false;
        }
    }

    public void answerFromServer(String text) {
        showOnWindow(text);
    }

    public void message(String text) {
        if (connected) {
            if (!text.isEmpty()) {
                server.message(name + ": " + text);
            }
        } else {
            showOnWindow("Нет подключения к серверу");
        }
    }

    public void disconnectFromServer() {
        if (connected) {
            connected = false;
            clientViewInterface.disconnectFromServer();
            server.disconnectUser(this);
            showOnWindow("Вы были отключены от сервера!");
        }
    }

    private void showOnWindow(String text) {
        clientViewInterface.showMessage(text + "\n");
    }
}
