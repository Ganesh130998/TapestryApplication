package org.pages;

import org.DAO.EmployeeDao;
import org.Data.entities.Employee;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

public class DeleteIndividualEmployee {

        @Property
        private int id;
        @Property
        private String name;
        @Property
        private int age;
        @Property
        private String address;

    @Inject
    private EmployeeDao employeeDao;
    @InjectPage
    private EmployeeDetails page2;
        void onActivate(int id,String name,int age,String address) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.address = address;
        }

        Object[] onPassivate() {
            employeeDao.delete(id);
            Employee user  = new Employee(id,name,age,address);
            page2.delete(user);
            return new Object[]{id,name,age,address};
        }
    }






