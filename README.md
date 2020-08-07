# test-orgyogi-sales

Repository to store all the automation test script which includes E2E Scenario orgYogi sales and purchase scenarios.

This project uses the Gradle build system.You don't need an IDE to build and execute it but IntelliJ IDEA is recommended.

Note: you need Gradle 4.6.1 to build the tests. If you're getting compilation issues, make sure you're not using other version of Gradle

## Quick start
1. Download the project code, preferably using git clone.
2. In IntelliJ IDEA, select File | Open... and point to the ./build.gradle file.
3. Open the Plugins Repository (File Menu | Settings | Plugins) and make sure you have installed the Lombok plugin. (For more Information click here)
4. Ensure that annotation processing is enabled (File Menu | Settings | Build, Execution, Deployment | Annotation Processors): Enable annotation processing checkbox should be selected.
5. Tests are in src/test/java/testcase
6. Page Class for UI is located in src/main/java/pagetest under the specific app module
7. Data is provided via ini file located in src/main/resources


                    

## Samples
You can run the tests from the commandline using the Gradle wrapper.

Run all the tests for xmls configured in build.gradle:
1. Using command : gradle test

Note:
1. Configurations are made to run test cases in browser when running on local machine
2. Tests will run in headless browser when running on Jenkins CI tool.

## Supported WebDriver configurations

| buildEnv \ browser  | `chrome` *(default)* | `browser`          |
|---------------------|----------------------|--------------------|
| `local` *(default)* | :heavy_check_mark:   | :heavy_check_mark: |
| `jenkins`           | :heavy_check_mark:   | :x:                |

## Authentication configurations when test cases run on local

|Module             |  buildEnv         |Environment variable |ini file            |Vault              |
|-------------------|-------------------|-------------------- | -------------------|-------------------|
| ORGYogi               |`local` *(default)*|:x:                  | :heavy_check_mark: | :x:               |



## Test Report
1. After the test run completed, test results are generated in target\allure-results folder
2. Execute command "gradlew allureReport" to get desired platform
3. Execute command "gradlew allureserve" to get allure report in default browser
4. Jira-Xray API are implemented which generates Test execution with all the relevant test cases which are run.
5. Test execution Id can be obtained from the console output (it will be generated automatically after the test run completed)

For more Information about Allure report click [here](https://docs.qameta.io/allure/)

For more Information about Xray Rest API, used to create Xray Execution Results, click [here](https://confluence.xpand-it.com/display/XRAYCLOUD/REST+API)

##Ares-Live Reporting
Setup:-
Navigate to testastra.com
Project creation 
Go to dashboard URL
Login to dashboard and create a project 
Copy API details i.e. Project Key and User Key of the created project, project_ name and ws _name.

Config changes in Framework:-
ARES dashboard: Name of the Dashboard. 
token/UserToken/UserKey: A token will be generated upon creating project in dashboard for every user, which is user specific
ProjectKey/projectId: A project key will get generated upon creating project in dashboard that can be used while posting test results to dashboard, which is unique to every project.
ws_name: workspace name, you can get it from dashboard homepage (Dashboard URL)
 


