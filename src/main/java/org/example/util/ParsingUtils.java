package org.example.util;

import org.example.domain.Question;
import org.example.domain.Service;
import org.example.pojo.DateRange;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParsingUtils {

    private static final DateTimeFormatter DATE_FORMATTER_QUERY = DateTimeFormatter.ofPattern("d.M.yyyy");
    private static final DateTimeFormatter DATE_FORMATTER_RECORD = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private ParsingUtils() {}

    public static Service parseService(String line) {

        String[] serviceParts = line.split("\\.");
        int serviceId = Integer.parseInt(serviceParts[0]);
        Integer variationId = (serviceParts.length > 1) ? Integer.parseInt(serviceParts[1]) : null;
        return new Service(serviceId, variationId);
    }

    public static Question parseQuestion(String line) {

        String[] questionParts = line.split("\\.");
        int questionTypeId = Integer.parseInt(questionParts[0]);
        Integer categoryId = (questionParts.length > 1) ? Integer.parseInt(questionParts[1]) : null;
        Integer subCategoryId = (questionParts.length > 2) ? Integer.parseInt(questionParts[2]) : null;
        return new Question(questionTypeId, categoryId, subCategoryId);
    }

    public static DateRange parseDateRange(String line) {

        String[] dateRange = line.split("-");
        LocalDate dateFrom = LocalDate.parse(dateRange[0], DATE_FORMATTER_QUERY);
        LocalDate dateTo = dateRange.length > 1 ? LocalDate.parse(dateRange[1], DATE_FORMATTER_QUERY) : null;
        return new DateRange(dateFrom, dateTo);
    }

    public static LocalDate parseDate(String line) {
        return LocalDate.parse(line, DATE_FORMATTER_RECORD);
    }
}

