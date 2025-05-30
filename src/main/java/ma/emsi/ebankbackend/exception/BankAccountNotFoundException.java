package ma.emsi.ebankbackend.exception;

public class BankAccountNotFoundException extends Exception{
    public BankAccountNotFoundException(String bankAccuntNotFound) {
        super(bankAccuntNotFound);
    }
}
