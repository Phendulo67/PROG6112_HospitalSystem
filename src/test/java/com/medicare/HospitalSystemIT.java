/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.medicare;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class HospitalSystemIT {
    
    public HospitalSystemIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of registerPatient method, of class HospitalSystem.
     */
    @Test
    public void testRegisterPatient() {
        System.out.println("registerPatient");
        Patient patient = null;
        HospitalSystem instance = new HospitalSystem();
        instance.registerPatient(patient);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findPatient method, of class HospitalSystem.
     */
    @Test
    public void testFindPatient() {
        System.out.println("findPatient");
        String id = "";
        HospitalSystem instance = new HospitalSystem();
        Patient expResult = null;
        Patient result = instance.findPatient(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePatient method, of class HospitalSystem.
     */
    @Test
    public void testUpdatePatient() {
        System.out.println("updatePatient");
        String id = "";
        String firstName = "";
        String lastName = "";
        int age = 0;
        String gender = "";
        String condition = "";
        PatientCategory category = null;
        HospitalSystem instance = new HospitalSystem();
        boolean expResult = false;
        boolean result = instance.updatePatient(id, firstName, lastName, age, gender, condition, category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletePatient method, of class HospitalSystem.
     */
    @Test
    public void testDeletePatient() {
        System.out.println("deletePatient");
        String id = "";
        HospitalSystem instance = new HospitalSystem();
        boolean expResult = false;
        boolean result = instance.deletePatient(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPatients method, of class HospitalSystem.
     */
    @Test
    public void testGetPatients() {
        System.out.println("getPatients");
        HospitalSystem instance = new HospitalSystem();
        List<Patient> expResult = null;
        List<Patient> result = instance.getPatients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allocateBed method, of class HospitalSystem.
     */
    @Test
    public void testAllocateBed() {
        System.out.println("allocateBed");
        String patientId = "";
        String bedNumber = "";
        HospitalSystem instance = new HospitalSystem();
        instance.allocateBed(patientId, bedNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of releaseBed method, of class HospitalSystem.
     */
    @Test
    public void testReleaseBed() {
        System.out.println("releaseBed");
        String bedNumber = "";
        HospitalSystem instance = new HospitalSystem();
        instance.releaseBed(bedNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBed method, of class HospitalSystem.
     */
    @Test
    public void testGetBed() {
        System.out.println("getBed");
        String bedNumber = "";
        HospitalSystem instance = new HospitalSystem();
        Inpatient expResult = null;
        Inpatient result = instance.getBed(bedNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailableBeds method, of class HospitalSystem.
     */
    @Test
    public void testGetAvailableBeds() {
        System.out.println("getAvailableBeds");
        HospitalSystem instance = new HospitalSystem();
        List<String> expResult = null;
        List<String> result = instance.getAvailableBeds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOccupiedBeds method, of class HospitalSystem.
     */
    @Test
    public void testGetOccupiedBeds() {
        System.out.println("getOccupiedBeds");
        HospitalSystem instance = new HospitalSystem();
        List<String> expResult = null;
        List<String> result = instance.getOccupiedBeds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOccupiedBedCount method, of class HospitalSystem.
     */
    @Test
    public void testGetOccupiedBedCount() {
        System.out.println("getOccupiedBedCount");
        HospitalSystem instance = new HospitalSystem();
        int expResult = 0;
        int result = instance.getOccupiedBedCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAvailableBedCount method, of class HospitalSystem.
     */
    @Test
    public void testGetAvailableBedCount() {
        System.out.println("getAvailableBedCount");
        HospitalSystem instance = new HospitalSystem();
        int expResult = 0;
        int result = instance.getAvailableBedCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOccupancyPercentage method, of class HospitalSystem.
     */
    @Test
    public void testGetOccupancyPercentage() {
        System.out.println("getOccupancyPercentage");
        HospitalSystem instance = new HospitalSystem();
        double expResult = 0.0;
        double result = instance.getOccupancyPercentage();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of allBedsOccupied method, of class HospitalSystem.
     */
    @Test
    public void testAllBedsOccupied() {
        System.out.println("allBedsOccupied");
        HospitalSystem instance = new HospitalSystem();
        boolean expResult = false;
        boolean result = instance.allBedsOccupied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortBySurname method, of class HospitalSystem.
     */
    @Test
    public void testSortBySurname() {
        System.out.println("sortBySurname");
        HospitalSystem instance = new HospitalSystem();
        List<Patient> expResult = null;
        List<Patient> result = instance.sortBySurname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortByPatientId method, of class HospitalSystem.
     */
    @Test
    public void testSortByPatientId() {
        System.out.println("sortByPatientId");
        HospitalSystem instance = new HospitalSystem();
        List<Patient> expResult = null;
        List<Patient> result = instance.sortByPatientId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayWardLayout method, of class HospitalSystem.
     */
    @Test
    public void testDisplayWardLayout() {
        System.out.println("displayWardLayout");
        HospitalSystem instance = new HospitalSystem();
        instance.displayWardLayout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
