package org.pages;

import org.DAO.EmployeeDao;
import org.Data.entities.Employee;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDetails {
    @Property
    private List<Employee> users;
    @Inject
    private EmployeeDao employeeDao;

    @InjectComponent
    private Zone imageDialogZone;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Property
    @Persist
    private int count1;

    @Property
    private String designation;

    @Property
    private String imagePath;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    @Property
    private Employee employee;

    @Property
    private int id;
    @Property
    private String name;
    @Property
    private int age;
    @Property
    private String address;
    @Property
    private String promote;



//    @SetupRender
//    void setup() {
//        countMap = new HashMap<>();
//        //designation = ;
//        for (Employee employee : users) {
//            countMap.put(employee, 0);
//        }
//    }

    public void onActivate() {
        if (users == null) {
            users = new ArrayList<>();
        }
        users = employeeDao.getAllEmployees();
    }

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

        employeeDao.updateEmployee(updatedUser);
        users = employeeDao.getAllEmployees();
    }

    public void onActionFromDeleteLink(int id) {
        employeeDao.deleteEmployee(id);
    }



    @OnEvent(component = "imageLink")
    void imageLink(String imagePath) {
        this.imagePath = imagePath;
        ajaxResponseRenderer.addRender(imageDialogZone);
    }
}

