package RefactorSeminar2;

import RefactorSeminar2.client.ClientGUI;
import RefactorSeminar2.server.ServerGUI;

public class Main {
    public static void main(String[] args) {
        ServerGUI serverGUI = new ServerGUI();
        new ClientGUI(serverGUI);
        new ClientGUI(serverGUI);
    }
}
