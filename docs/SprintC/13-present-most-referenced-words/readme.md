# Presenting the top 20 most referenced words in candidate files

-----------

## 1. User Story Description

### 1.1. User Story Description

As a Customer Manager, when displaying the candidate data, I expect the system to present 
a top 20 list of the most frequently referenced words from files uploaded by a
candidate. Additionally, I require a comprehensive list of the files in which these 
words appear.

### 1.2. Customer Specifications and Clarifications

**From the specifications document:**

> **NFR14(SCOMP) - Process to counting words of very large files:**
> 
> The process to count words of very large files should follow specific
technical requirements such as implementing parallelism and concurrency using Java
and threads. Specific requirements will be provided in SCOMP.

**From the client clarifications:**

### 1.3. Acceptance Criteria

### 1.4. Found out Dependencies

### 1.5. Input and Output Data

**Selected Data:**

    * Application

**Output Data:**

    * (In)Success of the operation
    * Top 20 most referenced words

### 1.6. System Sequence Diagram (SSD)
![system-sequence-diagram.svg](system-sequence-diagram.svg)

### 1.7. Sequence Diagram (SD)
![sequence-diagram.svg](sequence-diagram.svg)

### 1.8 Other Relevant Remarks

*  None to specify

## 2. Analysis and Design
The desing of this use case is based on the following domain model and class diagram:

### 2.1. Domain Model
![domain-model.svg](domain-model.svg)

### 2.2. Class Diagram

## 3. Implementation
The most important method analyzes a list of candidate files to find the most referenced words across them. It spawns 
threads to process each file concurrently. Each thread reads a file, counts word occurrences, and updates a shared word 
count map, ensuring thread safety with synchronization. After all threads complete, it sorts the map based on word counts 
and returns the result.

```java
    public Map<String, Map<String, Integer>> findMostReferencedWords(List<File> candidateFiles) {
        Map<String, Map<String, Integer>> totalWordCount = new HashMap<>();
        List<Thread> threads = new ArrayList<>();

        for (File file : candidateFiles) {
            Thread thread = new Thread(() -> {
                List<String> fileLines = readFile(file);
                Map<String, Map<String, Integer>> wordCount = countWordsFile(file, fileLines);

                synchronized (totalWordCount) {
                    for (Map.Entry<String, Map<String, Integer>> entry : wordCount.entrySet()) {
                        String word = entry.getKey();
                        Map<String, Integer> fileCounts = entry.getValue();

                        totalWordCount.merge(word, fileCounts, (oldMap, newMap) -> {
                            newMap.forEach((fileName, count) -> oldMap.merge(fileName, count, Integer::sum));
                            return oldMap;
                        });
                    }
                }
            });

            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                LOGGER.error("Thread interrupted while waiting for completion", e);
            }
        }

        return sortMap(totalWordCount);
    }
```

## 4. Testing

## 5. Demonstration