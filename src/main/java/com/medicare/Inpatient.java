/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medicare;
/**
 *
 * @author User
 */

/**
 * Represents a patient who has been admitted to a hospital ward.
 *
 * Inpatient extends Patient, demonstrating inheritance.
 */
public class Inpatient extends Patient {
    // These fields are specific to an inpatient.
    private final int wardNumber;
    private String bedNumber;

    /**
     * Creates an inpatient and uses super() to initialise the inherited
     * Patient fields.
     */
    public Inpatient(String patientId, String firstName, String lastName, int age,
                     String gender, String medicalCondition, int wardNumber) {
        // Call the parent class constructor first.
        super(patientId, firstName, lastName, age, gender, medicalCondition,
                PatientCategory.INPATIENT);

        if (wardNumber <= 0) {
            throw new IllegalArgumentException("Ward number must be positive.");
        }

        this.wardNumber = wardNumber;
        // A newly admitted patient does not have a bed yet.
        this.bedNumber = "Not allocated";
    }

    public int getWardNumber() { return wardNumber; }
    public String getBedNumber() { return bedNumber; }

    /** Changes the bed assigned to this inpatient. */
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }

    /**
     * Method overriding: this version adds ward and bed information to the
     * details inherited from Patient.
     */
    @Override
    public String displayDetails() {
        return super.displayDetails()
                + String.format(" | Ward: %d | Bed: %s", wardNumber, bedNumber);
    }
}
