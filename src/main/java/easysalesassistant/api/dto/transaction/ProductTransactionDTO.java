package easysalesassistant.api.dto.transaction;

import easysalesassistant.api.enums.Type;
import lombok.Data;

@Data
public class ProductTransactionDTO {
    Long idProduct;
    int amount;
    Type output;
    int piecesBox;

    public int getTotalAmount(){
        int pieces = this.getAmount();
        if(output == Type.BOX) pieces = piecesBox * pieces;
        return pieces;
    }
}
