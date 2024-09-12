package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();

        s1.set_id(1);
        s1.set_name("Kirti");
        s1.set_grade(9);
        s1.set_age(22);

        s2.set_id(2);
        s2.set_name("Aish");
        s2.set_age(21);
        s2.set_grade(10);

        StudentRepository.add_student(s1);
        Logback.getLogger().debug("Student information added");
        StudentRepository.add_student(s2);
        Logback.getLogger().debug("Student information added");

        Scanner opt = new Scanner(System.in);
        System.out.println("do you want to retrieve student data press 0 to enter or 1 for no");
        int retrieve_temp = opt.nextInt();
        while(retrieve_temp == 0){
            System.out.println("Enter the option as to how you want to retrieve the data of the student");
            System.out.println("1. Enter only id");
            System.out.println("2. Enter only name");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();

            int flag = 0;
            while(flag == 0) {
                if (option == 1) {
                    System.out.println("Enter the id whose details you want");
                    int temp_id = sc.nextInt();
                    Student s_details = StudentRepository.get_student(temp_id);

                    assert s_details != null;
                    Logback.getLogger().debug("Student details retrieved" + " name: " + s_details.get_name() + " id: " + s_details.get_id() + " age: " + s_details.get_age() + " grade: " + s_details.get_grade());
                    flag = 1;
                } else if (option == 2) {
                    System.out.println("Enter the name whose details you want");
                    String temp_name = sc.nextLine();
                    Student s_details = StudentRepository.get_student(temp_name);

                    assert s_details != null;
                    Logback.getLogger().debug("Student details retrieved" + " name: " + s_details.get_name() + " id: " + s_details.get_id() + " age: " + s_details.get_age() + " grade: " + s_details.get_grade());
                    flag = 1;
                } else {
                    System.out.println("you have entered the wrong option !!");
                    System.out.println("Enter the option again");
                }
            }
            retrieve_temp = 1;
        }

        System.out.println("do you want to update student data press 0 for yes and 1 for no");
        int update_flag = opt.nextInt();
        while(update_flag == 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Select from the option");
            System.out.println("1. kirti");
            System.out.println("2. Aish");
            int stu_opt = sc.nextInt();
            System.out.println("enter the details you want ot update");
            System.out.println("1. name");
            System.out.println("2. age");
            System.out.println("3. grade");
            int temp_upd = sc.nextInt();

            if (stu_opt == 1) {
                if (temp_upd == 1) {
                    s1.set_name(sc.nextLine());
                    StudentRepository.update_details(s1);
                    Logback.getLogger().debug("Student details updated, The updated details are - name: " + s1.get_name() + " grade: " + s1.get_grade() + " id: " + s1.get_id() + " age: " + s1.get_age());
                } else if (temp_upd == 2) {
                    s1.set_age(sc.nextInt());
                    StudentRepository.update_details(s1);
                    Logback.getLogger().debug("Student details updated, The updated details are - name: " + s1.get_name() + " grade: " + s1.get_grade() + " id: " + s1.get_id() + " age: " + s1.get_age());
                } else {
                    s1.set_grade(sc.nextInt());
                    StudentRepository.update_details(s1);
                    Logback.getLogger().debug("Student details updated, The updated details are - name: " + s1.get_name() + " grade: " + s1.get_grade() + " id: " + s1.get_id() + " age: " + s1.get_age());
                }
            } else {
                if (temp_upd == 1) {
                    s2.set_name(sc.nextLine());
                    StudentRepository.update_details(s2);
                    Logback.getLogger().debug("Student details updated, The updated details are - name: " + s2.get_name() + " grade: " + s2.get_grade() + " id: " + s2.get_id() + " age: " + s2.get_age());
                } else if (temp_upd == 2) {
                    s2.set_age(sc.nextInt());
                    StudentRepository.update_details(s2);
                    Logback.getLogger().debug("Student details updated, The updated details are - name: " + s2.get_name() + " grade: " + s2.get_grade() + " id: " + s2.get_id() + " age: " + s2.get_age());
                } else {
                    s2.set_grade(sc.nextInt());
                    StudentRepository.update_details(s2);
                    Logback.getLogger().debug("Student details updated, The updated details are - name: " + s2.get_name() + " grade: " + s2.get_grade() + " id: " + s2.get_id() + " age: " + s2.get_age());
                }
            }
            update_flag = 1;
        }
    }
}