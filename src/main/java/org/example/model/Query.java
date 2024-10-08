package org.example.model;

import org.example.domain.Question;
import org.example.domain.ResponseType;
import org.example.domain.Service;
import org.example.pojo.DateRange;
import org.example.util.ParsingUtils;

import java.time.LocalDate;
import java.util.Optional;

public class Query {

    private final Service service;
    private final Question question;
    private final ResponseType responseType;
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public Query(Service service, Question question, ResponseType responseType, LocalDate dateFrom, LocalDate dateTo) {
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public static Optional<Query> parse(String line) {
        try {
            String[] parts = line.split(" ");
            if (parts.length != 5 || parts[0].charAt(0) != 'D') {
                throw new IllegalArgumentException("Invalid query format.");
            }

            Service service;
            if(parts[1].equals("*")) {
                service = new Service(null, null);
            } else {
                service = ParsingUtils.parseService(parts[1]);
            }

            Question question;
            if(parts[2].equals("*")) {
                question = new Question(null, null, null);
            } else {
                question = ParsingUtils.parseQuestion(parts[2]);
            }

            ResponseType responseType = ResponseType.valueOf(parts[3]);
            DateRange dateRange = ParsingUtils.parseDateRange(parts[4]);

            return Optional.of(new Query(service, question, responseType, dateRange.getDateFrom(), dateRange.getDateTo()));

        } catch (Exception e) {
            System.err.println("Line parsing error: " + line + " - " + e.getMessage());
            return Optional.empty();
        }
    }

    public Service getService() { return service; }
    public Question getQuestion() { return question; }
    public ResponseType getResponseType() { return responseType; }
    public LocalDate getDateFrom() { return dateFrom; }
    public LocalDate getDateTo() { return dateTo; }
}

