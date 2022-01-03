package revature.com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import revature.com.bank.model.Account;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository("accountDao")
@Transactional
public interface AccountDao extends JpaRepository<Account, Integer> {

    Optional<List<Account>> findAccountByUserId(Integer id);

    @Modifying
    @Query("update Account a SET a.balance = a.balance + :amount WHERE a.accountId = :accountID")
    public void deposit(@PathVariable("amount") Integer amount, @PathVariable("accountID") Integer accountID);

    @Modifying
    @Query("update Account a SET a.balance = a.balance - :amount WHERE a.accountId = :accountID")
    public void withdraw(@PathVariable("amount") Integer amount, @PathVariable("accountID") Integer accountID);
}
