# Framework
Test automation framework with Page Object Model design using Java + Cucumber + Maven + TestNG.
Framework follows many of the industry best practices.

Technologies/Tools used in building the framework
=================================================
- Maven - Build automation tool
- Java - Programming language
- Cucumber - BDD
- Gherkin - DSL
- TestNG - Unit testing framework
- Log4J - Logging framework
- Extent Reports - Reporting framework
- GitHub - Version control

Framework implements below best practices
=========================================
- Code reusability
- Code readability
- Scalable automation (demonstrated using multiple test classes)
- Uses explicit waits
- Abstraction layer for UI commands like click, send-keys, etc.
- Parameterisation using TestNG XML and config.properties
- Abstraction layer for test data
- Abstraction layer for static text
- Demonstrates how to effectively capture Screenshots/Videos
- Supports parallel execution using TestNG

How to use the framework as QA Automation Engineer
====================================================
- Create a feature file
- Map the feature file to a step definition
- Create classes for each project and maintain a Page object model
- Call the page using page object manager in the stepdefinition
- Use cucumber tags to filter run scenarios
- Use the Report/Spark Report as shareable evidence
