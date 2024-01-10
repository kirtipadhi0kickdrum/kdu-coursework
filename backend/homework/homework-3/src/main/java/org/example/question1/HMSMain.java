package org.example.question1;

import java.util.logging.Logger;

public class HMSMain {
    private static final Logger logger = Logger.getLogger(HMSMain.class.getName());
    public static void main(String[] a)
    {

        User staff = new User();
        InsuranceBrand insuranceBrand = new BlueCrossBlueShield();
        HealthInsurancePlan insurancePlan = new PlatinumPlan();
        insurancePlan.setOfferedBy(insuranceBrand);
        staff.setInsurancePlan(insurancePlan);
        double premium = insurancePlan.compMonthlyPrem(5000, 56, true);
        logger.info("The monthly premium is -> " + premium);
    }
}

