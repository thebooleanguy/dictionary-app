# 🌟 Dictionary Web App

A simple yet powerful dictionary web application with autocomplete and spell-checking features, leveraging advanced data structures and algorithms. Built using Java and Spring Boot for a University DSA Project. Still a work in progress!

## 🚀 Features

- **Autocomplete**: Quickly find words as you type using a Trie data structure.
- **Spell-checking**: Identify and correct spelling errors using BK trees.
- **Efficient Sorting**: Words are sorted using a custom QuickSort algorithm.
- **Offline Support**: Works seamlessly without an internet connection.
- **Modern UI**: Beautiful web interface using Thymeleaf for dynamic content rendering.

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, JavaScript
- **Template Engine**: Thymeleaf

## 📦 Setup and Installation

### Prerequisites

- Java 21 (may work with other versions too)
- Maven (only for building)

### 🚀 Running Without Building

1. **Download the JAR files from the [releases page](https://github.com/thebooleanguy/dictionary-app/releases)**:
   - `dictionary-app-web.jar` for the web application
   - `dictionary-app-cli.jar` for the CLI interface

2. **Run the web application JAR**:
    ```bash
    java -jar dictionary-app-web.jar
    ```
      Open your browser and navigate to `http://localhost:8080`

3. **Run the CLI JAR**:
    ```bash
    java -jar dictionary-app-cli.jar
    ```

### 🏗 Building and Running

1. **Clone the repository**:
    ```bash
    git clone https://github.com/thebooleanguy/dictionary-app.git
    cd dictionary-app
    ```

2. **Build and run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

3. **Access the application**:
    Open your browser and navigate to `http://localhost:8080`

## 🗂 Project Structure

```
dictionary-app/
├── src/
│   └── main/
│       ├── java/com/thebooleanguy/dictionary/     # Contains the Java source code.
│       │   │ 
│       │   ├── controller/                    # Spring Boot controllers (e.g., DictionaryController) which handle HTTP requests and responses.
│       │   │  
│       │   ├── model/                         # Data models (e.g., Word, SearchResult)
│       │   │  
│       │   ├── service/                       # Service layers (e.g., DictionaryService) that handle business logic and interact with data structures.
│       │   │  
│       │   ├── util/                          # Utility classes (e.g., DictionaryFileLoader for parsing datasets).
│       │   │  
│       │   ├── DictionaryCLI.java                   #  CLI interface independent of Spring Boot.
│       │   ├── DictionaryWebAppApplication.java     # The main class that runs the Spring Boot application.
│       │   │  
│       │   └── dataStructure/                  # Contains data structures and algorithms.
│       │       ├── algorithms/                 # Algorithms (e.g., QuickSort, LevenshteinDistance)
│       │       └── structures/                 # Data structures (e.g., Trie, BKTree)
│       │ 
│       └── resources/                         # Contains application resources.
│           ├── static/                        # Static resources (e.g., CSS, JS) for styling and scripting.
│           └── templates/                     # Thymeleaf templates (e.g., index.html) used for rendering web pages dynamically.
│ 
├── .gitignore                                 # Git ignore file
├── LICENSE                                    # License file
├── pom.xml                                    # Maven project file
└── README.md                                  # Readme file
```
