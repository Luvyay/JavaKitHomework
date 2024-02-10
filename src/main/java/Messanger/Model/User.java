package Messanger.Model;

public class User {
    private String login;
    private String password;
    private String IPadres;
    private String port;

    public User(String login, String password, String IPadres, String port) {
        this.login = login;
        this.password = password;
        this.IPadres = IPadres;
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIPadres() {
        return IPadres;
    }

    public void setIPadres(String IPadres) {
        this.IPadres = IPadres;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
