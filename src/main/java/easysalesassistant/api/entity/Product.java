package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "products")
@Data
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String name;

    @Column(length = 60)
    private String description;

    @Column(precision = 2)
    private float price;

    @Column(precision = 2)
    private float publicPrice;

    private boolean hasDiscount;

    private int piecesBox;

    @JsonBackReference(value = "products-providers")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_provider")
    private Provider idProvider;

    @JsonBackReference(value = "products-categories")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_category")
    private Category idCategory;

    @JsonBackReference(value = "products-tenants")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tenant")
    private Tenant idTenant;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProduct")
    private List<Stock> stock;
}
