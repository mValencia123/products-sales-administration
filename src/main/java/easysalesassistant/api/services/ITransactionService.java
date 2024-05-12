package easysalesassistant.api.services;

import easysalesassistant.api.dto.transaction.TransactionDTO;

public interface ITransactionService {
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
}
