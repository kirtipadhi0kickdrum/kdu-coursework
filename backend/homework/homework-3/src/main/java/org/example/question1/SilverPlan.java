package org.example.question1;

public class SilverPlan extends HealthInsurancePlan{
    public SilverPlan()
    {
        setCoverage(0.7);
    }
    double discount = 30;

    public double getDiscount()
    {
        return discount;
    }

    @Override
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        return (0.06 * salary);
    }
}
