package revature.com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import revature.com.bank.model.JsonResponse;
import revature.com.bank.model.Transaction;
import revature.com.bank.service.TransactionService;

@RestController("transactionController")
@CrossOrigin(value = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value="/transaction")
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse saveTransaction(@RequestBody Transaction transaction) {
        return new JsonResponse(true, "Transaction added", this.transactionService.saveTransaction(transaction));
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse getAllTransaction(){
        return new JsonResponse(true, "Get all transactions", this.transactionService.findAll());
    }

    @GetMapping(path= "/findByAccountId/{accountId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse findByAccountId(@PathVariable("accountId") Integer accountId){
        return new JsonResponse(true, "Get all transaction by account ID", this.transactionService.findByAccountID(accountId));
    }

    @GetMapping(path = "/findDeposits/{accountId}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse findDeposits(@PathVariable("accountId") Integer id) {
        return new JsonResponse(true, "Find deposits by Id", this.transactionService.findByTransactionTypeAndAccountID(0, id));
    }

    @GetMapping(path = "/findWithdrawals/{accountId}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse findWithdrawals(@PathVariable("accountId") Integer id) {
        return new JsonResponse(true, "Find withdrawals by Id", this.transactionService.findByTransactionTypeAndAccountID(1, id));
    }

    @GetMapping(path = "/findTransfers/{accountId}", produces =  MediaType.APPLICATION_JSON_VALUE)
    public JsonResponse findTransfers(@PathVariable("accountId") Integer id) {
        return new JsonResponse(true, "Find transfers by Id", this.transactionService.findByTransactionTypeAndAccountID(2, id));
    }


}
