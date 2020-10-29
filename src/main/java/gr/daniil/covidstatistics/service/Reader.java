package gr.daniil.covidstatistics.service;

import gr.daniil.covidstatistics.model.CovidReportDTO;
import gr.daniil.covidstatistics.model.CovidStatistics;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@Service
public class Reader {

    private static String URL_STRING = "https://raw.githubusercontent.com/datasets/covid-19/master/data/countries-aggregated.csv";

    public CovidReportDTO createReport() throws IOException {
        List<CovidStatistics> covidStatisticsList = parseUrl(URL_STRING);
        CovidReportDTO covidReportDTO = new CovidReportDTO();
        covidReportDTO.setNumberOfRecordsForGreece(calculateNumberOfRecordsForGreece(covidStatisticsList));
        covidReportDTO.setMaxConfirmedWorld(calculateMaxConfirmed(covidStatisticsList));
        Map<String, Optional<CovidStatistics>> map = groupByMaxDeaths(covidStatisticsList);
        covidReportDTO.setMaxDeathsPerCountry(calculateMaxDeathsPerCountry(map));
        covidReportDTO.setAverageDeathsWorld(calculateAverageDeaths(map));
        return covidReportDTO;
    }

    private List<CovidStatistics> parseUrl(String urlString) throws IOException {
        URL url = new URL(urlString);
        ArrayList<CovidStatistics> covidStatisticsArrayList = new ArrayList<>();
        try (InputStream in = url.openStream();
             InputStreamReader inr = new InputStreamReader(in);
             BufferedReader br = new BufferedReader(inr)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {

                line = line.replace("\"Korea, South\"", "South Korea ");
                //Best case: no String separation, no ; contained in data items. If not
                //you need some other way to split this
                String[] elements = line.split(",");

                //Deal with that one line

                try {
                    covidStatisticsArrayList.add(new CovidStatistics(elements[0],
                            elements[1],
                            parseInt(elements[2]),
                            parseInt(elements[3]),
                            parseInt(elements[4])));

                } catch (Exception e) {
                    System.out.println("A record was skipped for being invalid.");
                }

                //Get next line
                line = br.readLine();
            }
        }
        return covidStatisticsArrayList;
    }

    private CovidStatistics calculateMaxConfirmed(List<CovidStatistics> covidStatisticsList) {
        return covidStatisticsList
                .stream()
                .max(Comparator.comparing(CovidStatistics::getConfirmed)).get();
    }

    private long calculateNumberOfRecordsForGreece(List<CovidStatistics> covidStatisticsList) {
        return covidStatisticsList
                .stream()
                .filter(covidStatistics -> covidStatistics.getCountry().equals("Greece"))
                .count();
    }

    private Map<String, Optional<CovidStatistics>> groupByMaxDeaths(List<CovidStatistics> covidStatisticsList) {
        return covidStatisticsList
                .stream()
                .collect(Collectors.groupingBy(covidStatistics -> covidStatistics.getCountry(),
                        Collectors.maxBy(Comparator.comparing(c -> c.getDeaths()))));
    }

    private Map<String, Integer> calculateMaxDeathsPerCountry(Map<String, Optional<CovidStatistics>> groupedByMaxDeathsMap) {
        return groupedByMaxDeathsMap
                .entrySet()
                .stream()
                .collect(Collectors
                        .toMap(e -> e.getKey(), e -> e.getValue().get().getDeaths()));
    }

    private double calculateAverageDeaths(Map<String, Optional<CovidStatistics>> maxConfirmedPerCountry) {
        return maxConfirmedPerCountry
                .values()
                .stream()
                .map(Optional::get)
                .mapToDouble(CovidStatistics::getDeaths)
                .average()
                .getAsDouble();
    }


}
