package Messanger.Controller;

import Messanger.Model.User;
import Messanger.Server.ServerWindow;

import javax.swing.text.Document;

public class ServerController {
    private ServerWindow serverWindow;
    private AuthUser authUser;

    public ServerController(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
        authUser = new AuthUser(serverWindow.getUsers());
    }

    public Document getJtaTextAreaDocument() {
        return serverWindow.getJtaTextAreaDocument();
    }

    public boolean checkLogin(String login) {
        if (authUser.checkLogin(login)) {
            return true;
        }

        return false;
    }

    public void addUser(User user) {
        serverWindow.addUserToList(user);
    }

    public boolean getServerStatus() {
        return serverWindow.getServerStatus();
    }

    public void writeLog(String message) {
        serverWindow.writeLog(message);
    }

    public void writeMessage(User user, String message) {
        serverWindow.writeMessage(user, message);
    }

    public String readMessage(User user) {
        return serverWindow.readMessage(user);
    }

    public void createLogChatIfNot(User user) {
        serverWindow.createLogChatIfNot(user);
    }
}
