# Foodics API Automation Framework

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![RestAssured](https://img.shields.io/badge/RestAssured-5.3.2-green.svg)](https://rest-assured.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.8.0-red.svg)](https://testng.org/)
[![Allure](https://img.shields.io/badge/Allure-2.24.0-purple.svg)](https://docs.qameta.io/allure/)

A professional REST API automation framework built with **RestAssured**, **TestNG**, and the **Page Object Model (POM)** pattern. Designed for comprehensive testing of the Foodics/Solo API with support for multiple environments, role-based authentication, and concept-specific operations.

---

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Project Structure](#project-structure)
- [Framework Architecture](#framework-architecture)
- [Running Tests](#running-tests)
- [Allure Reports](#allure-reports)
- [API Modules Covered](#api-modules-covered)
- [Usage Examples](#usage-examples)
- [Adding New Tests](#adding-new-tests)
- [Contributing](#contributing)

---

## Features

- **Page Object Model (POM)** — Clean separation of API endpoints and test logic
- **Role-Based Authentication** — Support for Admin, User, Merchant, Customer, Employee, and Guest roles
- **Multi-Environment** — QA, Staging, and Production configurations
- **Concept Key Management** — Automatic extraction and caching for Solo-Concept header
- **Token Caching** — Efficient token reuse across test runs
- **Fluent API Client** — Chainable methods for headers, auth, query params, and body
- **Allure Reporting** — Rich HTML reports with test history and attachments
- **Parallel Execution** — TestNG parallel execution for faster runs
- **22+ API Modules** — Comprehensive coverage of authentication, orders, payments, customers, and more

---

## Tech Stack

| Component | Technology |
|-----------|------------|
| **Language** | Java 11 |
| **Build Tool** | Maven |
| **API Testing** | RestAssured 5.3.2 |
| **Test Framework** | TestNG 7.8.0 |
| **JSON Processing** | Jackson 2.15.2 |
| **Reporting** | Allure 2.24.0 |
| **Logging** | Log4j 2.20.0, SLF4J |

---

## Prerequisites

- **Java 11** or higher
- **Maven 3.6+**
- Valid API credentials (email/password) for the target environment

---

## Installation

### 1. Clone the repository

```bash
git clone https://github.com/your-username/api-automation-framework.git
cd api-automation-framework
```

### 2. Build the project

```bash
mvn clean install
```

### 3. Configure your environment

Copy the configuration template and update with your credentials:

```bash
# Edit the appropriate environment file
# For QA: src/test/resources/config/qa.properties
# For Staging: src/test/resources/config/staging.properties
# For Production: src/test/resources/config/production.properties
```

---

## Configuration

### Environment Selection

Set the `env` system property to switch environments:

| Value | Config File |
|-------|-------------|
| `qa` (default) | `qa.properties` |
| `staging` | `staging.properties` |
| `production` | `production.properties` |

### Configuration Properties

Edit `src/test/resources/config/<env>.properties`:

```properties
# Base API URL (required)
base.url=https://test-consumer-api.getsolo.io

# API Key (optional - for endpoints requiring X-API-Key header)
api.key=your-api-key-here

# Request timeout in milliseconds
timeout=30000

# Test credentials (required for authenticated tests)
test.user.email=your-email
test.user.password=your-password

# Role-specific credentials (optional - falls back to test.user.*)
test.admin.email=admin-email
test.admin.password=admin-password

# Pre-generated tokens (optional - tokens are auto-fetched via login API)
auth.token=
auth.token.admin=
auth.token.user=
auth.token.merchant=
auth.token.customer=

# Concept key (optional - extracted from login response if not set)
concept.key=
```

---

## Project Structure

```
api-automation-framework/
├── src/
│   ├── main/java/com/foodics/api/
│   │   ├── auth/                 # Authentication
│   │   │   ├── AuthManager.java # Token management, role-based auth
│   │   │   └── Role.java        # User roles enum
│   │   ├── base/
│   │   │   └── ApiClient.java   # Base HTTP client with fluent API
│   │   ├── config/
│   │   │   ├── ConfigManager.java # Environment configuration
│   │   │   └── Environment.java  # Environment enum
│   │   ├── models/              # Request/Response DTOs
│   │   │   ├── BaseResponse.java
│   │   │   ├── LoginRequest.java
│   │   │   └── LoginResponse.java
│   │   ├── pom/                 # Page Object Models (API endpoints)
│   │   │   ├── AuthPage.java
│   │   │   ├── UserPage.java
│   │   │   └── v1/             # V1 API modules
│   │   │       ├── OrderPage.java
│   │   │       ├── PaymentPage.java
│   │   │       ├── CustomerPage.java
│   │   │       ├── MenuPage.java
│   │   │       └── ... (20+ page classes)
│   │   └── utils/
│   │       ├── ConceptKeyHelper.java  # Concept key extraction
│   │       ├── JsonUtils.java         # JSON serialization
│   │       ├── ResponseValidator.java # Assertion helpers
│   │       └── TokenHelper.java       # Token utilities
│   └── test/
│       ├── java/com/foodics/api/
│       │   ├── base/
│       │   │   └── BaseTest.java      # Test base class
│       │   └── tests/                 # Test classes (22+)
│       │       ├── AuthTest.java
│       │       ├── WhoAmITest.java
│       │       ├── PaymentTest.java
│       │       └── ...
│       └── resources/
│           ├── config/               # Environment configs
│           │   ├── qa.properties
│           │   ├── staging.properties
│           │   └── production.properties
│           └── testng.xml            # TestNG suite definition
├── pom.xml
└── README.md
```

---

## Framework Architecture

### ApiClient (Base Layer)

The `ApiClient` provides a fluent interface for all API requests:

```java
// Unauthenticated request
withoutAuth().get("/endpoint");

// With Bearer token
withAuth(token).get("/endpoint");

// With role-based auth
withAuth(Role.ADMIN).get("/endpoint");

// With Solo-Concept header (for concept-specific APIs)
withAuth(token).withConceptKey(conceptKey).get("/endpoint");

// Full chain: auth + concept + body + path params
withAuth(Role.USER)
    .withConceptKey("1")
    .withPathParams(Map.of("id", "123"))
    .withBody(requestData)
    .post("/orders/validate");
```

### Authentication Flow

1. **AuthManager** — Singleton that manages token caching per role
2. **Login API** — Tokens are fetched automatically when not cached
3. **Config Fallback** — Pre-set tokens in config are used if login fails
4. **ConceptKeyHelper** — Extracts concept keys from login response for Solo-Concept header

### Page Object Pattern

Each API module has a corresponding Page class that extends `ApiClient`:

- **AuthPage** — Login, guest login, logout, reset password
- **UserPage** — WhoAmI, profile, user management
- **OrderPage** — Create, validate, cancel orders
- **CustomerPage** — Customer CRUD, addresses, vehicles
- **PaymentPage** — Payment by order token
- And 17+ more modules...

---

## Running Tests

### Run all tests (default: QA environment)

```bash
mvn test
```

### Run with specific environment

```bash
mvn test -Denv=staging
mvn test -Denv=production
```

### Run a specific test class

```bash
mvn test -Dtest=AuthTest
mvn test -Dtest=WhoAmITest
```

### Run a specific test method

```bash
mvn test -Dtest=AuthTest#testLoginSuccess
```

### Run tests matching a pattern

```bash
mvn test -Dtest=*Test
```

---

## Allure Reports

### Generate and view Allure report

```bash
# Run tests with Allure listener
mvn clean test

# Generate and serve report
mvn allure:serve
```

This opens an interactive HTML report in your browser with:

- Test execution history
- Pass/fail statistics
- Step-by-step traces
- Attachments and logs

### Generate report only (no browser)

```bash
mvn allure:report
# Report generated in target/site/allure-maven-plugin/
```

---

## API Modules Covered

| Module | Test Class | Key Endpoints |
|--------|------------|---------------|
| **Authentication** | AuthTest | Login, Guest Login, Logout |
| **User** | WhoAmITest | WhoAmI, Profile |
| **Payment** | PaymentTest | Get payment by order token |
| **Order** | OrderTest | Get order, Validate promotions |
| **Customer** | CustomerTest | Create customer, List, CRUD |
| **Menu** | MenuTest | Get menus, Menu items |
| **Concept** | ConceptTest | List concepts, Create, Update |
| **Location** | LocationTest | List, Create locations |
| **Promotion** | PromotionTest | List, Create promotions |
| **Coupon** | CouponTest | List coupons, Digital coupons |
| **Application** | ApplicationTest | Get application, Update theme |
| **Report** | ReportTest | Top performing products |
| **Country** | CountryTest | Get countries |
| **Client** | ClientTest | List clients |
| **Employee** | EmployeeTest | Forgot password, Update |
| **Device** | DeviceTest | Maintenance mode |
| **Segment** | SegmentTest | List segments |
| **Push Notification** | PushNotificationTest | List, Send notifications |
| **License** | LicenseTest | Create, Get license |
| **CMS** | CmsTest | Pages, Sliders, Slides |
| **Feedback** | FeedbackTest | Create feedback |
| **Modifier** | ModifierTest | Create, Update modifier |
| **Token** | GetTokenTest | Token retrieval utilities |

---

## Usage Examples

### Example 1: Simple unauthenticated GET

```java
@Test
public void testGetMenus() {
    MenuPage menuPage = new MenuPage();
    Response response = menuPage.getMenus();
    
    Assert.assertNotNull(response);
    Assert.assertEquals(response.getStatusCode(), 200);
}
```

### Example 2: Authenticated request with token

```java
@Test
public void testWhoAmI() {
    String token = TokenHelper.getToken(email, password);
    String conceptKey = ConceptKeyHelper.getConceptKey(token);
    
    UserPage userPage = new UserPage();
    Response response = userPage.whoAmI(token, conceptKey);
    
    ResponseValidator.validateStatusCode(response, 200);
    ResponseValidator.validateFieldExists(response, "data");
}
```

### Example 3: Role-based authentication

```java
@Test
public void testListCustomers() {
    CustomerPage customerPage = new CustomerPage();
    String conceptKey = ConceptKeyHelper.getConceptKey(token);
    
    Response response = customerPage.listCustomers(conceptKey);
    // Uses Role.USER automatically via AuthManager
}
```

### Example 4: POST with request body

```java
@Test
public void testCreateOrder() {
    Map<String, Object> orderData = new HashMap<>();
    orderData.put("items", Arrays.asList(...));
    
    OrderPage orderPage = new OrderPage();
    Response response = orderPage.createOrder(orderData);
}
```

---

## Adding New Tests

### 1. Create a new Page class (if needed)

```java
public class NewFeaturePage extends ApiClient {
    private static final String ENDPOINT = "/new-feature";
    
    public Response getFeature(String id) {
        return withAuth(Role.USER)
            .withPathParams(Map.of("id", id))
            .get(ENDPOINT);
    }
}
```

### 2. Create a test class

```java
@Epic("New Feature API")
@Feature("New Feature")
public class NewFeatureTest extends BaseTest {
    private NewFeaturePage newFeaturePage;
    
    @BeforeClass
    public void setup() {
        newFeaturePage = new NewFeaturePage();
    }
    
    @Test(description = "Call get feature API")
    @Severity(SeverityLevel.NORMAL)
    public void testGetFeature() {
        Response response = newFeaturePage.getFeature("1");
        Assert.assertNotNull(response);
        Assert.assertTrue(response.getStatusCode() >= 200 && response.getStatusCode() < 600);
    }
}
```

### 3. Register in testng.xml

```xml
<test name="New Feature API Tests">
    <classes>
        <class name="com.foodics.api.tests.NewFeatureTest"/>
    </classes>
</test>
```

---

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## License

This project is proprietary to Foodics. All rights reserved.
