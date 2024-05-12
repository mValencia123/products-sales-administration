package easysalesassistant.api.controllers;

import easysalesassistant.api.dto.transaction.TransactionDTO;
import easysalesassistant.api.entity.Transaction;
import easysalesassistant.api.services.ITransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "api/transactions")
public class TransactionController {

    ITransactionService transactionService;

    TransactionController(ITransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping(value = {"/",""})
    public TransactionDTO createTransaction(TransactionDTO transactionDTO){
        return transactionService.createTransaction(transactionDTO);
    }
}
