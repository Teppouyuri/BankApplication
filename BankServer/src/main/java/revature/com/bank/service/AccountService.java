package revature.com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import revature.com.bank.model.Account;
import revature.com.bank.repository.AccountDao;

import java.util.List;

@Service("accountService")
public class AccountService {
    private AccountDao accountDao;

    @Autowired
    public AccountService(AccountDao accountDao){
        this.accountDao = accountDao;
    }

    public Account createAccount(Account account){
        return this.accountDao.save(account);
    }

    public Account findByAccountId(Integer id){
        return this.accountDao.getById(id);
    }

    public List<Account> findAccountByUserId(Integer id){
        return this.accountDao.findAccountByUserId(id).orElse(null);
    }

    public void deposit(Integer amount, Integer accountID) {
        this.accountDao.deposit(amount, accountID);
    }

    public Boolean withdraw(Integer amount, Integer accountID) {
        if(findByAccountId(accountID).getBalance() >= amount) {
            this.accountDao.withdraw(amount, accountID);
            return true;
        }
        return false;
    }


}
