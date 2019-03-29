package finki.ukim.mk.emt.konstantinb.lab01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Manufacturer Not Found Exception")
public class ManufacturerNotFoundException extends RuntimeException {
}
