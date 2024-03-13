package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "branches")
@Setter
@Getter
public class Branch implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String description;

    @ManyToOne
    @JoinColumn(name="id_store")
    private Store idStore;

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
