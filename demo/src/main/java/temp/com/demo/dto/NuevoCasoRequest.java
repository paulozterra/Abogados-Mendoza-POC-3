package temp.com.demo.dto;

// import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NuevoCasoRequest {
    private String dni;
    private String nombre;
    private String email;
    private String telefono;
    private String caseNumber;
    // @JsonProperty("lawyerId")
    // private Long lawyerId;
}