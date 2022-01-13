package revature.com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import revature.com.bank.model.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository("userDao")
@Transactional
public interface UserDao extends JpaRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Integer id);
}
