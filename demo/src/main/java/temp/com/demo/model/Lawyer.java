package temp.com.demo.model;

import lombok.*;
import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Lawyer")
public class Lawyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dni;
    private String phoneNumber;
    private String mailBox;

    @JsonIgnore
    @OneToMany(mappedBy = "lawyer")
    private List<Case> cases;

    @JsonIgnore
    @OneToMany(mappedBy = "lawyer")
    private List<Receipt> receipts;
}