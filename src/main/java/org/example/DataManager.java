package org.example;

import org.example.model.Query;
import org.example.model.Record;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DataManager {

    private static DataManager instance;
    private final List<Record> records;

    private DataManager() {
        this.records = new ArrayList<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void addRecord(Record record) {
        records.add(record);
    }


    public String processQueryToString(Query query) {
        Optional<Integer> averageWaitingTime = processQuery(query);
        return averageWaitingTime.map(String::valueOf).orElse("-");
    }

    public Optional<Integer> processQuery(Query query) {

        List<Record> matchingRecords = records.stream()
                .filter(record -> matchesService(record, query))
                .filter(record -> matchesQuestion(record, query))
                .filter(record -> record.responseType() == query.responseType())
                .filter(record -> matchesDateRange(record, query))
                .toList();

        if (matchingRecords.isEmpty()) {
            return Optional.empty();
        }

        int averageWaitingTime = (int) matchingRecords.stream()
                .mapToInt(Record::waitingTime)
                .average()
                .orElse(0);

        return Optional.of(averageWaitingTime);
    }

    public void clearRecords() {
        records.clear();
    }

    private boolean matchesService(Record record, Query query) {

        if (query.service().id() == null) {
            return true;
        }

        if (!Objects.equals(record.service().id(), query.service().id())) {
            return false;
        }

        Integer recordVariationId = record.service().variationId();
        Integer queryVariationId = query.service().variationId();

        return queryVariationId == null || queryVariationId.equals(recordVariationId);
    }


    private boolean matchesQuestion(Record record, Query query) {

        if (query.question().typeId() == null) {
            return true;
        }

        if (!Objects.equals(record.question().typeId(), query.question().typeId())) {
            return false;
        }

        Integer recordCategoryId = record.question().categoryId();
        Integer queryCategoryId = query.question().categoryId();

        if(queryCategoryId != null && !queryCategoryId.equals(recordCategoryId)) {
            return false;
        }

        Integer recordSubCategoryId = record.question().subCategoryId();
        Integer querySubCategoryId = query.question().subCategoryId();

        return querySubCategoryId == null || querySubCategoryId.equals(recordSubCategoryId);
    }

    private boolean matchesDateRange(Record record, Query query) {
        LocalDate recordDate = record.date();
        LocalDate dateFrom = query.dateFrom();
        LocalDate dateTo = query.dateTo();

        boolean afterOrOnStart = !recordDate.isBefore(dateFrom);

        if(dateTo == null) {
            return afterOrOnStart;
        }
        boolean beforeOrOnEnd = !recordDate.isAfter(dateTo);

        return afterOrOnStart && beforeOrOnEnd;
    }
}
