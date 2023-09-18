# Software Testing & QA Course

## Task Description:

Given the attached files, you will find the following:
1. `Software-Testing-Assi3-Backend`: This contains a Spring Boot application that serves as the backend server for a TODO application.
2. `Software-Testing-Assi3-Frontend`: This contains an HTML file and JavaScript file that work as the frontend for the TODO application.
3. `Software-Testing-Assi3-PostmanCollection`: This contains a Postman collection with all the requests.

### Backend Testing:

#### 1. Write Unit Test Cases using JUnit:
   - Perform test coverage for the application.
   - Ensure the following coverage techniques are considered:
     - Graph Coverage
     - Data Flow Coverage
   - Ensure there is no overlap between test cases or redundant ones.
   - Target a code coverage of at least 80%.
   - If code changes are required to facilitate test case writing, make them and highlight them either in comments or a separate .txt file.

### Frontend Testing:

#### 2. Write Test Suite Cases:
   - Use Robot Framework or Selenium WebDriver.
   - Test at least 3 functionalities in the application:
     a. **Add new TODO:**
        - Verify that a user can add a new TODO.
        - After submission, it should be added to the table, and all text inputs should be empty.
     b. **Delete:**
        - Verify that a user can delete a TODO, and it is removed from the table.
     c. **Update completion:**
        - Verify that a user can update the completion of a TODO by clicking the checkbox.
        - The table should display it correctly.
     d. **Get all TODOs:**
        - Verify that all TODOs are displayed correctly.
     e. **Get completed TODOs:**
        - Verify that the table displays only completed TODOs.
   - Make necessary changes in the HTML file if needed for test case completion, and highlight these changes either in comments or a separate .txt file.

## Deadline & Submission:

1. **Teams:** Form a team of 3-4.

## References:

- [Graph Coverage](https://cs.gmu.edu:8443/offutt/coverage/GraphCoverage)
- [Data Flow Graph Coverage](http://cs.gmu.edu:8080/offutt/coverage/DFGraphCoverage)
- [Logic Coverage](http://cs.gmu.edu:8080/offutt/coverage/LogicCoverage)
