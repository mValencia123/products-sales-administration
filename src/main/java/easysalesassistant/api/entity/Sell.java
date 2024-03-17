package easysalesassistant.api.entity;

import easysalesassistant.api.enums.SellType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = "sells")
@Data
public class Sell implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date soldAt;

    private SellType sellType;

    private boolean isWholsale;

    private Long invoice;

    private int discountPercent;

    private Long discount;

    private Long total;

    private Date deletedAt;

    @ManyToOne
    @JoinColumn(name = "id_user_sold")
    private SystemUser idUserSold;

    @ManyToOne
    @JoinColumn(name = "id_user_buy")
    private SystemUser idUserBuy;

    @ManyToOne
    @JoinColumn(name = "id_user_deleted")
    private SystemUser idUserDeleted;

    @ManyToOne
    @JoinColumn(name = "id_branch")
    private Branch idBranch;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "idSell")
    Set<ProductSell> productSellSet = new HashSet<>();
}
