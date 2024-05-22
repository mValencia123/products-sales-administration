package easysalesassistant.api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String itemCode;

    @Column(length = 100)
    private String barCode;

    @Column(length = 30)
    private String name;

    @Column(length = 60)
    private String description;

    @Column(precision = 2)
    private float cost;

    @Column(precision = 2)
    private float wholesalePrice;

    @Column(precision = 2)
    private float retailPrice;

    private String photo;

    private boolean hasDiscount = true;

    private int piecesBox;

    private boolean deleted = false;

    private Date createdAt;

    private Date deletedAt;

    @ManyToOne()
    @JoinColumn(name = "id_user_created")
    private SystemUser idUserCreated;

    @ManyToOne()
    @JoinColumn(name = "id_user_deleted")
    private SystemUser idUserDeleted;

    @ManyToOne()
    @JoinColumn(name = "id_provider")
    private Provider idProvider;

    @ManyToOne()
    @JoinColumn(name = "id_category")
    private Category idCategory;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idProduct")
    private List<Stock> stock;
}
