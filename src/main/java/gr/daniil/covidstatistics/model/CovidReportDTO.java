package gr.daniil.covidstatistics.model;

import lombok.Data;

import java.util.Map;
import java.util.Optional;

@Data
public class CovidReportDTO {
    CovidStatistics maxConfirmedWorld;
    long numberOfRecordsForGreece;
    double averageDeathsWorld;
    Map<String, Optional<CovidStatistics>> maxDeathsPerCountry;
}
