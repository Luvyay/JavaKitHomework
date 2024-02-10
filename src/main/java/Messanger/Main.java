package Messanger;

import Messanger.Client.ClientGUI;
import Messanger.Controller.ServerController;
import Messanger.Server.ServerWindow;

public class Main {
    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow();
        ServerController serverController = new ServerController(serverWindow);
        new ClientGUI(serverController);
        new ClientGUI(serverController);
    }
}
