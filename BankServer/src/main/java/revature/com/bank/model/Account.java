package revature.com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "accountID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer accountId;
    @Column(name = "userID")
    private Integer userId;
    @Column(name = "accountType")
    private Integer accountType; //0 = checking, 1 = savings
    @Column(name = "balance")
    private Integer balance;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="accountID")
    private List<Transaction> transactions;
}
