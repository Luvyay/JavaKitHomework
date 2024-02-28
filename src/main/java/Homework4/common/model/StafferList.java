package Homework4.common.model;

import java.util.ArrayList;
import java.util.List;

public class StafferList<T extends Staffer> {
    private List<T> stafferList;
    private int id = 1;

    public StafferList() {
        this.stafferList = new ArrayList<>();
    }

    public boolean addStaffer(T staff) {
        if (staff == null) return false;

        stafferList.add(staff);
        staff.setId(id++);

        return true;
    }

    public List<T> getStafferList() {
        return stafferList;
    }

    @Override
    public String toString() {
        return "Справочник сотрудников: \n" + stafferList;
    }
}
