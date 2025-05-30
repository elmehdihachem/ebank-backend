package ma.emsi.ebankbackend.web;

import ma.emsi.ebankbackend.dtos.AccountOperationDTO;
import ma.emsi.ebankbackend.dtos.BankAccountDTO;
import ma.emsi.ebankbackend.exception.BankAccountNotFoundException;
import ma.emsi.ebankbackend.exception.CustomerNotFondException;
import ma.emsi.ebankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountRestAPI {
    private BankAccountService bankAccountService;
    private List<BankAccountDTO> list;

    public BankAccountRestAPI(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId) throws BankAccountNotFoundException {
        return bankAccountService.getBankAccount(accountId);
    }
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts(){
        return bankAccountService.bankAccountList();
    }
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId){
        return bankAccountService.accountHistory(accountId);
    }


}
