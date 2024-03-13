package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "providers")
@Setter
@Getter
public class Provider implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    private String rfc;

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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProvider")
    private List<Product> products;
}
