# CA2-Part2

## Maven as an alternative technological solution for the build automation tool

### 1. Maven (introduction)
Maven is a powerful build automation tool mainly used for Java projects. It was designed to simplify the project 
construction process through the use of an XML-based configuration model. Additionally, it facilitates dependency management 
and integration with development tools to improve software lifecycle efficiency.

### 2. Maven vs Gradle
Maven and Gradle are build automation tools widely used in Java project management, each offering distinct features that 
can influence the choice between one or the other, depending on the needs of the project.

#### 2.1. Performance

The three main features that make Gradle at least twice as fast as Maven are:
- Incrementality: Gradle minimizes work by monitoring the inputs and outputs of tasks, executing only what is necessary 
- and processing only files that have changed, whenever possible.
- Build Cache: Reuses the results of previous builds from any other project using Gradle with the same inputs, including 
- across different machines.
- Gradle Demon: A long-running process that keeps build information active in memory.

#### 2.2. Dependency Management

Gradle and Maven build systems resolve configurable repository dependencies by storing and unloading them in parallel. 
Gradle stands out for allowing custom dependency replacement rules and supporting composite builds, unlike Maven which 
has limitations on dependency scopes and conflict resolution. Gradle also offers best practices for library producers, 
allowing you to control the exposure of dependencies in consumer classpaths, functionality that Maven supports only as 
documentation.

#### 2.3. Easy to use and plugin Ecosystem

For those just starting out, Maven can prove to be a challenging tool, especially due to the complexity of its pom.xml file,
which is generally difficult to understand. Additionally, Maven has a limited set of plugins and extensions, thus limiting 
customization of the build process beyond standard behaviors. Despite having an extensive and consolidated plugin ecosystem, 
customizing build logic in Maven often requires the creation of specific plugins or the use of complex XML configurations.

In contrast, Gradle stands out as a more accessible and easier-to-learn tool. Gradle's build file is much more succinct 
and simpler to understand when compared to Maven's pom.xml. Additionally, Gradle benefits from a wide range of plugins 
and extensions, making it easy to adapt the build process to the specific needs of each project. Its DSL in Groovy or Kotlin 
allows for effective customization of build logic without the need to develop dedicated plugins, making it an attractive 
choice for many developers.

### 3. Some tools

This tutorial demonstrates how to set up and manage a basic project using Maven instead of Gradle,  focusing on similar  
steps but adapting them for the Maven build system.

#### 3.1. Extract the generated zip file inside the folder ”CA2/Part2/” of your repository.

To compile the project's source code, ensuring the code is syntactically correct before further build phases, run the following command:

```
mvn compile
```

#### 3.2. Experiment with the application using Maven

Run the Spring Boot application:

```
mvn spring-boot:run
```

#### 3.3. Add a Maven plugin for managing the frontend

Add the following plugin in `pom.xml` under `<build><plugins>` :

```xml
<plugin>
    <groupId>com.github.eirslett</groupId>
    <artifactId>frontend-maven-plugin</artifactId>
    <version>1.9.1</version>
    <configuration>
        <installDirectory>target</installDirectory>
    </configuration>
    <executions>
        <execution>
            <id>install node and npm</id>
            <goals>
                <goal>install-node-and-npm</goal>
            </goals>
            <configuration>
                <nodeVersion>v12.14.0</nodeVersion>
                <npmVersion>6.13.4</npmVersion>
            </configuration>
        </execution>
        <execution>
            <id>npm install</id>
            <goals>
                <goal>npm</goal>
            </goals>
            <configuration>
                <arguments>install</arguments>
            </configuration>
        </execution>
        <execution>
            <id>webpack build</id>
            <goals>
                <goal>webpack</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

This plugin allows us to install node and npm and install frontend dependencies through npm.
It also allows us to bundle javascript files for usage in the browser.


#### 3.4. Update the scripts section/object in package.json to configure the execution of webpack

Add in package.json:

```json
"packageManager": "npm@9.6.7",
"scripts": {
  "webpack": "webpack",
  "build": "npm run webpack",
  "check": "echo Checking frontend",
  "clean": "echo Cleaning frontend",
  "lint": "echo Linting frontend",
  "test": "echo Testing frontend"
},
```

#### 3.6. You can now execute `mvn package`

Use the command `mvn package` to compile the project and generate the distribution package, in addition to running tests 
and conducting code quality checks as specified in the project configuration.

#### 3.7. You may now execute the application

To do it, use the command below:

```
mvn spring-boot:run
```

#### 3.8.  Add a task to Maven to copy the generated jar to a folder named "dist" located at the project root level

Add the following plugin configuration under `<build><plugins>` in `pom.xml` to handle resource copying.
This plugin is configured to execute during the package phase. To run it, use the command `mvn package`.

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <version>3.2.0</version>
    <executions>
        <execution>
            <id>copy-jar</id>
            <phase>package</phase>
            <goals>
                <goal>copy-resources</goal>
            </goals>
            <configuration>
                <outputDirectory>${project.basedir}/dist</outputDirectory>
                <resources>
                    <resource>
                        <directory>${project.build.directory}</directory>
                        <includes>
                            <include>*.jar</include>
                        </includes>
                    </resource>
                </resources>
            </configuration>
        </execution>
    </executions>
</plugin>
```


#### 3.9. Add a task to Maven to delete all the files generated by webpack

Add the `maven-clean-plugin` plugin configuration under `<build><plugins>` in `pom.xml`.
The provided example includes a custom configuration to delete files generated by webpack.

The plugin is configured to execute during the clean phase. To run it, use the command `mvn clean`.

```xml
<plugin>
    <artifactId>maven-clean-plugin</artifactId>
    <version>3.1.0</version>
    <configuration>
        <filesets>
            <fileset>
                <directory>${project.basedir}/src/main/resources/static/built/</directory>
                <includes>
                    <include>**/*</include>
                </includes>
            </fileset>
        </filesets>
    </configuration>
</plugin>
```

#### 4. Bibliography

[https://spring.io/guides/gs/maven](https://spring.io/guides/gs/maven)
[https://loudbench.com/maven-vs-gradle/](https://loudbench.com/maven-vs-gradle/)
[https://gradle.org/maven-vs-gradle/](https://gradle.org/maven-vs-gradle/)
[https://tomgregory.com/gradle/maven-vs-gradle-comparison/](https://tomgregory.com/gradle/maven-vs-gradle-comparison/)

