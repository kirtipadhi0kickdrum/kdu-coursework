package org.example.question1;




public class BlueCrossBlueShield implements InsuranceBrand {
    @Override
    public double compMonthlyPrem(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        double insurance = 0;

        if (insurancePlan instanceof PlatinumPlan) {
            insurance += calculatePlatinumPremium(age, smoking);
        } else if (insurancePlan instanceof GoldPlan) {
            insurance += calculateGoldPremium(age, smoking);
        } else if (insurancePlan instanceof SilverPlan) {
            insurance += calculateSilverPremium(age, smoking);
        } else if (insurancePlan instanceof BronzePlan) {
            insurance += calculateBronzePremium(age, smoking);
        }

        // Uncovered code

        return insurance;
    }

    private double calculatePlatinumPremium(int age, boolean smoking) {
        double premium = 0;

        if (age > 55) {
            premium += 200;
        }

        if (smoking) {
            premium += 100;
        }

        return premium;
    }

    private double calculateGoldPremium(int age, boolean smoking) {
        double premium = 0;

        if (age > 55) {
            premium += 150;
        }

        if (smoking) {
            premium += 90;
        }

        return premium;
    }

    private double calculateSilverPremium(int age, boolean smoking) {
        double premium = 0;

        if (age > 55) {
            premium += 100;
        }

        if (smoking) {
            premium += 80;
        }

        return premium;
    }

    private double calculateBronzePremium(int age, boolean smoking) {
        double premium = 0;

        if (age > 55) {
            premium += 50;
        }

        if (smoking) {
            premium += 70;
        }

        return premium;
    }
}
