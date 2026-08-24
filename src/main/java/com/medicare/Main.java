/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.medicare;

/**
 *
 * @author User
 */
import java.util.List;
import java.util.Scanner;

/**
 * Entry point of the MediCare Hospital Patient Admission System.
 * This class handles user input and displays information on the console.
 */
public class Main {
    
    private static final Scanner scanner = new Scanner(System.in);

    // One HospitalSystem object stores the application's data while it runs.
    private static final HospitalSystem hospital = new HospitalSystem();

    /** Starts the console application. */
    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   MEDICARE HOSPITAL SYSTEM");
        System.out.println("======================================");

        boolean running = true;

        // Keep showing the menu until the user chooses option 0.
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            try {
                // A switch selects the method that belongs to the user's choice.
                switch (choice) {
                    case "1" -> registerPatient();
                    case "2" -> searchPatient();
                    case "3" -> updatePatient();
                    case "4" -> deletePatient();
                    case "5" -> displayPatients(hospital.getPatients(), "REGISTERED PATIENTS");
                    case "6" -> allocateBed();
                    case "7" -> releaseBed();
                    case "8" -> hospital.displayWardLayout();
                    case "9" -> displayBeds("AVAILABLE BEDS", hospital.getAvailableBeds());
                    case "10" -> displayBeds("OCCUPIED BEDS", hospital.getOccupiedBeds());
                    case "11" -> reports();
                    case "12" -> sortMenu();
                    case "0" -> running = false;
                    default -> System.out.println("Invalid option. Please choose a number from the menu.");
                }
            } catch (IllegalArgumentException | IllegalStateException ex) {
                // Prevent the program from crashing when invalid input is supplied.
                System.out.println("ERROR: " + ex.getMessage());
            }

            if (running) pause();
        }

        System.out.println("Thank you for using the MediCare system.");
    }

    /** Displays the main system menu. */
    private static void showMenu() {
        System.out.println("\n--------------- MAIN MENU ---------------");
        System.out.println("1. Register patient");
        System.out.println("2. Search patient");
        System.out.println("3. Update patient");
        System.out.println("4. Delete patient");
        System.out.println("5. Display all patients");
        System.out.println("6. Allocate bed");
        System.out.println("7. Release bed");
        System.out.println("8. Display ward layout");
        System.out.println("9. Display available beds");
        System.out.println("10. Display occupied beds");
        System.out.println("11. Ward reports");
        System.out.println("12. Sort patients");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    /** Collects the information needed to register a patient. */
    private static void registerPatient() {
        System.out.println("\n--- REGISTER PATIENT ---");

        String id = input("Patient ID: ");
        String first = input("First name: ");
        String last = input("Last name: ");
        int age = inputInt("Age: ");
        String gender = input("Gender: ");
        String condition = input("Medical condition: ");
        PatientCategory category = inputCategory();

        Patient patient;

        // Inpatients need a ward number, so create the child class when needed.
        if (category == PatientCategory.INPATIENT) {
            int ward = inputInt("Ward number (use 1 for this ward): ");
            patient = new Inpatient(id, first, last, age, gender, condition, ward);
        } else {
            patient = new Patient(id, first, last, age, gender, condition, category);
        }

        hospital.registerPatient(patient);
        System.out.println("Patient registered successfully.");
    }

    /** Searches for a patient using their Patient ID. */
    private static void searchPatient() {
        String id = input("Enter Patient ID: ");
        Patient p = hospital.findPatient(id);

        if (p == null) {
            System.out.println("Patient not found.");
        } else {
            System.out.println(p.displayDetails());
        }
    }

    /** Updates an existing patient's information. */
    private static void updatePatient() {
        String id = input("Enter Patient ID to update: ");
        Patient p = hospital.findPatient(id);

        if (p == null) {
            System.out.println("Patient not found.");
            return;
        }

        String first = input("First name [" + p.getFirstName() + "]: ");
        String last = input("Last name [" + p.getLastName() + "]: ");
        int age = inputInt("Age [" + p.getAge() + "]: ");
        String gender = input("Gender [" + p.getGender() + "]: ");
        String condition = input("Medical condition [" + p.getMedicalCondition() + "]: ");
        PatientCategory category = inputCategory();

        hospital.updatePatient(id, first, last, age, gender, condition, category);
        System.out.println("Patient updated successfully.");
    }

    /** Deletes a patient from the system. */
    private static void deletePatient() {
        String id = input("Enter Patient ID to delete: ");
        System.out.println(hospital.deletePatient(id)
                ? "Patient deleted successfully."
                : "Patient not found.");
    }

    /** Assigns a specific bed to an inpatient. */
    private static void allocateBed() {
        String id = input("Inpatient Patient ID: ");
        String bed = input("Bed number (B01-B20): ");

        hospital.allocateBed(id, bed);
        System.out.println("Bed allocated successfully.");
    }

    /** Releases a bed so that it becomes available again. */
    private static void releaseBed() {
        hospital.releaseBed(input("Bed number to release: "));
        System.out.println("Bed released successfully.");
    }

    /** Displays the requested ward statistics and patient/bed reports. */
    private static void reports() {
        System.out.println("\n============== WARD REPORTS ==============");
        displayPatients(hospital.getPatients(), "PATIENT REPORT");
        displayBeds("AVAILABLE BEDS", hospital.getAvailableBeds());
        displayBeds("OCCUPIED BEDS", hospital.getOccupiedBeds());
        System.out.println("Total registered patients: " + hospital.getPatients().size());
        System.out.println("Total occupied beds: " + hospital.getOccupiedBedCount());
        System.out.printf("Ward occupancy: %.2f%%%n", hospital.getOccupancyPercentage());
    }

    /** Lets the user choose how the patient list should be sorted. */
    private static void sortMenu() {
        System.out.println("\n1. Sort by surname\n2. Sort by Patient ID");
        String choice = input("Choice: ");

        if (choice.equals("1")) {
            displayPatients(hospital.sortBySurname(), "PATIENTS SORTED BY SURNAME");
        } else if (choice.equals("2")) {
            displayPatients(hospital.sortByPatientId(), "PATIENTS SORTED BY PATIENT ID");
        } else {
            System.out.println("Invalid sorting option.");
        }
    }

    /** Prints a list of patients. */
    private static void displayPatients(List<Patient> patients, String title) {
        System.out.println("\n============== " + title + " ==============");

        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
            return;
        }

        for (Patient p : patients) {
            System.out.println(p.displayDetails());
        }
    }

    /** Prints a list of available or occupied bed numbers. */
    private static void displayBeds(String title, List<String> beds) {
        System.out.println("\n--- " + title + " ---");
        System.out.println(beds.isEmpty() ? "None" : String.join(", ", beds));
    }

    /** Reads the patient category selected by the user. */
    private static PatientCategory inputCategory() {
        System.out.println("1. Inpatient\n2. Outpatient\n3. Emergency");

        return switch (input("Patient category: ")) {
            case "1" -> PatientCategory.INPATIENT;
            case "2" -> PatientCategory.OUTPATIENT;
            case "3" -> PatientCategory.EMERGENCY;
            default -> throw new IllegalArgumentException("Invalid patient category.");
        };
    }

    /** Reads a line of text from the keyboard. */
    private static String input(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /** Reads and validates an integer from the keyboard. */
    private static int inputInt(String prompt) {
        try {
            return Integer.parseInt(input(prompt));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Please enter a valid whole number.");
        }
    }

    /** Pauses after each menu operation so the user can read the result. */
    private static void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}
