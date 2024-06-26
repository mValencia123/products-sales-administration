package easysalesassistant.api.entity;


import easysalesassistant.api.enums.OutputType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Table(name = "products_reception")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReception implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product idProduct;

    @ManyToOne
    @JoinColumn(name = "id_reception")
    private Reception idReception;

    @Enumerated(EnumType.STRING)
    private OutputType output;

    private int amount;

    private int piecesBox;
}