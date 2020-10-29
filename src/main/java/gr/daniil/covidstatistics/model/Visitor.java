package gr.daniil.covidstatistics.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private Date date_created;

    //constructor
    public Visitor(String name) {
        this.name = name;
        this.date_created = new Date();
    }
}
