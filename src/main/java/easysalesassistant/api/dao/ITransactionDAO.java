package easysalesassistant.api.dao;

import easysalesassistant.api.entity.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionDAO extends CrudRepository<Transaction,Long>  {
}
