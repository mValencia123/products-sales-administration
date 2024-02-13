package easysalesassistant.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String description;
    private float price;
    private float publicPrice;
    private boolean hasDiscount;
    private int piecesBox;
}
