package Messanger.Controller;

import Messanger.Model.User;

import java.util.List;

public class AuthUser {
    private List<User> users;

    public AuthUser(List<User> users) {
        this.users = users;
    }

    public boolean checkLogin(String login) {
        for (User user : users) {
            if (login.equals(user.getLogin())) {
                return false;
            }
        }

        return true;
    }
}
