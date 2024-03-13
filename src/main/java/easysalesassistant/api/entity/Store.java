package easysalesassistant.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "stores")
@Setter
@Getter
public class Store implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String description;

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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idStore")
    private List<Stock> stock;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idStore")
    private List<Branch> branch;
}
