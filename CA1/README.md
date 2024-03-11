# CA1

## Introduction

The purpose of class assignment is to add functionalities to a program that keeps track of company's employes.
This tutorial will have the following structure:
1. Setup
2. Part I
3. Part II
4. Alternative technological solution for version control
5. Conclusion

## 1.Setup

Before starting part I, it is essential to prepare the local Git repository. It will be explained, step by step, how to proceed.

To download the specified GitHub repository to your local machine, creating a complete copy of its files and history for immediate use and exploration.

    git clone https://github.com/spring-guides/tut-react-and-spring-data-rest

Use the following command to remove the local Git repository directory in the current folder, deleting all version history, commits, and configurations without affecting the working files.

    rm -rf .git

Next we need to initialise a new Git repository in the current directory.

    git init

Now use the command below to stage the `README.md` file, preparing it to be included in the next commit to the Git repository.

    git add README.md

To create a first commit and capture the current state of staged changes in the Git repository:

    git commit -m "first commit"

After this rename the current branch to `main` with the next command.

    git branch -M main

To add a remote repository on GitHub to your local Git project enabling code push and pull:

    git remote add origin git@github.com:FranciscaNogueira/devops-23-24-JPE-PSM-1231829.git

Now you want to upload your local `main` branch changes to the remote repository named `origin` and sets it to track the upstream branch for future pushes and pulls.

    git push -u origin main

That’s it! Now you are ready to begin.

## 2.Part I

This section of the tutorial navigates us through implementing and managing a new feature for an application that combines React.js with Spring Data REST. We'll start by copying the code from an existing tutorial into a new directory, move on to committing changes in the Git repository, and finally mark our progress using version tags. The new feature will involve adding a field to record the employment duration of employees, which will be complemented by proper unit testing and subsequent verification on both client and server sides.

### 2.1. Copy the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1

Use the following command to create a new directory named `CA1` in the current location on your system.

    mkdir CA1

Now you can copy all contents from the `tut-react-and-spring-data-rest` directory to the `CA1` directory, preserving the original files' attributes and structure, with the command:

    cp -a tut-react-and-spring-data-rest/. CA1


### 2.2. Commit the changes (and push them)

First you have to stage all new and modified files in the current directory and all subdirectories for the next commit in your Git repository:

    git add .

After this you can create a snapshot of the staged changes with a descriptive message using the command below.

    git commit -m "New field to record the years of the employee in the company(JobYears) "

Now you just need to push your commit with:

    git push

### 2.3. We should use tags to mark the versions of the application. You should use a pattern like: major.minor.revision (e.g., 1.1.0).

#### 2.3.1. Tag the initial version as v1.1.0. Push it to the server.

To create a new tag in the Git repository, use:

    git tag v1.1.0

Now you want to upload the tag `v1.1.0` to the remote repository named `origin`, making the tag available to other users for reference or deployment purposes. Use the following command:

    git push origin v1.1.0

### 2.4. Lets develop a new feature to add a new field to the application. In this case, lets add a new field to record the years of the employee in the company (e.g., jobYears).

#### 2.4.1.  You should add support for the new field.

Go to your IDE.
- Add the attribute `jobYears` to the class `Employee` to look like below:

```
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
    	private String lastName;
    	private String description;
    	private String jobTitle;
    	private int jobYears;
    
    	private Employee() {}
    
    	public Employee(String firstName, String lastName, String description, String jobTitle, int jobYears) {
    		if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
    				description == null || description.isEmpty() || jobTitle == null || jobTitle.isEmpty() ||
    				jobYears < 0) {
    			throw new IllegalArgumentException("Invalid parameter(s)");
    		}
    		this.firstName = firstName;
    		this.lastName = lastName;
    		this.description = description;
    		this.jobTitle = jobTitle;
    		this.jobYears = jobYears;
    	}
    
    	@Override
    	public boolean equals(Object o) {
    		if (this == o) return true;
    		if (o == null || getClass() != o.getClass()) return false;
    		Employee employee = (Employee) o;
    		return Objects.equals(id, employee.id) &&
    			Objects.equals(firstName, employee.firstName) &&
    			Objects.equals(lastName, employee.lastName) &&
    			Objects.equals(description, employee.description) &&
    			Objects.equals(jobTitle,employee.jobTitle) &&
    			Objects.equals(jobYears,employee.jobYears);
    	}
    
    	@Override
    	public int hashCode() {
    
    		return Objects.hash(id, firstName, lastName, description, jobTitle, jobYears);
    	}
    
    	public Long getId() {
    		return id;
    	}
    
    	public void setId(Long id) {
    		if(id == null){
    			throw new IllegalArgumentException("Invalid parameter.");
    		}
    		this.id = id;
    	}
    
    	public String getFirstName() {
    		return firstName;
    	}
    
    	public void setFirstName(String firstName) {
    		if(firstName == null || firstName.isEmpty()){
    			throw new IllegalArgumentException("Invalid parameter.");
    		}
    		this.firstName = firstName;
    	}
    
    	public String getLastName() {
    		return lastName;
    	}
    
    	public void setLastName(String lastName) {
    		if(lastName == null || lastName.isEmpty()){
    			throw new IllegalArgumentException("Invalid parameter.");
    		}
    		this.lastName = lastName;
    	}
    
    	public String getDescription() {
    		return description;
    	}
    
    	public void setDescription(String description) {
    		if(description == null || description.isEmpty()){
    			throw new IllegalArgumentException("Invalid parameter.");
    		}
    		this.description = description;
    	}
    
    	public String getJobTitle() {
    		return jobTitle;
    	}
    
    	public void setJobTitle(String jobTitle) {
    		if(jobTitle == null || jobTitle.isEmpty()){
    			throw new IllegalArgumentException("Invalid parameter.");
    		}
    		this.jobTitle = jobTitle;
    	}
    
    	public int getJobYears() {
    		return jobYears;
    	}
    
    	public void setJobYears(int jobYears) {
    		if(jobYears < 0){
    			throw new IllegalArgumentException("Invalid parameter.");
    		}
    		this.jobYears = jobYears;
    	}
    
    	@Override
    	public String toString() {
    		return "Employee{" +
    			"id=" + id +
    			", firstName='" + firstName + '\'' +
    			", lastName='" + lastName + '\'' +
    			", description='" + description + '\'' +
    				", jobTitle='" + jobTitle + '\'' +
    				", jobYears='" + jobYears + '\'' +
    			'}';
    	}
    }

```

- Update the method `run` in the class `DatabaseLoader` to look like below:

```
@Override
	public void run(String... strings) throws Exception { // <4>
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer", "jobtitle", 3));
	}
```

- Now you need to update the react component `app.js` to render the jobYears in the table:

```
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
// end::vars[]

// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() { // <2>
		client({method: 'GET', path: '/api/employees'}).done(response => {
			this.setState({employees: response.entity._embedded.employees});
		});
	}

	render() { // <3>
		return (
			<EmployeeList employees={this.state.employees}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class EmployeeList extends React.Component{
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee._links.self.href} employee={employee}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Description</th>
						<th>Job Title</th>
						<th>Job Years</th>
					</tr>
					{employees}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobTitle}</td>
				<td>{this.props.employee.jobYears}</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
```

#### 2.4.2.  You should also add unit tests for testing the creation of Employees and the validation of their attributes (for instance, no null/empty values). For the new field, only integer values should be allowed.

- Now you can do some tests like these:

```
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
    void testConstructor_nullFirstName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(null, lastName, description, jobTitle, jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyFirstName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee("", lastName, description, jobTitle, jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_nullLastName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, null, description, jobTitle, jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyLastName() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, "", description, jobTitle, jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_nullDescription() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, null, jobTitle, jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyDescription() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, "", jobTitle, jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_nullJobTitle() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, null, jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_emptyJobTitle() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, "", jobYears));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testConstructor_negativeJobYears() {
        //Arrange
        String expectedMessage = "Invalid parameter(s)";
        //Act + Assert
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, -7));
        assertEquals(expectedMessage, result.getMessage());
    }

    @Test
    void testEquals() {
        //Arrange
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears);
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
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears);
        //Act + Assert
        assertFalse(employee.equals(employee1));
    }

    @Test
    void testHashCode() {
        //Arrange
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears);
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
        Employee employee1 = new Employee(firstName, lastName, description, jobTitle, jobYears);
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
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears).setId(null));
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
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears).setFirstName(null));
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
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears).setLastName(""));
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
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears).setDescription(null));
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
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears).setJobTitle(""));
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
        Exception result = assertThrows(IllegalArgumentException.class, () -> new Employee(firstName, lastName, description, jobTitle, jobYears).setJobYears(-1));
        assertEquals(expectedMessage, result.getMessage());
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
```

#### 2.4.3. You should debug the server and client parts of the solution.

Go to the terminal of your computer and access your project directory with the `cp` command.
After that, execute the following command:

```
./mvnw spring-boot:run
```

Once the application is running, you can now proceed with the debug.

##### 2.4.3.1. To debug the backend

Open another terminal and execute the following command:

```
curl localhost:8080/api/employees
```

Now you should see the following output :

```
{
	"_embedded" : {
		"employees" : [ {
			"firstName" : "Frodo",
			"lastName" : "Baggins",
			"description" : "ring bearer",
			"jobTitle" : "jobtitle",
			"jobYears" : 3,
			"_links" : {
				"self" : {
					"href" : "http://localhost:8080/api/employees/1"
				},
				"employee" : {
					"href" : "http://localhost:8080/api/employees/1"
				}
			}
		} ]
	},
	"_links" : {
		"self" : {
			"href" : "http://localhost:8080/api/employees"
		},
		"profile" : {
			"href" : "http://localhost:8080/api/profile/employees"
		}
	}
}
```

Check that all the parameters appear as expected.

##### 2.4.3.2. To debug the frontend

Open your browser and go to the URL http://localhost:8080.

You should be able to see the UI where the employee information is displayed in a table like the following:

| First Name | Last Name | Description | Job Title | Job Years |
|------------|-----------|-------------|-----------|-----------|
| Frodo      | Baggins   | ring bearer | jobtitle  | 3         |

#### 2.4.4.  When the new feature is completed (and tested) the code should be committed and pushed and a new tag should be created (e.g, v1.2.0).

Now execute the following commands as explained previously.

```
git add .
```
```
git commit -m " CA1 completed."  
```
```
git push  
```
```
git tag v1.1.0
```
```
git push origin v1.2.0
```

### 2.5. At the end of the assignment mark your repository with the tag ca1-part1.

```
git tag ca1-part1 
```
```
git push origin ca1-part1 
```

## 3. Part II

This segment of the tutorial addresses adding and correcting features in a React.js and Spring Data REST application, emphasizing the use of specific branches for isolated development. Through a practical example, we introduce the implementation and validation of a new email field, complemented by rigorous unit testing. We conclude with the merging of changes into the master branch and tagging the repository with version tags, thus promoting a robust codebase and facilitating project collaboration and management.

### 3.1.  You should use the master branch to ”publish” the ”stable” versions of the Tutorial React.js and Spring Data REST Application.

### 3.2.  You should develop new features in branches named after the feature. Create a branch named ”email-field” to add a new email field to the application.

#### 3.2.1.  You should create a branch called email-field.

Use the command below to create a new branch named `email-field` in the Git repository, allowing for the development of features or fixes related to the email field in isolation from the main branch.

```
git branch email-field
```

Now you have to switch your current working directory to the `email-field` branch, making it the active branch for development and commits.

```
git checkout email-field
```

#### 3.2.2. You should add support for the email field.

Go to your IDE.
- Add the attribute `email` to the class `Employee` to look like below:

```
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private String jobTitle;
	private int jobYears;
	private String email;

	private Employee() {}

	public Employee(String firstName, String lastName, String description, String jobTitle, int jobYears, String email) {
		if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
				description == null || description.isEmpty() || jobTitle == null || jobTitle.isEmpty() ||
				jobYears < 0 || email == null ||email.isEmpty()) {
			throw new IllegalArgumentException("Invalid parameter(s)");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobTitle = jobTitle;
		this.jobYears = jobYears;
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description) &&
			Objects.equals(jobTitle,employee.jobTitle) &&
			Objects.equals(jobYears,employee.jobYears) &&
			Objects.equals(email, employee.email);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description, jobTitle, jobYears, email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id == null){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName == null || firstName.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName == null || lastName.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description == null || description.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.description = description;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		if(jobTitle == null || jobTitle.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.jobTitle = jobTitle;
	}

	public int getJobYears() {
		return jobYears;
	}

	public void setJobYears(int jobYears) {
		if(jobYears < 0){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.jobYears = jobYears;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email == null || email.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
				", jobTitle='" + jobTitle + '\'' +
				", jobYears='" + jobYears + '\'' +
				", email='" + email + '\'' +
			'}';
	}
}
```

- Update the method `run` in the class `DatabaseLoader` to look like below:

```
@Override
	public void run(String... strings) throws Exception { // <4>
		this.repository.save(new Employee("Frodo", "Baggins", "ring bearer", "jobtitle", 3, "email@sapo.pt"));
	}
```

- Now you need to update the react component `app.js` to render the jobYears in the table:

```
const React = require('react'); // <1>
const ReactDOM = require('react-dom'); // <2>
const client = require('./client'); // <3>
// end::vars[]

// tag::app[]
class App extends React.Component { // <1>

	constructor(props) {
		super(props);
		this.state = {employees: []};
	}

	componentDidMount() { // <2>
		client({method: 'GET', path: '/api/employees'}).done(response => {
			this.setState({employees: response.entity._embedded.employees});
		});
	}

	render() { // <3>
		return (
			<EmployeeList employees={this.state.employees}/>
		)
	}
}
// end::app[]

// tag::employee-list[]
class EmployeeList extends React.Component{
	render() {
		const employees = this.props.employees.map(employee =>
			<Employee key={employee._links.self.href} employee={employee}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Description</th>
						<th>Job Title</th>
						<th>Job Years</th>
						<th>Email</th>
					</tr>
					{employees}
				</tbody>
			</table>
		)
	}
}
// end::employee-list[]

// tag::employee[]
class Employee extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.employee.firstName}</td>
				<td>{this.props.employee.lastName}</td>
				<td>{this.props.employee.description}</td>
				<td>{this.props.employee.jobTitle}</td>
				<td>{this.props.employee.jobYears}</td>
				<td>{this.props.employee.email}</td>
			</tr>
		)
	}
}
// end::employee[]

// tag::render[]
ReactDOM.render(
	<App />,
	document.getElementById('react')
)
```

#### 3.2.3. You should also add unit tests for testing the creation of Employees and the validation of their attributes (for instance, no null/empty values).

- Now you can do some tests like these:

```
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
```

#### 3.2.4. You should debug the server and client parts of the solution.

Go to the terminal of your computer and access your project directory with the `cp` command.
After that, execute the following command:

```
./mvnw spring-boot:run
```

Once the application is running, you can now proceed with the debug.

##### 3.2.4.1 To debug the backend

Open another terminal and execute the following command:

```
curl localhost:8080/api/employees
```

Now you should see the following output :

```
{
	"_embedded" : {
		"employees" : [ {
			"firstName" : "Frodo",
			"lastName" : "Baggins",
			"description" : "ring bearer",
			"jobTitle" : "jobtitle",
			"jobYears" : 3,
			"email" : "email@sapo.pt",
			"_links" : {
				"self" : {
					"href" : "http://localhost:8080/api/employees/1"
				},
				"employee" : {
					"href" : "http://localhost:8080/api/employees/1"
				}
			}
		} ]
	},
	"_links" : {
		"self" : {
			"href" : "http://localhost:8080/api/employees"
		},
		"profile" : {
			"href" : "http://localhost:8080/api/profile/employees"
		}
	}
}
```

Check that all the parameters appear as expected.

##### 2.4.3.2. To debug the frontend

Open your browser and go to the URL http://localhost:8080.

You should be able to see the UI where the employee information is displayed in a table like the following:

| First Name | Last Name | Description | Job Title | Job Years | Email         |
|------------|-----------|-------------|-----------|-----------|---------------|
| Frodo      | Baggins   | ring bearer | jobtitle  | 3         | email@sapo.pt |


#### 3.2.5. When the new feature is completed (and tested) the code should be merged with the master and a new tag should be created (e.g, v1.3.0).

First commit and push your code as below:

```
git add .
```

```
git commit -m "Added email field"
```
```
git push
```

To send the commits from the local branch `email-field` to the remote repository, setting it as the upstream branch on the `origin` remote, thus establishing a tracking relationship between the local and remote branches.

```
git push --set-upstream origin email-field\
```

Now to integrate changes from the `email-field` branch into the current branch, merging the histories of both branches into a single development timeline, execute the following command:

```
git merge email-field
```

After this you can execute the following commands as explained before.

```
git tag v1.3.0
```

```
git push origin v1.3.0
```

### 3.3.  You should also create branches for fixing bugs (e.g., ”fix-invalid-email”).

#### 3.3.1. Create a branch called fix-invalid-email. The server should only accept Employees with a valid email (e.g., an email must have the ”@” sign).

As before you can execute the commands below:

```
git branch fix-invalid-email
```

```
git checkout fix-invalid-email
```

After this go to your IDE.
-   Update the class  `Employee`  to look like below:

```
@Entity // <1>
public class Employee {

	private @Id @GeneratedValue Long id; // <2>
	private String firstName;
	private String lastName;
	private String description;
	private String jobTitle;
	private int jobYears;
	private String email;

	private Employee() {}

	public Employee(String firstName, String lastName, String description, String jobTitle, int jobYears, String email) {
		if(firstName == null || firstName.isEmpty() || lastName == null || lastName.isEmpty() ||
				description == null || description.isEmpty() || jobTitle == null || jobTitle.isEmpty() ||
				jobYears < 0 || email == null || !email.contains("@")) {
			throw new IllegalArgumentException("Invalid parameter(s)");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.description = description;
		this.jobTitle = jobTitle;
		this.jobYears = jobYears;
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Employee employee = (Employee) o;
		return Objects.equals(id, employee.id) &&
			Objects.equals(firstName, employee.firstName) &&
			Objects.equals(lastName, employee.lastName) &&
			Objects.equals(description, employee.description) &&
			Objects.equals(jobTitle,employee.jobTitle) &&
			Objects.equals(jobYears,employee.jobYears) &&
			Objects.equals(email, employee.email);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, firstName, lastName, description, jobTitle, jobYears, email);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		if(id == null){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName == null || firstName.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName == null || lastName.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.lastName = lastName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		if(description == null || description.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.description = description;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		if(jobTitle == null || jobTitle.isEmpty()){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.jobTitle = jobTitle;
	}

	public int getJobYears() {
		return jobYears;
	}

	public void setJobYears(int jobYears) {
		if(jobYears < 0){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.jobYears = jobYears;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(email == null || !email.contains("@")){
			throw new IllegalArgumentException("Invalid parameter.");
		}
		this.email = email;
	}

	@Override
	public String toString() {
		return "Employee{" +
			"id=" + id +
			", firstName='" + firstName + '\'' +
			", lastName='" + lastName + '\'' +
			", description='" + description + '\'' +
				", jobTitle='" + jobTitle + '\'' +
				", jobYears='" + jobYears + '\'' +
				", email='" + email + '\'' +
			'}';
	}
}
```

#### 3.3.2. You should debug the server and client parts of the solution.

Follow the same steps in  "3.2.4. You should debug the server and client parts of the solution.".

#### 3.3.3.  When the fix is completed (and tested) the code should be merged into master and a new tag should be created (with a change in the minor number, e.g., v1.3.0 -> v1.3.1)

Now you can do some tests like these:

```
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
}
```

Now you can execute the following commands (already explained):

```
git add .
```

```
git commit -m "fix email validations"
```

```
git push
```

```
git push --set-upstream origin fix-invalid-email
```

```
git checkout main
```

```
git merge fix-invalid-email
```

```
git push
```

```
git tag v1.3.1
```

```
git push origin v1.3.1
```

### 3.4.  At the end of the assignment mark your repository with the tag ca1-part2.

```
git tag ca1-part2 
```
```
git push origin ca1-part2 
```

## 4. Alternative  technological solution for version  control

### 4.1. Mercurial (introduction)

The Mercurial system is an open-source and free version control system. It is known for its ease of use and intuitive nature, making it suitable for projects of various sizes without compromising its remarkable performance. Mercurial allows each clone to contain the complete history of the project, which facilitates actions to be performed locally, resulting in increased speed and convenience for the user.

With this system, version control becomes a simple task. It allows for the efficient reversal of changes by creating new modifications in the repository that negate the originals, or even by rewriting the history to render the changes imperceptible. Additionally, for code that has not yet been subjected to versioning, Mercurial eases the rollback process.

Similar to Git, Mercurial supports the creation and management of branches, which enables parallel and organized development. Its implementation was designed to ensure independence from specific platforms, having been primarily developed in Python. This system can be expanded through extensions and customisations, allowing its users to adapt it to the unique requirements of each project.

### 4.2. Mercurial vs Git

#### 4.2.1. Mercurial pros:
- Easier to understand;
- More intuitive command line interface

#### 4.2.2. Git pros:
- Has a big community;
- Most used in industry;
- More flexible;
- Web interface with more features;

The information found does not allow me to guarantee which of the two is faster.

### 4.3. Some tools

#### 4.3.1. Setup

First you have to clone your repository. Since Mercurial does not directly support Git repositories, it's necessary first to convert the repository, using the following command:
```
hg-git
```

Next we need to initialise a new Mercurial repository in the current directory.
```
hg init
```

Now use the command below to stage the `README.md` file, preparing it to be included in the next commit to the Mercurial repository.

```
hg add README.md
```

To create a first commit and capture the current state of staged changes in the Mercurial repository:

```
hg commit -m "first commit"
```

After this rename the current branch to `main` with the next command.
- In Mercurial, branch management is different from Git, and the concept of renaming the current branch to `main` doesn't apply directly. Mercurial uses "bookmarks" to mark certain commits as points of interest, similar to branches in Git. Take this step forward.

Adding a remote repository to your local Mercurial project, enabling code push and pull, varies depending on the hosting service. If you're using a server that supports Mercurial (like Bitbucket), you would configure a path in your `.hg/hgrc` file like this:

```
[paths]  default = <yours Mercurial repository URL>
```

Finally, to upload your local changes to the remote repository, use:

```
hg push
```

#### 4.3.2. Part I

In order not to make this section too exhaustive, I will only mention the commands used once, and may not strictly follow the commands in Part I

##### 4.3.2.1. Copy the code of the Tutorial React.js and Spring Data REST Application into a new folder named CA1

Use the following command to create a new directory named `CA1` in the current location on your system.

```
mkdir CA1
```

Now you can copy all contents from the `tut-react-and-spring-data-rest` directory to the `CA1` directory, preserving the original files' attributes and structure, with the command:

```
cp -a tut-react-and-spring-data-rest/. CA1
```

##### 4.3.2.2. Commit the changes (and push them)

First you have to stage all new and modified files in the current directory and all subdirectories for the next commit in your Mercurial repository:

```
hg add .
```

After this you can create a snapshot of the staged changes with a descriptive message using the command below.

```
hg commit -m "New field to record the years of the employee in the company(JobYears)"
```

Now you just need to push your commit with:

```
hg push
```

##### 4.3.2.3. Tag the initial version as v1.1.0. Push it to the server.

To create a new tag in the Mercurial repository, use:

```
hg tag v1.1.0
```

Now you want to upload the tag `v1.1.0` to the remote repository, making the tag available to other users for reference or deployment purposes. Use the following command:

```
hg push
```

#### 4.3.3. Part II

In order not to make this section too exhaustive, I will only mention the commands used once, and may not strictly follow the commands in Part II.

##### 4.3.3.1.  You should create a branch called email-field.

Use the command below to create a new branch named `email-field` in the Mercurial repository, allowing for the development of features or fixes related to the email field in isolation from the main branch.

```
hg branch email-field
```

Now you have to switch your current working directory to the `email-field` branch, making it the active branch for development and commits.

```
hg update email-field
```

##### 4.3.3.2. When the new feature is completed (and tested) the code should be merged with the master and a new tag should be created (e.g, v1.3.0).

First commit and push your code as below:

```
hg add .
```

```
hg commit  -m "Added email field"
```

```
hg push
```

To send the commits from the local branch `email-field` to the remote repository.

```
hg update default
```

Now to integrate changes from the `email-field` branch into the current branch, merging the histories of both branches into a single development timeline, execute the following command:

```
hg merge email-field
```


### 4.4. Bibliography

https://www.mercurial-scm.org/ consulted on March 10, 2023

http://hgbook.red-bean.com/ consulted on March 10, 2023

https://git-scm.com/book/en/v2 consulted on March 10, 2023


## 5.Conclusion

This class assignment highlighted the importance of version control with Git and web development using React.js and Spring Data REST, highlighting the need for code organization, unit testing and release management.
Using branches and tags to implement new features and corrections, it was possible to simulate a collaborative software development environment.
By searching for alternatives to Git, it was possible to explore Mercurial, highlighting its simplicity, as well as some advantages and disadvantages in relation to Git.
With this analysis it was also possible to explore the differences in the commands used by the two version control tools.