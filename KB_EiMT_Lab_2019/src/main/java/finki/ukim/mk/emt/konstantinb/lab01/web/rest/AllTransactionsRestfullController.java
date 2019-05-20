package finki.ukim.mk.emt.konstantinb.lab01.web.rest;

import finki.ukim.mk.emt.konstantinb.lab01.models.Transaction;
import finki.ukim.mk.emt.konstantinb.lab01.services.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@RestController
@RequestMapping("/rest/transactions")
public class AllTransactionsRestfullController {
    private TransactionService transactionService;

    public AllTransactionsRestfullController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}
