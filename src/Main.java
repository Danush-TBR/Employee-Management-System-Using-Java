//Importing necessary packages
import java.util.*;
import EmployeePackage.*;

public class Main {
    public static void compareEmployee(ArrayList<ArrayList<String>> employees, int datatype, int comparetype, String toCompare){
        Iterator<ArrayList<String>> iterator = employees.iterator();
        while (iterator.hasNext()){
            ArrayList<String> currEmployee = iterator.next();
            String currData = currEmployee.get(datatype);
            //EQUALS
            if(comparetype==1){
                if(!currData.equals(toCompare)){
                    iterator.remove();
                }
            }
            //NOT EQUALS
            else if (comparetype==2) {
                if(currData.equals(toCompare)){
                    iterator.remove();
                }
            }
            //STARTS WITH
            else if (comparetype==3) {
                if(!currData.startsWith(toCompare)){
                    iterator.remove();
                }
            }
            //ENDS WITH
            else if (comparetype==4) {
                if(!currData.endsWith(toCompare)){
                    iterator.remove();
                }
            }
            //CONTAINS
            else if (comparetype==5) {
                if(!currData.contains(toCompare)){
                    iterator.remove();
                }
            }
            //NOT CONTAINS
            else{
                if(currData.contains(toCompare)){
                    iterator.remove();
                }
            }
        }
    }
    public static void main(String[] args) {
        //Declaring Necessary Variables
        Scanner scanner = new Scanner(System.in);
        int choice,comparetype;
        String employeeName;

        //Creating Departments
        Department Management = new Department();
        Management.setName("Management");
        Department HR = new Department();
        HR.setName("HR");
        Department Finance = new Department();
        Finance.setName("Finance");
        Department ProductManagement = new Department();
        ProductManagement.setName("Product Management");
        Department ProductDevelopment = new Department();
        ProductDevelopment.setName("Product Development");
        Department ProductTesting = new Department();
        ProductTesting.setName("Product Testing");

        //Creating a list of departments
        List<Department> departments = new ArrayList<>(Arrays.asList(Management, HR, Finance, ProductManagement,ProductDevelopment,ProductTesting));

        //Adding Employee's and adding them to their respective department
        Employee Sriram = new Employee(1,"Sriram",45,"CEO",Management);
        Employee Mukund = new Employee(2,"Mukund",42,"HR Manager",HR,Sriram);
        Employee Sebestain = new Employee(3,"Sebestain",38,"Finance Manager",Finance,Sriram);
        Employee Aashritha = new Employee(4,"Aashritha",32,"Dev Manager",ProductManagement,Sriram);
        Employee MohammedRafi = new Employee(5,"Mohammed Rafi",35,"HR Lead",HR,Mukund);
        Employee AnjaliKumar = new Employee(6,"Anjali Kumar",29,"HR Associate",HR,MohammedRafi);
        Employee Joseph = new Employee(7,"Joseph",40,"Finance Associate",Finance,Sebestain);
        Employee Ramachandran = new Employee(8,"Ramachandran",27,"Tech Lead",ProductDevelopment,Aashritha);
        Employee AbhinayaShankar = new Employee(9,"Abhinaya Shankar",23,"System Developer",ProductDevelopment,Ramachandran);
        Employee ImranKhan = new Employee(10,"Imran Khan",28,"QA Lead",ProductTesting,Ramachandran);
        //Creating a list of Employees and HashMap of Designation
        List<Employee> employees = new ArrayList<>(Arrays.asList(Sriram,Mukund,Sebestain,Aashritha,MohammedRafi,AnjaliKumar,Joseph,Ramachandran,AbhinayaShankar,ImranKhan));
        HashMap<String,Integer> designationMap = new HashMap<>();
        for(Employee employee : employees){
            designationMap.put(employee.designation,designationMap.getOrDefault(employee.designation,0)+1);
        }
        System.out.println("EMPLOYEE PORTAL...");
        while (true){
            System.out.println("1.Show All Records\n2.Search Records\n3.Manager Report\n4.Reporting For\n5.Summary of Records\n6.EXIT");
            try {
                choice=scanner.nextInt();
                if(choice<1 || choice>6){
                    System.out.println("Enter a number between 1-5");
                }
                else{
                    if(choice==1){
                        System.out.println("SHOW ALL RECORDS.");
                        System.out.println("ID | NAME | AGE | DEPARTMENT | DESIGNATION | REPORTING TO");
                        for(Employee employee : employees){
                            System.out.println("-".repeat(50));
                            System.out.println(employee);
                        }
                        System.out.println("-".repeat(50));
                    }
                    else if(choice==2){
                        System.out.println("SEARCH RECORDS");
                        ArrayList<ArrayList<String>> employees1 = new ArrayList<>();
                        for(Employee employee:employees){
                            ArrayList<String> currEmployee = new ArrayList<>();
                            currEmployee.add(employee.id+"");
                            currEmployee.add(employee.name);
                            currEmployee.add(employee.age+"");
                            currEmployee.add(employee.department.getName());
                            currEmployee.add(employee.designation);
                            if(employee.reportingTo!=null)currEmployee.add(employee.reportingTo.name);
                            else currEmployee.add("CEO");
                            employees1.add(currEmployee);
                        }
                        while (true){
                            System.out.println("Select One Option..");
                            System.out.println("1.Id\n2.Name\n3.Age\n4.Department\n5.Designation\n6.Reporting To\n");
                            choice = scanner.nextInt();
                            System.out.println("Select One Option..");
                            System.out.println("1.Equals\n2.Not Equals\n3.Starts With\n4.Ends With\n5.Contains\n6.Not Contains");
                            comparetype = scanner.nextInt();
                            if(choice>=1 && choice<7 && comparetype>=1 && comparetype<7) {
                                scanner.nextLine();
                                System.out.println("Enter a string to do the comparison");
                                String toCompare = scanner.nextLine();
                                compareEmployee(employees1, choice-1, comparetype,toCompare);
                            }
                            else{
                                System.out.println("Enter a Valid Option");
                                continue;
                            }
                            System.out.println("Do You Wish To Continue (Y/N)");
                            char ch = Character.toLowerCase(scanner.next().charAt(0));
                            if(ch=='n') break;
                        }
                        System.out.println("ID | NAME | AGE | DEPARTMENT | DESIGNATION | REPORTING TO");
                        for(ArrayList<String> currEmployee:employees1) {
                            for(Employee employee:employees) {
                                if(Integer.parseInt(currEmployee.get(0))==employee.id){
                                    System.out.println("-".repeat(50));
                                    System.out.println(employee);
                                }
                            }
                        }
                        System.out.println("-".repeat(50));
                    }
                    else if(choice==3){
                        System.out.println("MANAGER REPORT");
                        for(Employee employee : employees){
                            if(employee.employeeList.size()>0) {
                                System.out.println("MANAGER NAME"+" "+employee.name);
                                System.out.println("ID | NAME | AGE | DEPARTMENT | DESIGNATION | REPORTING TO");
                                for (Employee employee1 : employee.employeeList) {
                                    System.out.println("-".repeat(50));
                                    System.out.println(employee1);
                                }
                                System.out.println("-".repeat(50));
                                System.out.println();
                            }
                        }

                    }
                    else if(choice==4){
                        System.out.println("REPORTING FOR");
                        scanner.nextLine();
                        employeeName = scanner.nextLine();
                        Employee reportFor=null;
                        for (Employee employee:employees) {
                            if (employeeName.equals(employee.name)){
                                reportFor = employee;
                            }
                        }
                        if(reportFor == null){
                            System.out.println("You haven't entered a valid name");
                        }
                        else{
                            while (reportFor.reportingTo!=null){
                                System.out.print(reportFor.name+"->");
                                reportFor = reportFor.reportingTo;
                            }
                            System.out.println(reportFor.name);
                        }
                    }
                    else if (choice==5) {
                        System.out.println("SUMMARY OF RECORDS");
                        System.out.println("DEPARTMENT SUMMARY");
                        for(Department department: departments){
                            System.out.println(department.getName()+" - "+department.getCount());
                        }
                        System.out.println();
                        System.out.println("DESIGNATION SUMMARY");
                        for(Map.Entry<String,Integer> designationEntry : designationMap.entrySet()){
                            System.out.println(designationEntry.getKey()+" - "+designationEntry.getValue());
                        }
                        System.out.println();
                        System.out.println("MANAGER SUMMARY");
                        for (Employee employee:employees) {
                            if(employee.employeeList.size()>0){
                                System.out.println("MANAGER NAME : "+employee.name);
                                for (Employee employee1 : employee.employeeList){
                                    System.out.println(employee1.name);
                                }
                            }
                        }
                        System.out.println();
                    }
                    else{
                        System.out.println("THANK YOU :)");
                        break;
                    }
                }
            }
            catch (Exception e){
                System.out.println("Error occurred: "+e);
            }
        }
    }
}