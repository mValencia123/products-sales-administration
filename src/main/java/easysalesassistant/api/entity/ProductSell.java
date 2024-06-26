package easysalesassistant.api.entity;

import easysalesassistant.api.enums.OutputType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "products_sells")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private double priceProduct;

    private int amount;

    @Enumerated(EnumType.STRING)
    private OutputType type;
}
