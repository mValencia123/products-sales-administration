package easysalesassistant.api.dto.sell;

import easysalesassistant.api.enums.OutputType;
import lombok.Data;

@Data
public class ProductSellDTO {
    Long idProduct;
    double priceProduct;
    int amount;
    OutputType output;
}
