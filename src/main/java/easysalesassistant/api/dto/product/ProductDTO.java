package easysalesassistant.api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long idProduct;
    private String name;
    private String description;
    private float price;
    private float publicPrice;
    private boolean hasDiscount;
    private int piecesBox;
    private Date createdAt;
    private Date deletedAt;
}
