package easysalesassistant.api.services;

import easysalesassistant.api.dao.ITransactionDAO;
import easysalesassistant.api.dto.transaction.TransactionDTO;
import easysalesassistant.api.entity.*;
import easysalesassistant.api.exceptions.NotFoundProductException;
import easysalesassistant.api.mappers.ProductTransactionMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ITransactionServiceImp implements ITransactionService {

    IStoreService storeService;
    IBranchService branchService;
    ISystemUserService userService;
    IProductService productService;
    ProductTransactionMapper productTransactionMapper;
    ITransactionDAO transactionDAO;

    ITransactionServiceImp(IStoreService storeService, IBranchService branchService, ISystemUserService userService,IProductService productService,ProductTransactionMapper productTransactionMapper,ITransactionDAO transactionDAO){
        this.storeService = storeService;
        this.branchService = branchService;
        this.userService = userService;
        this.productService = productService;
        this.productTransactionMapper = productTransactionMapper;
        this.transactionDAO = transactionDAO;
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Store origin      = storeService.existsStoreById(transactionDTO.getIdOrigin());
        Store destination = storeService.existsStoreById(transactionDTO.getIdDestination());
        Branch branch = branchService.existsBranchById(transactionDTO.getIdBranch());
        SystemUser userCreated = userService.existsSystemUser(transactionDTO.getIdUserCreated());

        transactionDTO.getProducts().forEach((p) -> {
            productService.productHasStock(p.getIdProduct(),origin,p.getTotalAmount());
        });

        List<ProductTransaction> products = transactionDTO.getProducts()
                .stream()
                .map((p) -> productTransactionMapper.productTransactionDTOToProductTransaction(p))
                .collect(Collectors.toList());

        Transaction transaction = Transaction.builder()
                .branch(branch)
                .origin(origin)
                .destination(destination)
                .createdAt(new Date())
                .comment(transactionDTO.getComment())
                .products(products)
                .userCreated(userCreated)
                .build();

        transactionDAO.save(transaction);
        return transactionDTO;
    }
}
