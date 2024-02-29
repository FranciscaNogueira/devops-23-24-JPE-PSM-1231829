package com.greglturnquist.payroll;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    String firstName;
    String lastName;
    String description;
    String jobTitle;
    int jobYears;
    Employee employee;

    @BeforeEach
    void prepare() {
        firstName = "Frodo";
        lastName = "Baggins";
        description = "ring bearer";
        jobTitle = "jobtitle";
        jobYears = 3;
        employee = new Employee(firstName, lastName, description, jobTitle, jobYears);
    }

    @Test
    void testConstructor() {
        //Act
        Employee result = new Employee(firstName, lastName, description, jobTitle, jobYears);
        //Assert
        assertNotNull(result);
    }
    @Test
    void testConstructor_null() {
        //Act
        Employee result = new Employee(firstName, lastName, description, jobTitle, jobYears);
        //Assert
        assertNotNull(result);
    }

    @Test
    void testEquals() {
        //Act + Assert
        assertTrue(employee.equals(employee));
    }

    @Test
    void testEquals_null() {
        //Act + Assert
        assertFalse(employee.equals(null));
    }

    @Test
    void testEquals_notEmployee() {
        //Arrange
        String firstName = "Rose";
        String lastName = "Taylor";
        String description = "description";
        String jobTitle = "teacher";
        int jobYears = 7;
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears);
        //Act + Assert
        assertFalse(employee.equals(employee1));
    }

    @Test
    void testHashCode() {
    }

    @Test
    void getId() {
        //Arrange
        Long expected = 3L;
        employee.setId(expected);
        //Act
        long result = employee.getId();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setId() {
        //Arrange
        Long expected = 5L;
        //Act
        employee.setId(expected);
        long result = employee.getId();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getFirstName() {
        //Arrange
        String expected = "Frodo";
        //Act
        String result = employee.getFirstName();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setFirstName() {
        //Arrange
        String expected = "Alice";
        //Act
        employee.setFirstName(expected);
        String result = employee.getFirstName();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getLastName() {
        //Arrange
        String expected = "Baggins";
        //Act
        String result = employee.getLastName();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setLastName() {
        //Arrange
        String expected = "Robinson";
        //Act
        employee.setLastName(expected);
        String result = employee.getLastName();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getDescription() {
        //Arrange
        String expected = "ring bearer";
        //Act
        String result = employee.getDescription();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setDescription() {
        //Arrange
        String expected = "animal caregiver";
        //Act
        employee.setDescription(expected);
        String result = employee.getDescription();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getJobTitle() {
        //Arrange
        String expected = "jobtitle";
        //Act
        String result = employee.getJobTitle();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setJobTitle() {
        //Arrange
        String expected = "vet";
        //Act
        employee.setJobTitle(expected);
        String result = employee.getJobTitle();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getJobYears() {
        //Arrange
        int expected = 3;
        //Act
        int result = employee.getJobYears();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setJobYears() {
        //Arrange
        int expected = 5;
        //Act
        employee.setJobYears(expected);
        int result = employee.getJobYears();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void testToString() {
        //Arrange
        employee.setId(1L);
        String expected = "Employee{id=1, firstName='Frodo', lastName='Baggins', description='ring bearer', jobTitle='jobtitle', jobYears='3'}";
        //Act
        String result = employee.toString();
        //Assert
        assertEquals(expected, result);
    }
}