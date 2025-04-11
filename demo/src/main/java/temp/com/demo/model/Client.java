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
@Table(name = "Client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String dni;
    private String phoneNumber;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Case> cases;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Receipt> receipts;
}
