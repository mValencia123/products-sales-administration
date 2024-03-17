package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Table(name = "addresses")
@Entity
public class Address implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="id_state")
    private State idState;

    @ManyToOne
    @JoinColumn(name="id_city")
    private City idCity;

    private String street;

    private Long number;

    private Long postalCode;

    private String suburb;
}
