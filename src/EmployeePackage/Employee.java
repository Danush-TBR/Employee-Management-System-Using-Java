package EmployeePackage;

import java.util.ArrayList;
import java.util.List;

public class Employee{
    public int id,age;
    public String name,designation;
    public Department department;
    public Employee reportingTo;
    public List<Employee> employeeList;
    public Employee(int id, String name, int age, String designation, Department department){
        this.id = id;
        this.age = age;
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.employeeList = new ArrayList<>();
        this.department.depEmployeeList.add(this);
        this.department.setCount();
    }
    public Employee(int id, String name, int age, String designation, Department department, Employee reportingTo){
        this(id,name,age,designation,department);
//        this.id = id;
//        this.age = age;
//        this.name = name;
//        this.designation = designation;
//        this.department = department;
//        this.employeeList = new ArrayList<>();
//        department.depEmployeeList.add(this);
//        department.setCount();
        this.reportingTo = reportingTo;
        reportingTo.employeeList.add(this);
    }
    @Override
    public String toString(){
        String toReturn=this.id+" | "+this.name+" | "+this.age+" | "+this.department.getName()+" | "+this.designation+" | ";
        if(this.reportingTo!=null) toReturn+=this.reportingTo.name;
        else toReturn+='-';
        toReturn+=" | ";
        return toReturn;
    }
}
