# CyberVault

CyberVault is a lightweight, local-first desktop password manager and secure note repository designed to eliminate reliance on heavy, resource-intensive web-based solutions or bloated frameworks. Built with a robust backend architecture, it bridges the gap between raw relational database storage and clean Object-Oriented Programming (OOP) principles.

🚀 Key Features
Local-First Architecture: Operates entirely offline using an embedded database, ensuring absolute privacy and data ownership.

Single-Table Inheritance Design: Utilizes a unified database schema to seamlessly polymorphic map distinct asset types like login credentials and secure text notes.

Dynamic UI Synchronization: Leverages active data binding to keep the interface seamlessly aligned with the underlying database state without requiring manual component re-rendering.

SQL Injection Prevention: Implements strictly parameterized database queries to ensure user inputs are sanitized before persistence.

🛠️ Tech Stack
Language: Java

UI Framework: JavaFX (Modulized)

Database Engine: SQLite (via JDBC)

Build System & Dependency Management: Maven
