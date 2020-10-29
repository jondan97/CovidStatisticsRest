package gr.daniil.covidstatistics.service;

import gr.daniil.covidstatistics.model.Visitor;
import gr.daniil.covidstatistics.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorService {

    private VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public void saveVisitor(Visitor visitor) {
        visitorRepository.save(visitor);
    }

    public List<Visitor> findVisitors() {
        return visitorRepository.findAll();
    }
}
