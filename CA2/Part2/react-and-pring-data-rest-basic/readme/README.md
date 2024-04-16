# CA2-Part2

## Introduction

This tutorial serves as a step-by-step guide for configuring and managing a Spring Boot project using Gradle.
This tutorial will have the following structure:
1. [Part II](#1-part-ii)
2. [Alternative technological solution for the build automation tool](#2-alternative-technological-solution-for-the-build-automation-tool)
3. [Conclusion](#3-conclusion)

## 1. Part II

This section spans the creation of a new branch in your repository to the setup of a Spring Boot project with Gradle, 
including the management of dependencies and build tasks. Emphasizing hands-on instructions, it guides readers through 
structuring their project, running tests, and applying continuous integration, thus offering a comprehensive insight 
into preparing and optimizing the development environment for Spring Boot projects with Gradle.

### 1.1. In your repository create a new branch called tut-basic-gradle

First you need to go to your GitHub repository with the command below:

```
cd <path for your repository>
```

Now you can create your new branch using:

```
git  branch  tut-basic-gradle
```

Once you have your branch created you are ready to move into it, with the following command.

```
git  checkout  tut-basic-gradle
```

### 1.2. Start a new gradle spring boot project

You only need to start your new gradle spring boot project as shown in CA2Part2 requirements.

### 1.3. Extract the generated zip file inside the folder ”CA2/Part2/” of your repository

First you need to move into your CA2 directory, using:

```
cd  CA2
```

Now you are ready to create a new directory.

```
mkdir  Part2
```

Move into your `Part2`:

```
cd  Part2
```

At this time you can extract the generated zip file inside the folder `CA2/Part2/`of your repository.

```
cp  -r  ~/Downloads/react-and-pring-data-rest-basic  .
```

To download and execute gradle first you need to move into react-and-pring-data-rest-basic.

```
cd  react-and-pring-data-rest-basic
```

Now you should run the following command:

```
./gradlew  tasks 
```

![(.:gradlew tasks) part1.png](%28.%3Agradlew%20tasks%29%20part1.png)
![(.:gradlew tasks) part2.png](%28.%3Agradlew%20tasks%29%20part2.png)

Move to your repository root to commit and push with the following commands:

```
cd  ../../../

git  add  .

git  commit  -m  " closes #9 added empty gradle spring project to repository"

git  push  origin  tut-basic-gradle
```

### 1.4. Delete the src folder

Move to the basic folder.

```
cd  CA2/Part2/react-and-pring-data-rest-basic
```

Now remove the src folder with the command below:

```
rm  -r  src
```

You are ready to commit and push your changes.

```
git  add  .

git  commit  -m  "closes #10 removed src"

git  push  --set-upstream  origin  tut-basic-gradle
```

You need to use the command `git push --set-upstream origin tut-basic-gradle` to send local changes in the 
`tut-basic-gradle` branch to the remote repository named `origin` and sets it as the default upstream branch for 
future push and pull operations.

### 1.5. Copy the src folder (and all its subfolders) from the basic folder of the tutorial into this new folder

To copy all the folders use the following commands:

```
cp  -r  ../../../CA1/basic/src  .

cp  -r  ../../../CA1/basic/webpack.config.js  .

cp  -r  ../../../CA1/basic/package.json  .
```

To delete the folder src/main/resources/static/built/ that is generated from the javascrit by the webpack tool, 
use the command below.

```
rm  -r  src/main/resources/static/built
```

Now you can commit and push your changes:

```
git  add  .

git  commit  -m  "closes #11 - copy src folder and webpack config from previous tutorial"

git  push
```

### 1.6. You can now experiment with the application by using ./gradlew bootRun.

After you run the command `./gradlew  bootRun` you should be able to see:

![(.:gradlew bootRun) 6..png](%28.%3Agradlew%20bootRun%29%206..png)


### 1.7. and 1.8. We will add the gradle plugin org.siouan.frontend to the project so that gradle is also able to manage 
the frontend

Add the following plugin in `build.gradle`

```
id("org.siouan.frontend-jdk17") version "8.0.0"
```

### 1.9. Add also the following code in build.gradle to configure the previous plug-in:

Add the following code in `build.gradle` .

```groovy
frontend  {
nodeVersion  =  "16.20.2"
assembleScript  =  "run build"
cleanScript  =  "run clean"
checkScript  =  "run check"
}
```

Now you can commit and push the changes:

```
git  add  .

git  commit  -m  "#12 Setup plugin siouan frontend in build.gradle"

git  push
```

### 1.10. Update the scripts section/object in package.json to configure the execution of webpack

Add in package.json:

```json
"packageManager":  "npm@9.6.7",
"scripts":  {
"webpack":  "webpack",
"build":  "npm run webpack",
"check":  "echo Checking frontend",
"clean":  "echo Cleaning frontend",
"lint":  "echo Linting frontend",
"test":  "echo Testing frontend"
},
```

Now you can commit and push the changes:

```
git  add  .

git  commit  -m  "#12 Configure webpack scripts"

git  push
```

### 1.11. You can now execute ./gradlew build

Use the command `./gradlew build` to run the Gradle Wrapper script, which compiles the project and generates the 
distribution package, in addition to running tests and conducting code quality checks as specified in the project configuration.

Now you can commit and push the changes:

```
git  add  .

git  commit  -m  "Closes #12 - gradle build"

git  push
```

### 1.12. You may now execute the application by using ./gradlew bootRun

After run the command `./gradlew  bootRun`you will see:

![(.:gradlew bootRun) 12..png](%28.%3Agradlew%20bootRun%29%2012..png)

### 1.13. Add a task to gradle to copy the generated jar to a folder named ”dist” located a the project root folder level

Add in `build.gradle`:

```groovy
task  copyJar(type: Copy,  dependsOn:  classes) {
from  'build/libs'
into  'dist'
}
```

To execute, run the following command:

```
./gradlew  copyJar 
```

![(.:gradlew copyJar) 13..png](%28.%3Agradlew%20copyJar%29%2013..png)

Now you can commit and push the changes:

```
git  add  .

git  commit  -m  "#13 Add a task to gradle to copy the generated jar to a folder named dist"

git  push
```

### 1.14. Add a task to gradle to delete all the files generated by webpack

Add task and dependency for clean to the build.gradle file:

```groovy
task  deleteFilesGenByWebpack(type: Delete) {
delete  'src/main/resources/static/built/'
}

clean.dependsOn  deleteFilesGenByWebpack
```

Now you can execute the following command:

```
./gradlew  clean 
```

![(.:gradlew clean ) 14..png](%28.%3Agradlew%20clean%20%29%2014..png)

Commit and push your changes:

```
git  add  .

git  commit  -m  "closes #13 Add a task to gradle to delete all the files generated by webpack"

git  push
```

### 1.15. Commit your code and merge with the master branch

To merge with the master branch you can you use the commands below:

```
git  checkout  main

git  merge  --no-ff  tut-basic-gradle

git  push
```

### 1.16. At the end of part 2 of this assignment mark your repository with the tag ca2-part2

Go to your repository root and run the following commands:

```
git tag ca2-part2
git push origin ca2-part2
```

## 2. Alternative technological solution for the build automation tool

### 2.1.Bazel (introduction)

For this assignment, we will consider Bazel as an alternative build automation tool to Gradle. Bazel, developed by Google, 
offers a distinct approach to build automation, especially notable for its efficiency in handling large, multi-language 
projects and its emphasis on reproducibility and incremental builds.

### 2.2. Bazel vs Gradle

#### 2.2.1. Comparison with Gradle on Build Automation Features

**Build Automation Features:**

-   **Performance and Scalability:** Bazel excels in performance and scalability. It uses advanced caching and parallel 
- execution to ensure that builds are incremental, meaning only changes and their dependencies are rebuilt. This can 
- lead to faster build times compared to Gradle, especially in large projects.
-   **Extensibility:** Both Gradle and Bazel can be extended with new functionality. Gradle does this through plugins 
- and tasks, which can be written in Groovy or Kotlin. Bazel allows for extensibility through custom rules and macros 
- written in a Python-like syntax called Starlark. While Gradle has a vast ecosystem of plugins for various tasks, 
- Bazel's approach to extensibility is more low-level, giving developers the ability to deeply customize their build logic.

**Extending with New Functionality:**

-   **Gradle:** Extending Gradle is primarily done through plugins, which can add pre-defined tasks or new task types. 
- The Gradle plugin ecosystem is extensive, providing solutions for many common build requirements.
-   **Bazel:** Bazel's extension mechanism involves defining new build rules or macros in Starlark. This allows for a 
- high degree of customization and optimization but may require a deeper understanding of the build system's internals.

#### 2.2.2. Design of Solution Using Bazel to Achieve the Same Goals

To achieve the same goals as outlined for the assignment with Bazel, we would follow a design approach focused on 
defining a workspace, writing BUILD files, and leveraging Bazel's rules and targets for Java and Spring Boot applications.

-   **Workspace Configuration:** First, define a Bazel workspace using a `WORKSPACE` file at the root of the project. 
- This file identifies the directory and its contents as a Bazel workspace and can specify external dependencies required 
- for the build.

-   **BUILD Files:** For each module or component of the Spring Boot project, create BUILD files. These files specify 
- the build rules to apply, such as `java_binary` for compiling Java applications or custom rules for packaging 
- Spring Boot applications.

-   **Leveraging Bazel Rules:** Use Bazel's native rules for Java, such as `java_library` for building Java libraries 
- and `java_binary` for creating executable JAR files. For Spring Boot, custom build rules may be necessary to package 
- the application correctly, including dependencies and resource files.

-   **Dependencies Management:** Define external dependencies in the `WORKSPACE` file using Bazel's dependency management 
- system. Bazel ensures that all dependencies are correctly versioned and cached for reproducibility.

-   **Building and Running:** With Bazel, the build process would involve running commands like `bazel build //path/to:target` 
- to compile the project and `bazel run //path/to:target` for execution. Bazel takes care of dependency resolution, compilation, 
- and packaging based on the instructions in BUILD files and the `WORKSPACE` configuration.


In conclusion, while Bazel and Gradle differ in their approach to build automation, both tools are capable of handling 
complex, multi-language projects. Bazel's emphasis on performance, reproducibility, and scalability makes it a compelling 
alternative to Gradle, especially for projects that benefit from incremental builds and a high degree of customization 
in their build process.

### 2.3. Some tools

This tutorial demonstrates how to set up and manage a basic project using Bazel instead of Gradle, focusing on similar 
steps but adapting them for the specific tools and commands of the Bazel build system.

#### 2.3.1. Extract the generated zip file inside the folder "CA2/Part2/" of your repository

To view tasks:

```
bazel query //...
```

#### 2.3.2. Experiment with the application using Bazel

Run the Spring Boot application:

```
bazel run //src/main:app
``` 

#### 2.3.3. Add a Bazel rule to manage the frontend

Create a new Bazel build rule in your Build file:

```python
load("@npm_bazel_typescript//:index.bzl", "ts_project")

ts_project(
    name = "frontend",
    srcs = glob(["**/*.ts"]),
    deps = ["@npm//react", "@npm//react-dom"],
)
```

#### 2.3.4. Configure the Bazel build for frontend management

Configure the bazel build for frontend management:

```python
filegroup(
    name = "resources",
    srcs = glob(["src/main/resources/**"]),
)

ts_project(
    name = "frontend_build",
    srcs = glob(["src/**/*.ts", "src/**/*.tsx"]),
    tsconfig = ":tsconfig.json",
    deps = [":react_lib"],
)
```

#### 2.3.5. Update scripts in package.json for frontend execution

Modify the package.json to include custom Bazel commands:

```json
{
    "scripts": {
        "build": "bazel build //src/main:app",
        "test": "bazel test //src:all",
        "start": "bazel run //src/main:app"
    }
}
```

#### 2.3.6. Build the application with Bazel

Execute the Bazel build:

```
bazel build //src/main:app
```

#### 2.3.7. Add a Bazel rule to copy the generated jar to a folder named "dist"

Create a Bazel rule in your BUILD file:

```python
genrule(
    name = "copy_jar",
    srcs = ["//src/main:app"],
    outs = ["dist/app.jar"],
    cmd = "cp $(SRCS) $(OUTS)",
)
```

Execute the rule:

```
bazel run //:copy_jar
```

#### 2.3.8. Add a Bazel rule to delete all files generated by webpack

Add the following rule in your BUILD file:

```python
sh_binary(
    name = "clean_webpack",
    srcs = ["clean.sh"],
    data = glob(["src/main/resources/static/built/**"]),
)
```

The `clean.sh` script should contain:

```bash
#!/bin/bash
rm -rf $1
```

Execute the clean command:

```
bazel run //:clean_webpack
```

### 2.4. Bibliography

[https://docs.gradle.org](https://docs.gradle.org)
[https://bazel.build](https://bazel.build/)
[https://bazel.build/docs/user-manual](https://bazel.build/docs/user-manual)
[https://www.sabre.com/insights/delivering-software-faster-is-bazel-the-best-build-tool-for-monorepos/]
(https://www.sabre.com/insights/delivering-software-faster-is-bazel-the-best-build-tool-for-monorepos/)
[https://slaptijack.com/programming/bazel-vs-gradle-vs-maven.html](https://slaptijack.com/programming/bazel-vs-gradle-vs-maven.html)

## 3. Conclusion

This tutorial has showcased the capabilities and flexibility of Gradle and Bazel in managing Spring Boot projects, 
emphasizing the optimization of project structures for performance and scalability. Through practical examples, it 
detailed the configuration and management of project environments using both tools, highlighting Bazel's unique strengths 
in handling large-scale, multi-language projects efficiently. 