package easysalesassistant.api.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private String name;
    private String description;
    private float price;
    private float publicPrice;
    private boolean hasDiscount;
    private int piecesBox;
}
