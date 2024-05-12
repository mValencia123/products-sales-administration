package easysalesassistant.api.dto.transaction;

import easysalesassistant.api.enums.StatusTransaction;
import lombok.Data;

import java.util.List;

@Data
public class TransactionDTO {
    Long id;
    List<ProductTransactionDTO> products;
    Long idOrigin;
    Long idDestination;
    String comment;
    Long idBranch;
    StatusTransaction status;
    Long idUserCreated;
}
