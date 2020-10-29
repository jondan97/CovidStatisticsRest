package gr.daniil.covidstatistics.controller;

import gr.daniil.covidstatistics.model.CovidReportDTO;
import gr.daniil.covidstatistics.model.Visitor;
import gr.daniil.covidstatistics.service.Reader;
import gr.daniil.covidstatistics.service.VisitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class RestApiController {

    Reader reader;
    VisitorService visitorService;

    public RestApiController(Reader reader, VisitorService visitorService) {
        this.visitorService = visitorService;
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

    @GetMapping("/add/{name}")
    public void addVisitor(@PathVariable String name,
                           HttpServletResponse response) throws IOException {
        visitorService.saveVisitor(new Visitor(name));
        response.sendRedirect("/view");
    }

    @GetMapping("/view")
    public List<Visitor> findVisitors() {
        return visitorService.findVisitors();
    }
}
