package org.pages;

import org.DAO.EmployeeDao;
import org.Data.entities.Employee;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.ArrayList;
import java.util.Iterator;
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

    public void update(Employee updatedUser) {
        if (users == null) {
            users = new ArrayList<>();
        }


        /*//employeeDao.delete(user);
        user = employeeDao.update(user);
        users.add(user);
        //employeeDao.save(user);*/


        boolean employeeExists = false;
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
        }

        employeeDao.save(updatedUser);
    }

   /* public void delete(Employee user) {
//        employeeDao.delete(user.getId());
//        users = employeeDao.getAll();

        users.remove(user);

//        users= users.stream()
//                .filter(employee -> employee.getId()!=user.getId())
//                .collect(Collectors.toList());
    }*/

    public void delete(Employee user) {
        Iterator<Employee> iterator = users.iterator();
        while (iterator.hasNext()) {
            Employee existingUser = iterator.next();
            if (existingUser.getId() == user.getId()) {
                iterator.remove();
                break;
            }
        }
    }
}

