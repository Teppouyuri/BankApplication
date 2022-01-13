package revature.com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import revature.com.bank.model.Account;
import revature.com.bank.model.JsonResponse;
import revature.com.bank.service.AccountService;

import java.util.List;

@RestController("accountController")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value="/account")
public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public JsonResponse createAccount(@RequestBody Account account){
        return new JsonResponse(true, "Account created", this.accountService.createAccount(account));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse findByUser(@PathVariable Integer id){
        return new JsonResponse(true, "Accounts by ID", this.accountService.findAccountByUserId(id));
    }

    @PostMapping(path = "/deposit/{amount}/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse deposit(@PathVariable("amount") Integer amount, @PathVariable("accountId") Integer accountID) {
        this.accountService.deposit(amount, accountID);
        return new JsonResponse(true, "Money deposited", null);
    }

    @PostMapping(path = "/withdraw/{amount}/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse withdraw(@PathVariable("amount") Integer amount, @PathVariable("accountId") Integer accountID) {
        if (this.accountService.withdraw(amount, accountID)){
            return new JsonResponse(true, "Money withdrawn", null);
        }
        return new JsonResponse(false, "Withdrawals money is exceed your balance", null);

    }

    @PostMapping(path = "/transfer/{amount}/{fromAccountId}/{toAccountId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse transfer(@PathVariable("amount") Integer amount, @PathVariable("fromAccountId") Integer from,
                                 @PathVariable("toAccountId") Integer to) {
        this.accountService.withdraw(amount, from);
        this.accountService.deposit(amount, to);
        return new JsonResponse(true, "Money transferred", null);
    }


}
