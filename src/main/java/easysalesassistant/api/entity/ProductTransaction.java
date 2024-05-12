package easysalesassistant.api.entity;

import easysalesassistant.api.enums.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "products_transaction")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product idProduct;

    @ManyToOne
    @JoinColumn(name = "id_transaction")
    private Transaction idTransaction;

    private Type output;

    private int amount;

    private int piecesBox;
}
