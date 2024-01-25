package testCodingChallenges.trickySillyQuestions;


import java.util.ArrayList;
import java.util.List;

class Employee{
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String Name;
    private int age;

    public Employee(double salary, String name, int age) {
        this.salary = salary;
        Name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                '}';
    }
}
class EmployeeSalaryIncremnt {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        Employee e1= new Employee(1000,"unmesh", 20);
        Employee e2= new Employee(1000,"Chetan", 22);
        Employee e3= new Employee(1000,"Sumegh", 25);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);

        List<Employee> increamentedSalaries = employees.
                                              stream().
                                              map(e -> { if (e.getAge() > 21)
                                                  e.setSalary(e.getSalary()* 1.25);
                                              return e;} ).toList();

        System.out.print(increamentedSalaries);





    }

}
