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
 * Base class that stores the common information for every patient.
 *
 * Encapsulation is used here: the fields are private and are accessed
 * through public getter and setter methods.
 */
public class Patient {
    // Patient information is kept private to protect the object's data.
    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String medicalCondition;
    private PatientCategory category;

    /**
     * Creates a Patient object and validates all supplied information.
     */
    public Patient(String patientId, String firstName, String lastName, int age,
                   String gender, String medicalCondition, PatientCategory category) {
        setPatientId(patientId);
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setGender(gender);
        setMedicalCondition(medicalCondition);
        setCategory(category);
    }

    // Getter methods return the stored patient information.
    public String getPatientId() { return patientId; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getMedicalCondition() { return medicalCondition; }
    public PatientCategory getCategory() { return category; }

    /** Patient ID is required and cannot be blank. */
    private void setPatientId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Patient ID is required.");
        }
        this.patientId = id.trim();
    }

    // Setter methods validate data before changing the object's fields.
    public void setFirstName(String value) { firstName = required(value, "First name"); }
    public void setLastName(String value) { lastName = required(value, "Last name"); }

    /** Checks that the age is within a sensible range. */
    public void setAge(int value) {
        if (value < 0 || value > 120) {
            throw new IllegalArgumentException("Age must be between 0 and 120.");
        }
        age = value;
    }

    public void setGender(String value) { gender = required(value, "Gender"); }
    public void setMedicalCondition(String value) { medicalCondition = required(value, "Medical condition"); }

    /** Ensures that a patient always has a category. */
    public void setCategory(PatientCategory value) {
        if (value == null) {
            throw new IllegalArgumentException("Patient category is required.");
        }
        category = value;
    }

    /**
     * Reusable validation method for required text fields.
     */
    private String required(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " is required.");
        }
        return value.trim();
    }

    /**
     * Returns the basic details of a patient as one formatted line.
     */
    public String displayDetails() {
        return String.format(
                "ID: %s | Name: %s %s | Age: %d | Gender: %s | Condition: %s | Category: %s",
                patientId, firstName, lastName, age, gender, medicalCondition, category);
    }
}
