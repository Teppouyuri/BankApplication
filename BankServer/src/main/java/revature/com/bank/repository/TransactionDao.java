package revature.com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import revature.com.bank.model.Transaction;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("transactionDao")
@Transactional
public interface TransactionDao extends JpaRepository<Transaction, Integer> {
      Optional<Transaction> findByTransactionID(Integer id);

      Optional<List<Transaction>> findByAccountID(Integer id);

      Optional<List<Transaction>> findByTransactionTypeAndAccountID(Integer type, Integer accountId);

}
