package org.example;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

class StudentRepository{

    static ArrayList<Student> students = new ArrayList<>();

    public static void add_student(Student s){
        students.add(s);
    }

    public static Student get_student(int id){
        for(Student temp : students)
        {
            if(temp != null && temp.get_id() == id)
            {
                return temp;
            }
        }
        return null;
    }

    public static Student get_student(String name)
    {
        for(Student temp : students)
        {
            if(temp != null && Objects.equals(temp.get_name(), name))
            {
                return temp;
            }
        }
        return null;
    }

    public static void update_details(Student updated_stud)
    {
        for(Student temp : students) {
            if (temp != null && updated_stud.get_id() == temp.get_id()) {
                temp.set_name(updated_stud.get_name());
                temp.set_age(updated_stud.get_age());
                temp.set_grade(updated_stud.get_grade());

            }
        }
    }
}

