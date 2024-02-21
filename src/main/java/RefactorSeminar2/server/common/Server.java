package RefactorSeminar2.server.common;

import RefactorSeminar2.client.common.Client;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerViewInterface serverView;
    private ServerRepositoryInterface serverRepository;
    private List<Client> clientList;
    private boolean work;

    public Server(ServerViewInterface serverView, ServerRepositoryInterface serverRepository) {
        this.serverView = serverView;
        this.serverRepository = serverRepository;
        clientList = new ArrayList<>();
        work = false;
    }

    public boolean connectUser(Client client) {
        if (!work) {
            return false;
        }
        clientList.add(client);
        return true;
    }

    public void disconnectUser(Client client) {
        clientList.remove(client);
        if (client != null) {
            client.disconnectFromServer();
        }
    }

    public void message(String text) {
        if (!work) {
            return;
        }

        serverView.appendLog(text);
        answerAll(text);
        serverRepository.saveInLog(text);
    }

    private void answerAll(String text) {
        for (Client client : clientList) {
            client.answerFromServer(text);
        }
    }

    public String getHistory() {
        return serverRepository.readLog();
    }

    public boolean getWork() {
        return work;
    }

    public void setWork(boolean status) {
        work = status;
    }

    public List<Client> getClientList() {
        return clientList;
    }
}
