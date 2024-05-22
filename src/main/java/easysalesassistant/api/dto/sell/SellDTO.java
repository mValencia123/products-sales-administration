package easysalesassistant.api.dto.sell;

import easysalesassistant.api.enums.SellType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SellDTO {
    private Long id;
    private Long idBranch;
    private List<ProductSellDTO> productsSell;
    @Enumerated(EnumType.STRING)
    private SellType sellType;
    private boolean isWholsale;
    private int discountPercent;
    private double discount;
    private double total;
    private Date deletedAt;
    private Long idUserSold;
    private Long idCustomer;
    private Long idUserDeleted;
}
