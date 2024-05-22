package easysalesassistant.api.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String itemCode;
    private String barCode;
    private String name;
    private String description;
    private float cost;
    private float wholesalePrice;
    private float retailPrice;
    private String photo;
    private boolean hasDiscount;
    private int piecesBox;
    private Date createdAt;
    private Date deletedAt;
    private Long idProvider;
    private Long idCategory;
}
