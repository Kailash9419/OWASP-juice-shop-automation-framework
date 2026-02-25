🛒 OWASP Juice Shop Automation Framework
This is a robust, data-driven Selenium automation framework designed to test the OWASP Juice Shop web application. It is built using the Page Object Model (POM) design pattern to ensure maintainability and scalability.

🚀 Tech Stack
Language: Java

Automation Tool: Selenium WebDriver

Test Framework: TestNG

Build Tool: Maven

Data Driving: Apache POI (Excel)

Utility: WebDriverManager

🛠️ Key Features
Page Object Model: Separation of locators and test logic for cleaner code.

Data-Driven Testing: Uses TestNG @DataProvider and Apache POI to fetch test credentials from an External Excel sheet (loginData.xlsx).

Synchronization: Implements Explicit Waits (WebDriverWait) to handle dynamic elements and AJAX loaders.

Reusable Base Class: Centralized driver initialization and teardown logic.

Configuration Management: Uses config.properties for environment-specific URLs and file paths.

📁 Project Structure
Plaintext
src
 ├── main/java
 │    ├── Base           # Driver setup & Teardown
 │    ├── Pages          # Locators & Page Actions (POM)
 │    └── Utility        # ConfigReader & ExcelUtils
 ├── test/java
 │    ├── tests          # Test Scripts
 │    └── dataprovider   # Data supply logic
 └── test/resources      # Config files & Excel Data
pom.xml                  # Project Dependencies
testng.xml               # Test Suite Configuration
⚙️ How to Run
Clone the repository:

Bash
git clone https://github.com/yourusername/your-repo-name.git
Ensure you have Maven installed.

Update the config.properties file if necessary.

Run the tests via Maven:

Bash
mvn test
Alternatively: Right-click testng.xml and select Run As > TestNG Suite.
