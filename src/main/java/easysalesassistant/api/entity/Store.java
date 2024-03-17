package easysalesassistant.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
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

    private Date createdAt;

    private Long phoneNumber;

    @OneToOne
    @JoinColumn(name="id_address")
    private Address idAddress;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idStore")
    private List<Stock> stock;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idStore")
    private List<Branch> branch;
}
