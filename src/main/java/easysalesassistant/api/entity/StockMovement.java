package easysalesassistant.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "stock_movements")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement implements Serializable {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product idProduct;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private SystemUser idUser;

    @PrePersist
    public void prepersist(){
        createdAt = new Date();
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    private Long amountBefore;

    private Long amountAfter;
}
