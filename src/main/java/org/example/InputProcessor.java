package org.example;

import org.example.model.Query;
import org.example.model.Record;

import java.util.Optional;

public class InputProcessor {

    private final DataManager dataManager;

    public InputProcessor(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void processInput(String input) {
        String[] lines = input.split("\\R");

        int expectedLineCount = Integer.parseInt(lines[0].trim());

        if (lines.length - 1 != expectedLineCount) {
            System.err.println("Warning: Expected " + expectedLineCount + " lines, but found " + (lines.length - 1));
        }

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();

            if (line.startsWith("C")) {
                Optional<Record> record = Record.parse(line);
                record.ifPresent(dataManager::addRecord);

            } else if (line.startsWith("D")) {
                Optional<Query> query = Query.parse(line);
                query.ifPresent(q -> System.out.println(dataManager.processQueryToString(q)));
            }
        }
        dataManager.clearRecords();
    }
}

