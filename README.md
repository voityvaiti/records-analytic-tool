# Records analytic tool
## Overview
This project provides an analytical tool for evaluating customer support data, 
focusing on the time it takes for responses to customer inquiries across different 
services and question types. The program reads records of customer waiting times 
and processes queries to output average waiting times for specific criteria, 
helping improve customer satisfaction insights.

## Features
- **Process Waiting Timeline Records:** Records customer inquiries with details on service type, question type, date, and waiting time.
- **Query Average Waiting Time:** Provides the average waiting time for specific inquiries based on service type, question type, and date range.
- **Efficient Input Parsing:** Processes up to 100,000 lines of input.
- **Wildcard Queries:** Supports wildcard * for querying all services or question types.
- **Date Range Support:** Allows querying by a specific date or a range of dates.

## Technical Specifications
- **Input Format:** The input begins with a line indicating the number of lines (up to 100,000). Each line after the first is either:
  - **Waiting Timeline (C):** ```C service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date time```
  - **Query (D):** ```D service_id[.variation_id] question_type_id[.category_id.[sub-category_id]] P/N date_from[-date_to]```
- **Service ID and Question Type:** Can be specified as specific IDs or * for querying all services or question types.
- **Output:** For each query, the program outputs the average waiting time (rounded to minutes) or - if no data matches.

## Example
### Input:
```
7
C 1.1 8.15.1 P 15.10.2012 83
C 1 10.1 P 01.12.2012 65
C 1.1 5.5.1 P 01.11.2012 117
D 1.1 8 P 01.01.2012-01.12.2012
C 3 10.2 N 02.10.2012 100
D 1 * P 08.10.2012-20.11.2012
D 3 10 P 01.12.2012
```
### Output:
```
83
100
-
```

## Setup
1. **Prerequisites:** Java 17 or later.
2. **Compile and Run:**
   To compile: ```javac Main.java```
   To run: ```java Main```