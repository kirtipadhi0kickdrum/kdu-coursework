/**
 * Question - 2 part
 */

package org.example.Question1;

public abstract class HealthInsurancePlan{
    // Code for 'coverage' field goes here
    private double coverage;
    public double getCoverage()
    {
        return coverage;
    }
    public void setCoverage(double coverage)
    {
        this.coverage = coverage;
    }
    // Don't worry about the below code and also the InsuranceBrand class
    private InsuranceBrand offeredBy;

    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }

    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    public abstract double computeMonthlyPremium(double salary, int age, boolean smoking);
}
