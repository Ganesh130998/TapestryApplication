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
import org.model.SampleEmployee;

import java.util.List;

public class EditIndividualEmployee {

    @Property
    private int id;

    @Property
    @Persist
    private Employee employee;


    @InjectComponent("names")
    private Form form;

    public void onActivate(int id) {
        this.id =id;
        employee = employeeDao.getEmployeeById(id);
    }
/*
    @InjectComponent(value = "id")
    private TextField idField;
*/

    @InjectComponent(value = "name")
    private TextField nameField;

    @InjectComponent(value = "age")
    private TextField ageField;

    @InjectComponent(value = "address")
    private TextField addressField;

    void onValidateFromNames() {

        /*if(name == null || !name.matches("^[a-zA-Z]*$")){
            form.recordError(nameField, "Please provide correct name");
        }
        else if(age < 0 || age !=(int)age){
            form.recordError(ageField, "Please provide correct age");
        }
        else if(address == null || !address.matches("^[a-zA-Z]*$")){
            form.recordError(addressField, "Please provide correct address");
        }*/

    }

    @Inject
    private EmployeeDao employeeDao;
    @InjectPage
    private EmployeeDetails page2;
    @Property
    private List<SampleEmployee> users;
    @Property
    private SampleEmployee user;
    Object onSuccessFromNames() {
//
        page2.update(employee);
        return EmployeeDetails.class;
    }

    public int onPassivate(){
        return this.id;
    }
}



