package kdu.homework.StudentUtil;

import kdu.homework.APIResponseParser.APIResponseParser;

import java.util.logging.Logger;

public class StudentUtil {

    private static final Logger logger = Logger.getLogger(APIResponseParser.class.getName());
    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) {
        double[] stud = new double[2];
        for(int i=0;i<studentsGrades.length;i++)
        {
            int total_grade = 0;
            for(int j=0;j<studentsGrades[i].length;j++)
            {
                int grade = 0;
                if(studentsGrades[i][j] == 'A')
                {
                    grade = 4;
                } else if (studentsGrades[i][j] == 'B') {
                    grade = 3;
                }
                else {
                    grade = 2;
                }
                total_grade = total_grade + grade;
            }
            double temp_GPA = (double) total_grade /(studentsGrades[i].length);
            stud[i] = temp_GPA;
        }
        return new double[]{stud[0], stud[1]};
    }
    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) {
// perform parameter validation. Return null if passed parameters are
        //not valid
// invoke calculateGPA
// construct the result array and return it. You would need an extra
        // for loop to compute the size of the resulting array
        if(lower > higher)
        {
            return null;
        }

        double[] rest_GPA = calculateGPA(studentIdList, studentsGrades);
        int[] result = new int[1];

        if(rest_GPA[0] >= lower && rest_GPA[0] <= higher)
        {
            result[0] = studentIdList[0];
        } else if (rest_GPA[1] >= lower && rest_GPA[1] <= higher) {
            result[0] = studentIdList[1];
        }
        else {
            return null;
        }
        return result;
    }

    public static void main(String a[])
    {
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A','A','A','B'},{'A','B','B'}};
        double lower = 3.2;
        double higher = 3.5;

        StudentUtil obj = new StudentUtil();
//        int[] res = new int[1];
        int[] res = StudentUtil.getStudentsByGPA(lower, higher, studentIdList,studentsGrades);
        assert res != null;
        logger.info("The required student id is - " + res[0]);
    }
}

