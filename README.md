# 🌟 Dictionary Web App

A simple yet powerful dictionary web application with autocomplete and spell-checking features, leveraging advanced data structures and algorithms. Built using Java and Spring Boot for a University DSA Project. Still a work in progress!

<img src="https://cdn.britannica.com/97/118097-050-5B2CF2EA/English-dictionary.jpg" alt="Dictionary" width="800" />

## 🚀 Features

- **Autocomplete**: Quickly find words as you type using a Trie data structure.
- **Spell-checking**: Identify and correct spelling errors using BK trees.
- **Efficient Sorting**: Words are sorted using a custom QuickSort algorithm.
- **Query History Management**: Utilizes an LRU Cache to efficiently manage and store recent search queries.
- **Offline Support**: Works seamlessly without an internet connection.
- **Modern UI**: Beautiful web interface using Thymeleaf for dynamic content rendering.

## 🛠 Tech Stack

- **Backend**: Java, Spring Boot
- **Frontend**: HTML, CSS, JavaScript
- **Template Engine**: Thymeleaf

<br><br>

## 📦 Setup and Installation

### Prerequisites

- Java 21 (should work with other versions too)
- Maven (only if building)

### 🚀 Running Without Building

1. **Download the JAR files from the [releases page](https://github.com/thebooleanguy/dictionary-app/releases)**:
   - `dictionary-app-web.jar` for the web application
   - `dictionary-app-cli.jar` for the CLI interface

2. **Run the web application JAR**:
    ```bash
    java -jar dictionary-app-web.jar
    ```
    If it didn't autostart, Open your browser and navigate to `http://localhost:8080`

3. **Run the CLI JAR**:
    ```bash
    java -jar dictionary-app-cli.jar
    ```

<br>

### 📂 Running the Project from File Explorer

1. **Right-Click and Run on Windows**:
   - Navigate to the `scripts/` directory within the project.
   - Right-click on `run-dictionary-app.bat` and select "Run as administrator" to start the application.

<br>

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
    Or import this directory as a project (from existing sources) in your IDE (Intellij, Netbeans, etc...) with maven as the build tool. And then build.

3. **Access the application**:
    Open your browser and navigate to `http://localhost:8080`

<br>

## 🗂 Project Structure

```
dictionary-app/
├── src/
│   └── main/
│       ├── java/com/thebooleanguy/dictionary/       
│       │   ├── controller/                           # Spring Boot controllers (e.g., DictionaryController) which handle HTTP requests and responses.
│       │   ├── model/                                # Data models (e.g., Word, SearchResult)
│       │   ├── service/                              # Service layers (e.g., DictionaryService) that handle business logic and interact with data structures.
│       │   ├── util/                                 # Utility classes (e.g., DictionaryFileLoader for parsing datasets).
│       │   ├── DictionaryCLI.java                    # CLI interface independent of Spring Boot.
│       │   ├── DictionaryWebAppApplication.java      # The main class that runs the Spring Boot application.
│       │   └── dataStructure/                        # Contains data structures and algorithms.
│       │       ├── algorithms/                       # Algorithms (e.g., QuickSort, LevenshteinDistance)
│       │       └── structures/                       # Data structures (e.g., Trie, BKTree, LRUCache, HashMap)
│       └── resources/                                # Contains application resources.
│       │   ├── static/                               # Static resources (e.g., CSS, JS) for styling and scripting.
│       │   └── templates/                            # Thymeleaf templates (e.g., index.html, cachedView.html) used for rendering web pages dynamically.
│       └── test/                                     # Contains tests for the implemented data structures and algorithms
├── scripts                                           # Contains miscellaneous scripts (e.g., python script used to generate word dataset, wrapper scripts to launch .jar)
├── .gitignore                                        # Git ignore file
├── LICENSE                                           # License file
├── pom.xml                                           # Maven project file
└── README.md                                         # Readme file
```

<br><br>

## 📊 Data Structures and Algorithms

### 🏗 Data Structures

#### **Trie**

<img src="https://danieldev23.github.io/assets/images/algorithm/1126/trie.png" alt="Dictionary" width="800" />

- **Description**: A tree-like data structure for efficient retrieval of words with autocomplete functionality.
- **How It Works**:
  - Words are inserted into the Trie, creating nodes for each character in the word.
  - Searches are performed by traversing nodes corresponding to the characters of the word.
  - Supports efficient prefix searches and autocomplete features.
- **Usage**: Useful for implementing features like word suggestions and prefix-based searches.

<br>

#### **BK Tree**

<img src="https://miro.medium.com/v2/resize:fit:878/1*vNJ_BwaSD-j2VeGtkbmReg.png" alt="Dictionary" width="800" />

- **Description**: Used for approximate string matching. Finds words similar to a given word based on the Levenshtein distance (edit distance).
- **How It Works**: 
  - Words are added to the tree based on their distance from existing words using the Levenshtein distance.
  - During the search, nodes within a specified distance are explored to find approximate matches.
- **Usage**: Ideal for spell-checking and fuzzy search functionalities.

<br>

#### **LRU Cache**

<img src="https://ucarecdn.com/80434174-2689-4352-9445-2b92592954ca/" alt="Dictionary" width="800" />

- **Description**: The application uses an LRU (Least Recently Used) Cache to efficiently manage and store recent search queries. This ensures that only the most recent queries are kept, optimizing performance and memory usage.
- **Components**:
  - **LinkedList**: Manages the order of entries, with the most recently used queries at the head and the least recently used queries at the tail.
  - **HashMap**: Provides fast access to cache entries, mapping query strings to their corresponding nodes in the `LinkedList`.
- **How It Works**:
  - When a new search query is performed, the cache checks if the query is already present. If it is, the entry is moved to the front of the `LinkedList`.
  - If the query is not present and the cache is full, the oldest entry (at the tail of the `LinkedList`) is removed to make room for the new query. The corresponding entry in the `HashMap` is also removed.
  - The `LinkedList` maintains the order of entries, ensuring that the least recently used queries are evicted when the cache exceeds its capacity.
- **Benefits**:
  - **Performance**: Constant time complexity for insertions, deletions, and look-ups due to the combination of `LinkedList` and `HashMap`.
  - **Memory Management**: Optimizes memory usage by retaining only a fixed number of recent queries.
  - **User Experience**: Provides quick access to recent searches without affecting application responsiveness.

<br>

### 🔢 Algorithms

#### **Levenshtein Distance**

<img src="https://miro.medium.com/max/1108/1*bEWdxv_FoTQurG9fyS3nSA.jpeg" alt="Dictionary" max-width="800" />

- **Description**: Measures the difference between two sequences by calculating the minimum number of single-character edits (insertions, deletions, or substitutions) required to transform one sequence into the other.
- **Usage**: Used by the BK Tree to find approximate matches based on edit distance.

<br>

#### **QuickSort**

<img src="https://miro.medium.com/max/1280/1*aoXS7Bz8ZFEoQ-sq-UycsA.png" alt="Dictionary" width="800" />

- **Description**: A highly efficient sorting algorithm using a divide-and-conquer approach to sort lists.
- **How It Works**:
  - The list is divided into sublists based on a pivot element.
  - Elements are rearranged so that elements greater than the pivot are on one side, and elements less than the pivot are on the other side.
  - Recursively applies the same process to sublists.
- **Usage**: Utilized in the BK Tree and Trie implementations to sort results by frequency in descending order.

<br><br>

### ⏱ Time Complexity

#### **Trie**
- **Insertion/Search/Prefix Search**: O(L) — Linear time complexity based on the length of the word, effectively constant time due to the fixed number of possible characters (26 for English alphabet).

#### **BK Tree**
- **Insertion/Search**: O(N * D) — Linear time complexity with respect to the number of entries (N) and average distance computation time (D).

#### **Levenshtein Distance**
- **Computation**: O(m * n) — Linear time complexity relative to the length of the words being compared.

#### **QuickSort**
- **Average Case**: O(n log n) — Efficient linearithmic time complexity for sorting elements.
- **Worst Case**: O(n^2) — Quadratic time complexity in cases of poor pivot selection.

#### **LRU Cache**
- **Insertion/Deletion/Lookup**: O(1) — Constant time complexity using HashMap and LinkedList.
