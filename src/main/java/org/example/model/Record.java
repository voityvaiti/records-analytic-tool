package org.example.model;

import org.example.domain.Question;
import org.example.domain.ResponseType;
import org.example.domain.Service;
import org.example.util.ParsingUtils;

import java.time.LocalDate;
import java.util.Optional;

public class Record {

    private final Service service;
    private final Question question;
    private final ResponseType responseType;
    private final LocalDate date;
    private final int waitingTime;

    public Record(Service service, Question question, ResponseType responseType, LocalDate date, int waitingTime) {
        this.service = service;
        this.question = question;
        this.responseType = responseType;
        this.date = date;
        this.waitingTime = waitingTime;
    }

    public static Optional<Record> parse(String line) {
        try {
            String[] parts = line.split(" ");
            if (parts.length != 6 || parts[0].charAt(0) != 'C') {
                throw new IllegalArgumentException("Invalid record format.");
            }

            Service service = ParsingUtils.parseService(parts[1]);
            Question question = ParsingUtils.parseQuestion(parts[2]);
            ResponseType responseType = ResponseType.valueOf(parts[3]);
            LocalDate date = ParsingUtils.parseDate(parts[4]);
            int waitingTime = Integer.parseInt(parts[5]);

            return Optional.of(new Record(service, question, responseType, date, waitingTime));

        } catch (Exception e) {
            System.err.println("String parsing error: " + line + " - " + e.getMessage());
            return Optional.empty();
        }
    }

    public Service getService() { return service; }
    public Question getQuestion() { return question; }
    public ResponseType getResponseType() { return responseType; }
    public LocalDate getDate() { return date; }
    public int getWaitingTime() { return waitingTime; }
}


