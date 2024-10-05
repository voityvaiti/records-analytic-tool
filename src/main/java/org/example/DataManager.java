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
                .filter(record -> record.getResponseType() == query.getResponseType())
                .filter(record -> matchesDateRange(record, query))
                .toList();

        if (matchingRecords.isEmpty()) {
            return Optional.empty();
        }

        int averageWaitingTime = (int) matchingRecords.stream()
                .mapToInt(Record::getWaitingTime)
                .average()
                .orElse(0);

        return Optional.of(averageWaitingTime);
    }

    public void clearRecords() {
        records.clear();
    }

    private boolean matchesService(Record record, Query query) {

        if (query.getService().getId() == null) {
            return true;
        }

        if (!Objects.equals(record.getService().getId(), query.getService().getId())) {
            return false;
        }

        Integer recordVariationId = record.getService().getVariationId();
        Integer queryVariationId = query.getService().getVariationId();

        return queryVariationId == null || queryVariationId.equals(recordVariationId);
    }


    private boolean matchesQuestion(Record record, Query query) {

        if (query.getQuestion().getTypeId() == null) {
            return true;
        }

        if (!Objects.equals(record.getQuestion().getTypeId(), query.getQuestion().getTypeId())) {
            return false;
        }

        Integer recordCategoryId = record.getQuestion().getCategoryId();
        Integer queryCategoryId = query.getQuestion().getCategoryId();

        if(queryCategoryId != null && !queryCategoryId.equals(recordCategoryId)) {
            return false;
        }

        Integer recordSubCategoryId = record.getQuestion().getSubCategoryId();
        Integer querySubCategoryId = query.getQuestion().getSubCategoryId();

        return querySubCategoryId == null || querySubCategoryId.equals(recordSubCategoryId);
    }

    private boolean matchesDateRange(Record record, Query query) {
        LocalDate recordDate = record.getDate();
        LocalDate dateFrom = query.getDateFrom();
        LocalDate dateTo = query.getDateTo();

        boolean afterOrOnStart = !recordDate.isBefore(dateFrom);

        if(dateTo == null) {
            return afterOrOnStart;
        }
        boolean beforeOrOnEnd = !recordDate.isAfter(dateTo);

        return afterOrOnStart && beforeOrOnEnd;
    }
}
