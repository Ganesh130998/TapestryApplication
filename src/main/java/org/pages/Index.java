package org.pages;


import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;


public class Index {

        @Property

        private String userName;

        @Property
        private String password;

        /*@InjectPage
        private EmployeeDetails page2;*/

        @InjectComponent("names")
        private Form form;

        @InjectComponent(value = "userName")
        private TextField userNameField;

        @InjectComponent(value = "password")
        private TextField passwordField;

        void onValidateFromNames() {

            if(userName == null || !userName.equals("admin")){
                form.recordError(userNameField, "UserName incorrect");
            }
            if(password == null || !password.equals("admin")){
                form.recordError(passwordField, "Password incorrect.");
            }

        }


        Object onSuccessFromNames() {
//            if(userName.equals("Admin") && password.equals("admin")){
//                page2.set(userName, password);
//                return page2;
//            }

            return EmployeeDetails.class;
        }
    }



