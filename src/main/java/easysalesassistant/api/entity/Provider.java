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
    private String description;

    @JsonBackReference(value = "providers-tenants")
    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tenant")
    private Tenant idTenant;

    @JsonManagedReference(value = "products-providers")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProvider")
    private List<Product> products;
}
