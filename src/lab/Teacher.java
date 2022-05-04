package lab;

public class Teacher extends Employee {
    public Department dept;
    public String role;

    public Teacher(String fname, String lname, int empID, Department dept, String role) throws EmployeeIDException {
        super.setName(fname + " " + lname);

        if (empID <= 100000 || empID >= 999999)
            throw new EmployeeIDException("Employee ID must be between 100000 and 999999.");

        super.employeeID = empID;
        this.dept = dept;
        this.role = role;
    }

    @Override
    public String getEmployeeDetails() {
        return "Name: " + super.getName()
            + "\nDepartment: " + this.dept.deptName
            + "\nRole: " + this.role;
    }
}
