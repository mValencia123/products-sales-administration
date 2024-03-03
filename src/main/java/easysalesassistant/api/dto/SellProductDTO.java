package easysalesassistant.api.dto;

import lombok.Data;

@Data
public class SellProductDTO {
    Long idProduct;
    Float price;
    int amount;
    String output;
}
