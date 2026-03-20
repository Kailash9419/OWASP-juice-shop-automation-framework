🛒 OWASP Juice Shop - Hybrid Automation Framework
Ek robust aur scalable Hybrid Automation Framework jo popular e-commerce application OWASP Juice Shop ko automate karta hai. Ismein Page Object Model (POM), Data-Driven Testing, aur CI/CD Integration ka use kiya gaya hai.

🛠️ Tech Stack & Tools
Language: Java 17

Automation: Selenium WebDriver (v4.x)

Test Runner: TestNG

Build Tool: Maven

Reporting: Extent Reports & TestNG Reports

CI/CD: GitHub Actions

Data Management: Apache POI (Excel) & Config Properties

✨ Key Features
Page Object Model (POM): Locators aur Test Logic ko alag rakha gaya hai taaki maintenance aasaan ho.

Hybrid Approach: Environment details ke liye config.properties aur heavy test data ke liye Excel ka use.

Smart Synchronization: Angular components ko handle karne ke liye WebDriverWait aur JavaScriptExecutor ka upyog.

Automated Screenshots: Har failure par timestamp ke saath screenshots capture hote hain.

CI/CD Ready: GitHub Actions ke saath integrated hai, har code push par tests automatically Linux server par run hote hain.

Headless Execution: Server-side execution ke liye Chrome Headless mode supported hai.

📁 Project Structure
Plaintext
JuiceShopAutomation/
├── .github/workflows/      # GitHub Actions (CI/CD) configuration
├── src/main/java/
│   ├── Pages/              # Page Classes (Login, Product, Payment, etc.)
│   ├── Base/               # Driver Setup & Teardown (BaseClass)
│   └── Utility/            # ScreenshotUtils, ExcelUtils, ConfigReader
├── src/test/java/
│   └── tests/              # Test Scripts (End-to-End & Functional)
├── src/test/resources/     # Test Data (Excel, config.properties)
├── screenshots/            # Failed test captures (Auto-generated)
├── test-output/            # Execution reports & Artifacts
├── pom.xml                 # Maven dependencies & Plugins
└── testng.xml              # Test Suite configuration

🚀 How to Run
Clone the project:

Bash
git clone <your-repository-link>
Setup Data: src/test/resources/config.properties mein apni valid email aur password dalein.

Execute via Maven:

Bash
mvn clean test
📊 Reporting & Artifacts
Har test run ke baad, GitHub Actions ke Artifacts section mein niche di gayi cheezein upload hoti hain:

Extent Reports: Test execution ka visual summary.

Failure Screenshots: Fail huye steps ka exact visual evidence.

Console Logs: Detailed step-by-step execution history.

👤 Author
Kailash
QA Automation Engineer | 5 Years Experience
