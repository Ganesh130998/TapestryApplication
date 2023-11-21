package org.pages;


import org.Data.entities.Employee;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.model.SampleEmployee;

import java.util.List;


public class NewEmployee {

    @Property
    private int id;

    @Property
    private String name;
    @Property
    private int age;
    @Property
    private String address;


    @InjectComponent("names")
    private Form form;

    @InjectComponent(value = "id")
    private TextField idField;

    @InjectComponent(value = "name")
    private TextField nameField;

    @InjectComponent(value = "age")
    private TextField ageField;

    @InjectComponent(value = "address")
    private TextField addressField;

    void onValidateFromNames() {

        if(id < 0 || id !=(int)id){
            form.recordError(idField, "Please provide correct id");
        }
        else if(name == null || !name.matches("^[a-zA-Z]*$")){
            form.recordError(nameField, "Please provide correct name");
        }
        else if(age < 0 || age !=(int)age){
            form.recordError(ageField, "Please provide correct age");
        }
        else if(address == null || !address.matches("^[a-zA-Z]*$")){
            form.recordError(addressField, "Please provide correct address");
        }

    }

    @InjectPage
    private EmployeeDetails page2;
    @Property
    private List<SampleEmployee> users;
    @Property
    private SampleEmployee user;
    Object onSuccessFromNames() {
        Employee user  = new Employee(id,name,age,address);
        //users.add(user);
        page2.set(user);
        return EmployeeDetails.class;
    }
}



