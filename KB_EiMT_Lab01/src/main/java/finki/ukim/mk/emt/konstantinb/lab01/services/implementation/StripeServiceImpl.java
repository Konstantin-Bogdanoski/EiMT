package finki.ukim.mk.emt.konstantinb.lab01.services.implementation;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Charge;
import finki.ukim.mk.emt.konstantinb.lab01.models.ChargeRequest;
import finki.ukim.mk.emt.konstantinb.lab01.services.StripeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
@Service
public class StripeServiceImpl implements StripeService {

    @Value("${STRIPE_SECRET_KEY}")
    private String stripePrivateKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripePrivateKey;
    }

    public Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, CardException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        return Charge.create(chargeParams);
    }
}
