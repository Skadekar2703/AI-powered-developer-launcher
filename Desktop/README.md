# AI Powered Developer Launcher - Desktop

A modern desktop application built with **Kotlin Multiplatform** and **Compose Multiplatform**.

The desktop application allows developers to set up their development workspace quickly by automating project initialization, authentication, and environment configuration.

---

## Tech Stack

- Kotlin Multiplatform
- Compose Multiplatform
- Gradle Kotlin DSL
- JVM Desktop

---

## Prerequisites

Before running the project, make sure you have:

- JDK 17 or later
- IntelliJ IDEA (Latest Recommended)
- Git
- Gradle (or use the included Gradle Wrapper)

---

## Clone the Repository

```bash
git clone https://github.com/Skadekar2703/AI-powered-developer-launcher.git
```

Navigate to the Desktop project:

```bash
cd AI-powered-developer-launcher/Desktop
```

---

## Open the Project

1. Open IntelliJ IDEA.
2. Select **Open**.
3. Choose the **Desktop** folder.
4. Wait for Gradle Sync to finish.

---

## Run the Application

Using Gradle:

```bash
./gradlew :desktopApp:run
```

Or simply click the **Run** button inside IntelliJ IDEA.

---

## Hot Reload

For development, you can use:

```bash
./gradlew :desktopApp:hotRun --auto
```

---

## Project Structure

```
Desktop
│
├── desktopApp/        # Desktop launcher
├── shared/            # Shared UI and business logic
├── gradle/
├── build.gradle.kts
├── settings.gradle.kts
└── README.md
```

---

## Features

- Secure Authentication
- Workspace Configuration
- Cross-platform Shared UI
- Modern Compose UI
- Modular Architecture
- Easy Development Workflow

---

## Building the Project

To generate a build:

```bash
./gradlew build
```

---

## Contributors

| Name | Role |
|------|------|
| **Soham Kadekar** | Android & Kotlin Multiplatform Developer |
| **Vishal Ude** | Desktop Application Developer |

---

## License

This project is developed for educational and development purposes.
