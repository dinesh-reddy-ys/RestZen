# RestZen
Automated API testing framework using RestAssured, Java, and TestNG. Supports testing RESTful services with JSON payloads, validating status codes, headers, and response data. Includes reusable methods, parameterized tests, and structured reporting for efficient backend validation.

#Project structure
src
├── main
│   └── java
│       ├── utilities/               # Utility classes (e.g., configuration, API helpers)
│       └── resources/               # Global resources (e.g., configurations)
│           └── application.properties
├── test
│   └── java
│       ├── stepDefinitions/         # Step Definitions (Glue code for Gherkin steps)
│       ├── runners/                # Test Runner classes
│       ├── hooks/                  # Hooks for setup and teardown
│       ├── features/               # Gherkin feature files (BDD Scenarios)
│       └── models/                 # POJO classes for request/response payloads (if used)
│   
└── test/resources
    └── data/                       # Test data files (optional, e.g. JSON, CSV, etc.)
