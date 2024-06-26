package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "stocks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    @ManyToOne
    @JoinColumn( name = "id_product")
    private Product idProduct;

    @ManyToOne
    @JoinColumn( name = "id_store")
    private Store idStore;

    @ManyToOne
    @JoinColumn(name = "id_user_created")
    private SystemUser idUserCreated;
}
