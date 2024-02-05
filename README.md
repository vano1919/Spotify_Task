# Spotify_Task

## Overview
This Java-based framework specializes in automating both positive and negative test scenarios for the Spotify web application's login page. It aims to ensure a robust authentication process, minimizing errors and avoiding interruptions in the automation workflow.

## How It Works
At its core, the framework conducts thorough testing by simulating user behavior on Spotify's login page. It leverages Java and Selenium WebDriver for intricate browser automation tasks. To capture the full spectrum of user interactions, the framework includes tests for both successful logins (positive cases) and various login failures (negative cases). Cucumber is integrated to enable behavior-driven development, enhancing the clarity and maintainability of test scripts. The strategic combination of these tools creates a protective envelope around the login functionality, preventing regressions and ensuring consistent performance.

## Project Structure
- `enumeration`: Enums for user credentials and page links.
- `factory`: Factory pattern implementation for WebDriver creation.
- `pages`: Page objects representing various pages within the application.
- `properties`: Configuration for browser drivers.
- `test`: Cucumber step definitions, feature files, and test runners.

## Setup Instructions
1. Ensure Java JDK 8 or above and Maven are installed on your machine.
2. Clone the repository to your local environment.
3. Download `chromedriver.exe` compatible with your Chrome version and place it in the `src/test/resources/webdrivers` directory.
4. Update the `src/main/resources/credentials.txt` file with valid Spotify user credentials for positive test cases.
   Follow the instructions within the file for the correct format.

## Running Tests
To run the tests, execute the following command in your terminal:
```bash
mvn test
