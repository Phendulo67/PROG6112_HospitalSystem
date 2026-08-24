/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medicare;

/**
 *
 * @author User
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Main business-logic class for the MediCare hospital system.
 * It manages patients and the 4 x 5 ward bed layout.
 */
public class HospitalSystem {
    // The assignment requires a ward containing 20 beds arranged as 4 x 5.
    public static final int ROWS = 4;
    public static final int COLUMNS = 5;

    // ArrayList stores all registered patients.
    private final ArrayList<Patient> patients = new ArrayList<>();

    // Two-dimensional array represents the physical ward layout.
    // A null position means that the bed is available.
    private final Inpatient[][] beds = new Inpatient[ROWS][COLUMNS];

    /** Adds a new patient after checking that the ID is unique. */
    public void registerPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        if (findPatient(patient.getPatientId()) != null) {
            throw new IllegalArgumentException("Duplicate Patient ID: " + patient.getPatientId());
        }
        patients.add(patient);
    }

    /** Searches the patient list using the Patient ID. */
    public Patient findPatient(String id) {
        if (id == null) return null;

        for (Patient patient : patients) {
            if (patient.getPatientId().equalsIgnoreCase(id.trim())) {
                return patient;
            }
        }
        return null;
    }

    /** Updates the editable details of an existing patient. */
    public boolean updatePatient(String id, String firstName, String lastName, int age,
                                 String gender, String condition, PatientCategory category) {
        Patient p = findPatient(id);
        if (p == null) return false;

        p.setFirstName(firstName);
        p.setLastName(lastName);
        p.setAge(age);
        p.setGender(gender);
        p.setMedicalCondition(condition);

        // An Inpatient remains an Inpatient while using this ward system.
        if (p instanceof Inpatient && category != PatientCategory.INPATIENT) {
            throw new IllegalArgumentException(
                    "An Inpatient cannot be changed to another category while using this system.");
        }
        p.setCategory(category);
        return true;
    }

    /** Deletes a patient and releases their bed first if necessary. */
    public boolean deletePatient(String id) {
        Patient p = findPatient(id);
        if (p == null) return false;

        // Pattern matching for instanceof gives us the Inpatient object safely.
        if (p instanceof Inpatient inpatient
                && inpatient.getBedNumber() != null
                && !inpatient.getBedNumber().equals("Not allocated")) {
            releaseBed(inpatient.getBedNumber());
        }
        return patients.remove(p);
    }

    /** Returns a copy so outside code cannot directly modify the internal list. */
    public List<Patient> getPatients() {
        return new ArrayList<>(patients);
    }

    /** Allocates a specific bed to an inpatient. */
    public void allocateBed(String patientId, String bedNumber) {
        Patient p = findPatient(patientId);
        if (p == null) {
            throw new IllegalArgumentException("Patient not found.");
        }
        if (!(p instanceof Inpatient inpatient)) {
            throw new IllegalArgumentException("Only Inpatients may be allocated a bed.");
        }
        if (!inpatient.getBedNumber().equals("Not allocated")) {
            throw new IllegalArgumentException("Patient already has a bed.");
        }

        // Convert B01-B20 into row and column positions in the 2D array.
        int[] pos = bedPosition(bedNumber);

        if (beds[pos[0]][pos[1]] != null) {
            throw new IllegalArgumentException("Bed is already occupied.");
        }

        beds[pos[0]][pos[1]] = inpatient;
        inpatient.setBedNumber(normaliseBed(bedNumber));
    }

    /** Releases an occupied bed and marks the patient's bed as available. */
    public void releaseBed(String bedNumber) {
        int[] pos = bedPosition(bedNumber);
        Inpatient inpatient = beds[pos[0]][pos[1]];

        if (inpatient == null) {
            throw new IllegalArgumentException("Bed is already available.");
        }

        beds[pos[0]][pos[1]] = null;
        inpatient.setBedNumber("Not allocated");
    }

    /** Returns the inpatient currently occupying a particular bed. */
    public Inpatient getBed(String bedNumber) {
        int[] p = bedPosition(bedNumber);
        return beds[p[0]][p[1]];
    }

    /** Returns a list of available bed numbers. */
    public List<String> getAvailableBeds() { return bedList(false); }

    /** Returns a list of occupied bed numbers. */
    public List<String> getOccupiedBeds() { return bedList(true); }

    /**
     * Builds either an occupied-bed or available-bed list.
     * The boolean decides which type is returned.
     */
    private List<String> bedList(boolean occupied) {
        ArrayList<String> result = new ArrayList<>();

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                if ((beds[r][c] != null) == occupied) {
                    result.add(bedName(r, c));
                }
            }
        }
        return result;
    }

    public int getOccupiedBedCount() {
        return getOccupiedBeds().size();
    }

    public int getAvailableBedCount() {
        return ROWS * COLUMNS - getOccupiedBedCount();
    }

    /** Calculates ward occupancy as a percentage. */
    public double getOccupancyPercentage() {
        return getOccupiedBedCount() * 100.0 / (ROWS * COLUMNS);
    }

    /** Returns true when all 20 beds are occupied. */
    public boolean allBedsOccupied() {
        return getOccupiedBedCount() == ROWS * COLUMNS;
    }

    /** Returns a new list sorted alphabetically by surname. */
    public List<Patient> sortBySurname() {
        ArrayList<Patient> result = new ArrayList<>(patients);
        result.sort(Comparator.comparing(
                Patient::getLastName, String.CASE_INSENSITIVE_ORDER));
        return result;
    }

    /** Returns a new list sorted alphabetically by Patient ID. */
    public List<Patient> sortByPatientId() {
        ArrayList<Patient> result = new ArrayList<>(patients);
        result.sort(Comparator.comparing(
                Patient::getPatientId, String.CASE_INSENSITIVE_ORDER));
        return result;
    }

    /** Prints the ward as a 4 x 5 grid. [ ] = available, [X] = occupied. */
    public void displayWardLayout() {
        System.out.println("\nWARD LAYOUT (4 x 5)");

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLUMNS; c++) {
                String name = bedName(r, c);
                System.out.printf("%-8s",
                        beds[r][c] == null ? name + "[ ]" : name + "[X]");
            }
            System.out.println();
        }
    }

    /** Converts a bed number such as B07 into its row and column. */
    private int[] bedPosition(String bedNumber) {
        if (bedNumber == null
                || !bedNumber.trim().toUpperCase().matches("B(0[1-9]|1[0-9]|20)")) {
            throw new IllegalArgumentException("Invalid bed number. Use B01 to B20.");
        }

        int n = Integer.parseInt(bedNumber.trim().substring(1));
        return new int[]{(n - 1) / COLUMNS, (n - 1) % COLUMNS};
    }

    /** Changes a bed number into a consistent format, for example b1 -> B01. */
    private String normaliseBed(String s) {
        return "B" + String.format("%02d", Integer.parseInt(s.trim().substring(1)));
    }

    /** Creates the display name for a position in the 2D bed array. */
    private String bedName(int r, int c) {
        return "B" + String.format("%02d", r * COLUMNS + c + 1);
    }
}
