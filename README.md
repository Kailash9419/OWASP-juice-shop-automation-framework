# 🛒 OWASP Juice Shop — Hybrid Automation Framework

![CI Status](https://github.com/Kailash9419/OWASP-juice-shop-automation-framework/actions/workflows/maven.yml/badge.svg)
![Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.x-red)
![Maven](https://img.shields.io/badge/Maven-3.x-blue?logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-yellow)

A robust and scalable **Hybrid Automation Framework** built to automate the popular vulnerable e-commerce application — [OWASP Juice Shop](https://juice-shop.herokuapp.com). The framework follows industry best practices including **Page Object Model (POM)**, **Data-Driven Testing**, and **CI/CD Integration** using GitHub Actions.

---

## 🏗️ Framework Architecture

```
JuiceShopAutomation/
├── .github/
│   └── workflows/
│       └── maven.yml           # GitHub Actions CI/CD pipeline
├── src/
│   ├── main/java/
│   │   ├── Base/
│   │   │   └── BaseClass.java  # WebDriver setup, teardown, retry support
│   │   ├── Pages/
│   │   │   ├── LoginPage.java
│   │   │   ├── ProductPage.java
│   │   │   ├── BasketPage.java
│   │   │   ├── AddressPage.java
│   │   │   └── PaymentPage.java
│   │   └── Utility/
│   │       ├── ConfigReader.java      # config.properties reader
│   │       ├── ExcelUtils.java        # Apache POI Excel handler
│   │       ├── ScreenshotUtils.java   # Auto screenshot on failure
│   │       ├── Listeners.java         # TestNG + Extent Report hooks
│   │       ├── RetryAnalyzer.java     # Flaky test retry with driver reset
│   │       └── AnnotationTransformer.java
│   └── test/
│       ├── java/tests/
│       │   └── End2End_2.java         # End-to-end regression suite
│       └── resources/
│           ├── config.properties      # URL, credentials, browser config
│           └── testData/              # Excel test data files
├── screenshots/                       # Auto-generated failure screenshots
├── test-output/                       # Extent Reports & TestNG reports
├── pom.xml
└── testng.xml
```

---

## ✨ Key Features

| Feature | Description |
|---|---|
| 📄 Page Object Model | Locators and test logic fully separated for easy maintenance |
| 🔄 Retry Mechanism | Failed tests auto-retry with fresh WebDriver session |
| 📊 Extent Reports | Rich HTML reports with screenshots attached to failures |
| 📸 Auto Screenshots | Timestamped screenshots captured on every test failure |
| ⚙️ CI/CD Pipeline | GitHub Actions runs full suite on every push to main |
| 🖥️ Headless Execution | Chrome headless mode for server-side Linux execution |
| 📁 Data-Driven | Test data managed via Excel (Apache POI) + config.properties |
| ⏱️ Smart Waits | WebDriverWait for Angular dynamic content handling |

---

## 🛠️ Tech Stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17 | Core language |
| Selenium WebDriver | 4.x | Browser automation |
| TestNG | 7.x | Test runner & assertions |
| Maven | 3.x | Build & dependency management |
| Extent Reports | 5.x | HTML test reporting |
| Apache POI | 5.x | Excel data handling |
| WebDriverManager | 5.x | Auto ChromeDriver management |
| GitHub Actions | — | CI/CD pipeline |
| Docker | — | OWASP Juice Shop hosting |
| Log4j2 | 2.x | Execution logging |

---

## 🚀 How to Run Locally

### Prerequisites
- Java 17+
- Maven 3.x
- Google Chrome (latest)
- Git

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/Kailash9419/OWASP-juice-shop-automation-framework.git
cd OWASP-juice-shop-automation-framework
```

**2. Configure test data**

Edit `src/test/resources/config.properties`:
```properties
url=https://juice-shop.herokuapp.com
email=your-registered-email@example.com
password=YourPassword
browser=chrome
```

**3. Run the full suite**
```bash
mvn clean test
```

**4. Run specific group**
```bash
mvn clean test -Dgroups=smoke
mvn clean test -Dgroups=regression
```

**5. View Reports**

Open `test-output/ExtentReport.html` in browser.

---

## 🔄 CI/CD Pipeline

The framework is integrated with **GitHub Actions** and runs automatically on every push to `main` or `master`.

### Pipeline Steps
```
Push to main
    ↓
Checkout Code
    ↓
Setup JDK 17
    ↓
Install Chrome (headless)
    ↓
Run Tests (mvn clean test)
    ↓
Upload Artifacts (Reports + Screenshots)
```

### Artifacts Available After Each Run
- 📊 Extent HTML Reports
- 📸 Failure Screenshots  
- 📋 TestNG XML Reports
- 📝 Console Logs

---

## 📊 Test Coverage

| Test | Group | Description |
|---|---|---|
| `loginAndDashboardCheck` | smoke, regression | Login + dashboard URL validation |
| `productBasketAndCheckoutFlow` | regression | Search → Add to cart → Checkout → Payment |

---

## 🔧 Framework Design Decisions

**Why Retry Analyzer?**
CI environments are slower than local — network latency and cold Docker starts can cause flaky failures. The `RetryAnalyzer` resets the WebDriver session completely before retrying, ensuring a clean state.

**Why separate BaseClass?**
All driver lifecycle (init, teardown, reset) is centralized. Test classes only extend `BaseClass` — no boilerplate in test files.

**Why ThreadLocal-ready structure?**
The current design isolates driver per test instance, making it straightforward to upgrade to parallel execution with `ThreadLocal<WebDriver>`.

---

## 👤 Author

**Kailash**  
QA Automation Engineer | 5 Years Experience

[![GitHub](https://img.shields.io/badge/GitHub-Kailash9419-black?logo=github)](https://github.com/Kailash9419)

---

## 📝 License

This project is licensed under the MIT License.
