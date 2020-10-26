package gr.daniil.covidstatistics.controller;

import gr.daniil.covidstatistics.model.CovidReportDTO;
import gr.daniil.covidstatistics.service.Reader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RestApiController {

    Reader reader;

    public RestApiController(Reader reader) {
        this.reader = reader;
    }

    @GetMapping("/")
    public String main() {
        return "Hello";
    }

    @GetMapping("/covidStatistics")
    public CovidReportDTO covidStatistics() throws IOException {
        return reader.createReport();
    }
}
