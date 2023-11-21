package org.Data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
 public class Employee {

    @Id
    @Column(name ="id")
    private int id;

    @Column(name= "name")
    private String name;

    @Column(name= "age")
    private int age;

    @Column(name= "address")
    private String address;


    public Employee(int id, String name,int age, String address){
       this.id = id;
       this.name =name;
       this.age = age;
       this.address =address;
    }

    public Employee(){

    }

   public int getId(){
      return id;
   }
   public void setId(int id){
      this.id = id;
   }
   public String getName(){
      return name;
   }
   public void setName(String name){
      this.name = name;
   }

   public int getAge(){
      return age;
   }
   public void setAge(int age){
      this.age = age;
   }

   public String getAddress(){
      return address;
   }
   public void setAddress(String address){
      this.address = address;
   }

}
