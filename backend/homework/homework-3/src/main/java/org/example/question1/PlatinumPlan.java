package org.example.question1;

public class PlatinumPlan extends HealthInsurancePlan{
    public PlatinumPlan()
    {
        setCoverage(0.9);
    }

    private double discount = 50;
    public double getDiscount()
    {
        return discount;
    }

    public double compMonthlyPrem(double salary, int age,
                                        boolean smoking) {
        return 0.08 * salary +
                getOfferedBy().compMonthlyPrem(this, age, smoking);
    }
}
