package Homework4;

import Homework4.common.view.ViewConsole;

public class Main {
    public static void main(String[] args) {
        ViewConsole viewConsole = new ViewConsole();

        viewConsole.addStaffer("Nikita", "789", 2);
        viewConsole.addStaffer("Olga", "987", 3);
        viewConsole.addStaffer("Gena", "654", 1);
        viewConsole.addStaffer("Anton", "456", 2);

        viewConsole.findByExperience(2);
        viewConsole.findById(3);
        viewConsole.findPhoneNumberByName("Anton");
    }
}
