package gr.daniil.covidstatistics.model;

import lombok.Data;

import java.util.Map;

@Data
public class CovidReportDTO {
    CovidStatistics maxConfirmedWorld;
    long numberOfRecordsForGreece;
    double averageDeathsWorld;
    Map<String, Integer> maxDeathsPerCountry;
}
