package finki.ukim.mk.emt.konstantinb.lab01.web;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import finki.ukim.mk.emt.konstantinb.lab01.models.ChargeRequest;
import finki.ukim.mk.emt.konstantinb.lab01.models.Transaction;
import finki.ukim.mk.emt.konstantinb.lab01.services.ProductService;
import finki.ukim.mk.emt.konstantinb.lab01.services.StripeService;
import finki.ukim.mk.emt.konstantinb.lab01.services.TransactionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Controller
public class CheckoutController {
    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private StripeService stripeService;
    private ProductService productService;
    private TransactionService transactionService;

    public CheckoutController(ProductService productService, StripeService stripService, TransactionService transactionService) {
        this.productService = productService;
        this.stripeService = stripService;
        this.transactionService = transactionService;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("/checkout/{id}")
    public String checkoutProduct(@PathVariable("id") String ProductID, Long ID, Model model) {
        model.addAttribute("product", productService.getById(ID));
        model.addAttribute("name", productService.getById(ID).getName());
        model.addAttribute("amount", (int) productService.getById(ID).getPrice() * 100);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.USD);
        return "checkoutProduct";
    }

    @PostMapping("/charge/{id}")
    public String chargeProduct(@PathVariable("id") Long ID, ChargeRequest chargeRequest, Model model) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.USD);
        Charge charge = stripeService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());

        if (charge.getStatus().equals("succeeded")) {
            Transaction transaction = new Transaction();
            transaction.setPurchasedProduct(productService.getById(ID));
            transaction.setUsername(chargeRequest.getStripeEmail());
            transaction.setTransactionDate(new Date());
            transaction.setAmount(productService.getById(ID).getPrice());
            transactionService.addNewTransaction(transaction);
        }
        return "checkoutResult";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "checkoutResult";
    }
}
