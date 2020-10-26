package gr.daniil.covidstatistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CovidStatistics {

    private String date;
    private String country;
    private int confirmed;
    private int recovered;
    private int deaths;
}
