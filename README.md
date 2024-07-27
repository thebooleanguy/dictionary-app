# Dictionary Web App

A simple dictionary web application with autocomplete and spell-checking features using tries and BK trees, implemented with Java and Spring Boot.

## Features

- **Autocomplete**: Quickly find words as you type.
- **Spell-checking**: Identify and correct spelling errors.
- **Offline Support**: Works without an internet connection.
- **Efficient Data Structures**: Utilizes tries for fast prefix searches and BK trees for spell-checking.

## Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, JavaScript

## Setup and Installation

### Prerequisites

- Java 11 or higher
- Maven

### Steps

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

## Project Structure

```
dictionary-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/dictionary/  # Java source code
│   │   └── resources/                    # Application resources
│   └── test/                             # Test code
├── .gitignore                            # Git ignore file
├── LICENSE                               # License file
├── mvnw                                  # Maven wrapper script (Unix)
├── mvnw.cmd                              # Maven wrapper script (Windows)
├── pom.xml                               # Maven project file
└── README.md                             # Readme file
```
