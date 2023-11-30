package org.pages;


import org.DAO.EmployeeDao;
import org.Data.entities.Employee;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.annotations.Inject;

import java.util.ArrayList;
import java.util.List;


public class NewEmployee {



    @Property
    private String name;
    @Property
    private int age;
    @Property
    private String address;
    @Property
    private String promote;

    @Property
    @Persist
    private Employee employee;

    @Inject
    private EmployeeDao employeeDao;

    private List<Employee> users;

    @InjectComponent("names")
    private Form form;

    @InjectPage
    private EmployeeDetails page2;

    @InjectComponent(value = "name")
    private TextField nameField;

    @InjectComponent(value = "age")
    private TextField ageField;

    @InjectComponent(value = "address")
    private TextField addressField;
    @InjectComponent(value = "promote")
    private TextField promoteField;

    public void onActivate() {
        if (users == null) {
            users = new ArrayList<>();
        }

        users = employeeDao.getAllEmployees();
    }

    public boolean isNameUnique(List<Employee> users, String newName) {
        for (Employee existingEmployee : users) {
            if (existingEmployee.getName().equals(newName)) {
                return false;
            }
        }

        return true;
    }

    void onValidateFromNames() {

        if(name == null || !isNameUnique(users,name)){
            form.recordError(nameField, "Name already exists!");
        }
        else if(age < 18 || age > 60){
            form.recordError(ageField, "Age should be between 18-60");
        }
        else if(address == null || !address.matches("^[a-zA-Z]*$")){
            form.recordError(addressField, "Please provide correct address");
        }
        else if(!(employee.getPromote() == "Software Engineer" || employee.getPromote() != "Team Lead" || employee.getPromote() != "Manager")){
            form.recordError(promoteField, "Please provide correct promotefield");
        }

    }




    Object onSuccessFromNames() {
        Employee user  = new Employee(name,age,address,promote);
        employeeDao.saveEmployee(user);
        //page2.set(user);
        return EmployeeDetails.class;
    }
}



