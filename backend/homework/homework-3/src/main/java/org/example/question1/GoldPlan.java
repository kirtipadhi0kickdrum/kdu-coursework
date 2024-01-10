package org.example.question1;

public class GoldPlan extends HealthInsurancePlan{
    public GoldPlan()
    {
        setCoverage(0.8);
    }
    double discount = 40;

    public double getDiscount()
    {
        return discount;
    }

    @Override
    public double compMonthlyPrem(double salary, int age, boolean smoking) {
        return (0.07 * salary);
    }
}
