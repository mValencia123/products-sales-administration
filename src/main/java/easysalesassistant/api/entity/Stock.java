package easysalesassistant.api.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "stocks")
public class Stock implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn( name = "id_product")
    private Product idProduct;

    @ManyToOne
    @JoinColumn( name = "id_store")
    private Store idStore;

    private Long amount;
}
