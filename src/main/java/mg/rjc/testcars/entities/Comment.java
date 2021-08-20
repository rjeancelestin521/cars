package mg.rjc.testcars.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateComment;
    private String title;
    @Column(nullable = false)
    private String contents;
    @ManyToOne
    @JoinColumn(name = "car_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Car car;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AppUser user;
}
