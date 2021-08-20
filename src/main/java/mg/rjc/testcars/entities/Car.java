package mg.rjc.testcars.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNumber;
    private String mark;
    private String model;
    private String color;
    private String year;
    private String photoName;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
