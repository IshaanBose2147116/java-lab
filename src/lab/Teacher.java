package lab;

public class Teacher extends Employee {
    public Department dept;
    public String role;

    @Override
    public String getEmployeeDetails() {
        return "Name: " + super.getName()
            + "\nDepartment: " + this.dept.deptName
            + "\nRole: " + this.role;
    }
}
