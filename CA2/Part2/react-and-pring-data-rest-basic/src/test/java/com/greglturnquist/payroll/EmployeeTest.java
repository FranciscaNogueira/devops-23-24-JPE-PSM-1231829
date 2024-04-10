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
    String email;
    Employee employee;

    @BeforeEach
    void prepare() {
        firstName = "Frodo";
        lastName = "Baggins";
        description = "ring bearer";
        jobTitle = "jobtitle";
        jobYears = 3;
        email = "email@sapo.pt";
        employee = new Employee(firstName, lastName, description, jobTitle, jobYears, email);
    }

    @Test
    void testConstructor() {
        //Act
        Employee result = new Employee(firstName, lastName, description, jobTitle, jobYears, email);
        //Assert
        assertNotNull(result);
    }
    @Test
    void testConstructor_null() {
        //Act
        Employee result = new Employee(firstName, lastName, description, jobTitle, jobYears, email);
        //Assert
        assertNotNull(result);
    }

    @Test
    void testConstructor_nullFirstName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(null, lastName, description, jobTitle, jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyFirstName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee("", lastName, description, jobTitle, jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_nullLastName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, null, description, jobTitle, jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyLastName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, "", description, jobTitle, jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_nullDescription() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, null, jobTitle, jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyDescription() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, "", jobTitle, jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_nullJobTitle() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, null, jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyJobTitle() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, "", jobYears, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_negativeJobYears() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, -7, email));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_invalidEmail() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, null));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testEquals() {
        //Arrange
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears, email);
        //Act + Assert
        assertTrue(employee.equals(employee1));
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
        String email = "abc@sapo.pt";
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears, email);
        //Act + Assert
        assertFalse(employee.equals(employee1));
    }

    @Test
    void testHashCode() {
        //Arrange
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears, email);
        //Act + Assert
        assertEquals(employee1.hashCode(), employee.hashCode());
    }

    @Test
    void testHashCode_notEmployee() {
        //Arrange
        String firstName = "Rose";
        String lastName = "Taylor";
        String description = "description";
        String jobTitle = "teacher";
        int jobYears = 7;
        String email = "abc@sapo.pt";
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears, email);
        //Act + Assert
        assertNotEquals(employee1.hashCode(), employee.hashCode());
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
    void setId_invalidId() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setId(null));
        assertEquals(expectedMessage, result.getMessage());
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
    void setFirstName_invalidFirstName() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setFirstName(null));
        assertEquals(expectedMessage, result.getMessage());
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
    void setLastName_invalidLastName() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setLastName(""));
        assertEquals(expectedMessage, result.getMessage());
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
    void setDescription_invalidDescription() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setDescription(null));
        assertEquals(expectedMessage, result.getMessage());
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
    void setJobTitle_invalidJobTitle() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setJobTitle(""));
        assertEquals(expectedMessage, result.getMessage());
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
    void setJobYears_negativeJobYears() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setJobYears(-1));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void getEmail() {
        //Arrange
        String expected = "email@sapo.pt";
        //Act
        String result = employee.getEmail();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void setEmail() {
        //Arrange
        String expected = "abc@sapo.pt";
        //Act
        employee.setEmail(expected);
        String result = employee.getEmail();
        //Assert
        assertEquals(expected, result);
    }
    @Test
    void setEmail_invalidEmail() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setEmail(""));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void setEmail_invalidEmail_withoutAt() {
        //Arrange
        String expectedMessage = "Invalid parameter.";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears, email).setEmail("email"));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testToString() {
        //Arrange
        employee.setId(1L);
        String expected = "Employee{id=1, firstName='Frodo', lastName='Baggins', description='ring bearer', jobTitle='jobtitle', jobYears='3', email='email@sapo.pt'}";
        //Act
        String result = employee.toString();
        //Assert
        assertEquals(expected, result);
    }
}