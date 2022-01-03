package revature.com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import revature.com.bank.model.Transaction;
import revature.com.bank.repository.TransactionDao;

import javax.transaction.Transactional;
import java.util.List;

@Service("transactionService")
public class TransactionService {
    private TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao){
        this.transactionDao = transactionDao;
    }

    public List<Transaction> findAll() {
        return this.transactionDao.findAll();
    }

    public Transaction saveTransaction(Transaction transaction) {
        return this.transactionDao.save(transaction);
    }

    public Transaction findByTransactionID(Integer id) {
        return this.transactionDao.findByTransactionID(id).orElse(null);
    }

    public List<Transaction> findByAccountID(Integer id) {
        return this.transactionDao.findByAccountID(id).orElse(null);
    }

    public List<Transaction> findByTransactionTypeAndAccountID(Integer type, Integer id) {
        return this.transactionDao.findByTransactionTypeAndAccountID(type, id).orElse(null);
    }
}
