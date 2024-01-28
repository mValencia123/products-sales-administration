package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories")
@Setter
@Getter
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotBlank(message = "Description of category must not be empty")
    private String description;

    @JsonBackReference(value = "categories-tenants")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tenant")
    private Tenant idTenant;

    @JsonManagedReference(value = "products-categories")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idCategory")
    private List<Product> products;
}
