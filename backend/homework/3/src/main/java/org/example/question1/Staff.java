package org.example.question1;

public class Staff {
    private long staffId;
    private int yearOfExperience;
    private String description;
    private double salary;
    public long getStaffId()
    {
        return staffId;
    }
    public void setStaffId(long staffId)
    {
        this.staffId = staffId;
    }
    public int getYearOfExperience()
    {
        return yearOfExperience;
    }
    public void setYearOfExperience(int yearOfExperience)
    {
        this.yearOfExperience = yearOfExperience;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public double getSalary()
    {
        return salary;
    }
    public void setSalary(double salary)
    {
        this.salary = salary;
    }
}
