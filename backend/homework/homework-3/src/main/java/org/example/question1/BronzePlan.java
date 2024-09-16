package org.example.question1;

public class BronzePlan extends HealthInsurancePlan{
    public BronzePlan(){
        setCoverage(0.6);
    }
    double discount = 25;

    public double getDiscount() {
        return discount;
    }

    @Override
    public double compMonthlyPrem(double salary, int age, boolean smoking) {
        return (0.05 * salary);
    }
}
