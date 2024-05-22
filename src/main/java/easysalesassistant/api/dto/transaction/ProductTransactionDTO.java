package easysalesassistant.api.dto.transaction;

import easysalesassistant.api.enums.OutputType;
import lombok.Data;

@Data
public class ProductTransactionDTO {
    Long idProduct;
    int amount;
    OutputType output;
    int piecesBox;

    public int getTotalAmount(){
        int pieces = this.getAmount();
        if(output == OutputType.BOX) pieces = piecesBox * pieces;
        return pieces;
    }
}
