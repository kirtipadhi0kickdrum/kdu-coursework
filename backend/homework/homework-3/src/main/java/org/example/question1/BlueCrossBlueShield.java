package org.example.question1;

public class BlueCrossBlueShield implements InsuranceBrand{
    @Override
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double insurance = 0;
        if(insurancePlan instanceof PlatinumPlan)
        {
            if(age > 55)
            {
                insurance += 200;
            }
            if(smoking)
            {
                insurance += 100;
            }
        }
        else if(insurancePlan instanceof GoldPlan)
        {
            if(age > 55)
            {
                insurance += 150;
            }
            if(smoking)
            {
                insurance += 90;
            }
        }
        else if(insurancePlan instanceof SilverPlan)
        {
            if(age > 55)
            {
                insurance += 100;
            }
            if(smoking)
            {
                insurance += 80;
            }
        }
        else if(insurancePlan instanceof BronzePlan)
        {
            if(age > 55)
            {
                insurance += 50;
            }
            if(smoking)
            {
                insurance += 70;
            }
        }
        return insurance;
    }
}
