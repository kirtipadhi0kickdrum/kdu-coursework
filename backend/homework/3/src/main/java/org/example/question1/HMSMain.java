package org.example.question1;

import java.util.logging.Logger;


public class HMSMain {
    private static final Logger logger = Logger.getLogger(HMSMain.class.getName());

    public static void main(String[] a) {
        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);

        double salary = 5000;
        int age = 56;
        boolean isSmoking = true;


        try {

            double premium = insurancePlan.compMonthlyPrem(salary, age, isSmoking);
            String premiumMessage = String.format("The monthly premium for a salary of $%.2f, age %d, smoking: %b is -> $%.2f",
                    salary, age, isSmoking, premium);

            logger.info(premiumMessage);
        } catch (Exception e) {
            logger.severe("An error occurred: " + e.getMessage());

        }
    }
}

