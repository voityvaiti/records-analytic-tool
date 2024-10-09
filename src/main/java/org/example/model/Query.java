package org.example.model;

import org.example.domain.Question;
import org.example.domain.ResponseType;
import org.example.domain.Service;
import org.example.pojo.DateRange;
import org.example.util.ParsingUtils;

import java.time.LocalDate;
import java.util.Optional;

public record Query(Service service, Question question, ResponseType responseType, LocalDate dateFrom,
                    LocalDate dateTo) {

    public static Optional<Query> parse(String line) {
        try {
            String[] parts = line.split(" ");
            if (parts.length != 5 || parts[0].charAt(0) != 'D') {
                throw new IllegalArgumentException("Invalid query format.");
            }

            Service service;
            if (parts[1].equals("*")) {
                service = new Service(null, null);
            } else {
                service = ParsingUtils.parseService(parts[1]);
            }

            Question question;
            if (parts[2].equals("*")) {
                question = new Question(null, null, null);
            } else {
                question = ParsingUtils.parseQuestion(parts[2]);
            }

            ResponseType responseType = ResponseType.valueOf(parts[3]);
            DateRange dateRange = ParsingUtils.parseDateRange(parts[4]);

            return Optional.of(new Query(service, question, responseType, dateRange.dateFrom(), dateRange.dateTo()));

        } catch (Exception e) {
            System.err.println("Line parsing error: " + line + " - " + e.getMessage());
            return Optional.empty();
        }
    }
}

