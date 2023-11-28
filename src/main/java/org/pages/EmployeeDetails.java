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

    public void onActivate() {
        if (users == null) {
            users = new ArrayList<>();
        }

        users = employeeDao.getAllEmployees();
    }

    /*private List<Employee> getUserData(){
        List<Employee> Users = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            Employee user = new Employee("User" + i, 20 + i, "City" +i);
            employeeDao.save(user);
            Users.add(user);
        }
        return Users;
    }*/

    public void set(Employee user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        employeeDao.saveEmployee(user);
        users = employeeDao.getAllEmployees();
    }

    public void update(Employee updatedUser) {
        if (users == null) {
            users = new ArrayList<>();
        }

        /*boolean employeeExists = false;
        for (int i = 0; i < users.size(); i++) {
            Employee existingUser = users.get(i);
            if (existingUser.getId() == updatedUser.getId()) {
                existingUser.setName(updatedUser.getName());
                existingUser.setAge(updatedUser.getAge());
                existingUser.setAddress(updatedUser.getAddress());
                employeeExists = true;
                break;
            }
        }

        if (!employeeExists) {
            users.add(updatedUser);
        }*/

        employeeDao.updateEmployee(updatedUser);
        users = employeeDao.getAllEmployees();
    }


    /*public void delete(Employee user) {
        Iterator<Employee> iterator = users.iterator();
        while (iterator.hasNext()) {
            Employee existingUser = iterator.next();
            if (existingUser.getId() == user.getId()) {
                iterator.remove();
                break;
            }
        }
    }*/

    @Property
    private int id;
    @Property
    private String name;
    @Property
    private int age;
    @Property
    private String address;


    public void onActionFromDeleteLink(int id, String name,int age , String address) {
        employeeDao.deleteEmployee(id);
        users = employeeDao.getAllEmployees();
//        Employee user  = new Employee(name,age,address);
//        delete(user);
    }

  /*  public void afterRender() {
        users = employeeDao.getAllEmployees();
    }*/
}

