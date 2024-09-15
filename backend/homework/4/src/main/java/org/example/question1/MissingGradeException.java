package org.example.question1;

public class MissingGradeException extends Exception {
    private final int studentId;

    public MissingGradeException(int studentId){
        super("Missing grade for student ID: " + studentId);
        this.studentId = studentId;
    }
    public int getStudentId()
    {
        return studentId;
    }
}
