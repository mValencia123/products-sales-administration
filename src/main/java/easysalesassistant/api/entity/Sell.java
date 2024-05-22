package easysalesassistant.api.entity;

import easysalesassistant.api.enums.SellType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "sells")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date soldAt;

    @Enumerated(EnumType.STRING)
    private SellType sellType;

    private boolean isWholsale;

    private int discountPercent;

    private double discount;

    private double total;

    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "id_user_sold")
    private SystemUser idUserSold;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private SystemUser idCustomer;

    @ManyToOne
    @JoinColumn(name = "id_user_deleted")
    private SystemUser idUserDeleted;

    @ManyToOne
    @JoinColumn(name = "id_branch")
    private Branch idBranch;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idSell")
    Set<ProductSell> productSellSet = new HashSet<>();
}
