package org.Data.entities;

import javax.persistence.*;

@Entity
@Table(name = "employee")
 public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name= "name")
    private String name;

    @Column(name= "age")
    private int age;

    @Column(name= "address")
    private String address;

    @Column(name= "promote")
    private String promote;


//    public Employee( String name,int age, String address){
//        this(0,name,age,address);
//    }

    public Employee(String name,int age, String address,String promote){

//        this.id = id;
        this.name =name;
        this.age = age;
        this.address =address;
        this.promote = promote;
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

    public String getPromote(){
        return promote;
    }
    public void setPromote(String promote){
        this.promote = promote;
    }
}
