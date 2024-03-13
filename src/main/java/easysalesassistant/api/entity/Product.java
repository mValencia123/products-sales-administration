package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
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

    private boolean hasDiscount = true;

    private int piecesBox;

    private boolean visible = true;

    private boolean deleted = false;

    private Date createdAt;

    private Date deletedAt;

    @ManyToOne()
    @JoinColumn(name = "id_user_created")
    private SystemUser userCreated;

    @ManyToOne()
    @JoinColumn(name = "id_user_deleted")
    private SystemUser userDeleted;

    @ManyToOne()
    @JoinColumn(name = "id_provider")
    private Provider idProvider;

    @ManyToOne()
    @JoinColumn(name = "id_category")
    private Category idCategory;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProduct")
    private List<Stock> stock;
}
