/**
 * Question - 2 - part
 */

package org.example.question1;

//class Billing {
//
//    public static double[] computePaymentAmount(Patient patient, double amount) {
//        double[] payments = new double[2];
//
//        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();
//
//        // your logic
//        if(patientInsurancePlan == null)
//        {
//            double discount = 20;
//            payments[0] = 0;
//            payments[1] = amount - discount;
//            return payments;
//        }
//        double coverage = patientInsurancePlan.getCoverage();
//        double insuranceAmount = amount * coverage;
//        payments[0] = insuranceAmount;
//        if(patientInsurancePlan instanceof PlatinumPlan platinumPlan)
//        {
//            payments[1] = amount - insuranceAmount - ((PlatinumPlan) patientInsurancePlan).getDiscount();
//        }
//        else if(patientInsurancePlan instanceof GoldPlan goldPlan)
//        {
//            payments[1] = (amount - insuranceAmount - ((GoldPlan) patientInsurancePlan).getDiscount());
//        }
//        else if(patientInsurancePlan instanceof SilverPlan silverPlan)
//        {
//            payments[1] = (amount*(1-coverage) - ((SilverPlan) patientInsurancePlan).getDiscount());
//        } else if (patientInsurancePlan instanceof BronzePlan bronzePlan) {
//            payments[1] = amount*(1-coverage) - ((BronzePlan) patientInsurancePlan).getDiscount();
//        }
//
//        return payments;
//    }
//
//}


class Billing {

    public static double[] computePaymentAmount(Patient patient, double amount) {
        double[] payments = new double[2];

        HealthInsurancePlan patientInsurancePlan = patient.getInsurancePlan();

        // your logic
        if (patientInsurancePlan == null) {
            double discount = 20;
            payments[0] = 0;
            payments[1] = amount - discount;
            return payments;
        }

        double coverage = patientInsurancePlan.getCoverage();
        double insuranceAmount = amount * coverage;
        payments[0] = insuranceAmount;

        switch (patientInsurancePlan) {
            case PlatinumPlan platinumPlan -> payments[1] = amount - insuranceAmount - platinumPlan.getDiscount();
            case GoldPlan goldPlan -> payments[1] = amount - insuranceAmount - goldPlan.getDiscount();
            case SilverPlan silverPlan -> payments[1] = amount * (1 - coverage) - silverPlan.getDiscount();
            case BronzePlan bronzePlan -> payments[1] = amount * (1 - coverage) - bronzePlan.getDiscount();
        }

        return payments;
    }
}