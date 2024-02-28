package Homework4.common.model;

import java.util.List;

public class Department {
    private StafferList<Staffer> stafferList;
    private View controller;

    public Department(View controller) {
        this.stafferList = new StafferList<>();
        this.controller = controller;
    }

    public void addStaffer(String name, String phoneNumber, int experience) {
        if (stafferList.addStaffer(new Staffer(name, phoneNumber, experience))) {
            controller.print("Сотрудник успешно добавлен.");
        } else {
            controller.print("Введены не корректные данные");
        }
    }

    public void findByExperience(int experience) {
        List<Staffer> result = stafferList.getStafferList()
                .stream().filter(staffer -> staffer.getExperience() == experience).toList();

        if (result.isEmpty()) {
            controller.print(String.format("Сотрудников со стажем работы %d - нет"
                    , experience));
        } else {
            controller.print(result.toString());
        }
    }

    public void findPhoneNumberByName(String name) {
        List<String> result = stafferList.getStafferList()
                .stream().filter(staffer -> staffer.getName().equals(name)).map(Staffer::getPhoneNumber).toList();

        if (result.isEmpty()) {
            controller.print(String.format("Сотрудников с именем %s - нет"
                    , name));
        } else {
            controller.print(result.toString());
        }
    }

    public void findById(int id) {
        List<Staffer> result = stafferList.getStafferList()
                .stream().filter(staffer -> staffer.getId() == id).toList();

        if (result.isEmpty()) {
            controller.print(String.format("Сотрудников с табельным номером %d - нет"
                    , id));
        } else {
            controller.print(result.toString());
        }
    }

    @Override
    public String toString() {
        return stafferList.toString();
    }
}
