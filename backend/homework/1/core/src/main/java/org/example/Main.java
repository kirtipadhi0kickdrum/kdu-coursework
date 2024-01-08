package org.example;

class Student{
    private int id;
    private String name;
    private int age;
    private int grade;

    public void set_age(int age)
    {
        this.age = age;
    }
    public void set_name(String name)
    {
        this.name = name;
    }
    public void set_id(int id)
    {
        this.id = id;
    }
    public void set_grade(int grade)
    {
        this.grade = grade;
    }

    public int get_age()
    {
        return age;
    }
    public int get_id()
    {
        return id;
    }
    public String get_name()
    {
        return name;
    }
    public int get_grade()
    {
        return grade;
    }
}

