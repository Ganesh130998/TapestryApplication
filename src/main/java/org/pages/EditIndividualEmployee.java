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

    @Inject
    private EmployeeDao employeeDao;
//    @Inject
//    private EmployeeDaoImpl employeeDaoImpl;
    @InjectPage
    private EmployeeDetails page2;
    @Property
    private List<SampleEmployee> users;

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

    @InjectComponent(value = "promote")
    private TextField promoteField;

    void onValidateFromNames() {

        if(employee.getName() == null || employeeDao.isNameAlreadyExists(employee.getName())){
            form.recordError(nameField, "Name already exists!");
        }
        else if(employee.getAge() < 18 || employee.getAge() > 60){
            form.recordError(ageField, "Age should be between 18-60");
        }
        else if(employee.getAddress() == null || !employee.getAddress().matches("^[a-zA-Z]*$")){
            form.recordError(addressField, "Please provide correct address");
        }
        else if(!(employee.getPromote() == "Software Engineer" || employee.getPromote() != "Team Lead" || employee.getPromote() != "Manager")){
            form.recordError(promoteField, "Please provide correct promotefield");
        }

    }


    Object onSuccessFromNames() {
//
        page2.update(employee);
        return EmployeeDetails.class;
    }

    public int onPassivate(){
        return this.id;
    }
}



