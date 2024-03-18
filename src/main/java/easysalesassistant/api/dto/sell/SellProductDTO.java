package easysalesassistant.api.dto.sell;

import lombok.Data;

@Data
public class SellProductDTO {
    Long idProduct;
    Float price;
    int amount;
    String output;
}
