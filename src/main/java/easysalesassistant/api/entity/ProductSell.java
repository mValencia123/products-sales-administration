package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "products_sells")
@Setter
@Getter
public class ProductSell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "id_product")
    private Product idProduct;

    @ManyToOne
    @JoinColumn(name = "id_sell")
    private Sell idSell;

    @Column(precision = 2)
    private float priceProduct;

    private int amount;
}
