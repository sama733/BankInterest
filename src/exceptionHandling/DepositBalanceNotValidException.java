package exceptionHandling;

/**
 * Created by DOTIN SCHOOL 4 on 6/22/2016.
 */
public class DepositBalanceNotValidException extends Throwable {
    public DepositBalanceNotValidException(String message) {
        super(message);
    }
}
