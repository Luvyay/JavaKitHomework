package Homework4.common.view;

import Homework4.common.model.Department;
import Homework4.common.model.View;

public class ViewConsole implements View {
    private Department department;

    public ViewConsole() {
        this.department = new Department(this);
    }

    public void addStaffer(String name, String phoneNumber, int experience) {
        department.addStaffer(name, phoneNumber, experience);
    }

    public void findByExperience(int experience) {
        department.findByExperience(experience);
    }

    public void findPhoneNumberByName(String name) {
        department.findPhoneNumberByName(name);
    }

    public void findById(int id) {
        department.findById(id);
    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
