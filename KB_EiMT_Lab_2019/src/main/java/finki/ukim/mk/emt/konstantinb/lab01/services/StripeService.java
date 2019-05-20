package finki.ukim.mk.emt.konstantinb.lab01.services;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import finki.ukim.mk.emt.konstantinb.lab01.models.ChargeRequest;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardException;

/**
 * @author Konstantin Bogdanoski (konstantin.b@live.com)
 */
public interface StripeService {
    Charge charge(ChargeRequest chargeRequest) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, com.stripe.exception.CardException;
}
