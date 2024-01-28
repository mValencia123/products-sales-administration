package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tenants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tenant implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    @NotBlank(message = "Description must not be empty.")
    private String description;

    @JsonManagedReference(value = "providers-tenants")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idTenant")
    private List<Provider> providers;

    @JsonManagedReference(value = "categories-tenants")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idTenant")
    private List<Category> categories;

    @JsonManagedReference(value = "products-tenants")
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idTenant")
    private List<Product> products;
}
