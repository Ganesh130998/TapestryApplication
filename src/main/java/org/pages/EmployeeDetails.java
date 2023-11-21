package org.pages;

import org.DAO.EmployeeDao;
import org.Data.entities.Employee;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails {
    @Property
    private List<Employee> users;
    @Inject
    private EmployeeDao employeeDao;
    @Property
    private Employee user;

    EmployeeDetails() {
        users = getUserData();
    }
    private List<Employee> getUserData(){
        List<Employee> Users = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            Employee user = new Employee( i,"User" + i, 20 + i, "City" +i);
            employeeDao.save(user);
            Users.add(user);
        }
        return Users;
    }

    public void set(Employee user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
        employeeDao.save(user);
    }

    public void update(Employee user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        user = employeeDao.update(user);
        users.add(user);
        employeeDao.save(user);
    }
}

