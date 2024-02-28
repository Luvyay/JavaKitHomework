package Homework4.common.model;

public class Staffer {
    private int id, experience;
    private String phoneNumber, name;

    public Staffer(String name, String phoneNumber, int experience) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Сотрудник:\nИмя: %s; Номер телефона: %s; Стаж: %d; Табельный номер: %d\n"
                , name, phoneNumber, experience, id);
    }
}
