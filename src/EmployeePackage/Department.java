package EmployeePackage;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    public List<Employee> depEmployeeList;
    private int EmployeeCount =0;
    public Department(){
        depEmployeeList = new ArrayList<>();
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCount(){
        this.EmployeeCount +=1;
    }
    public int getCount() {
        return this.EmployeeCount;
    }
}
