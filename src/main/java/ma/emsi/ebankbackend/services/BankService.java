package ma.emsi.ebankbackend.services;

import jakarta.transaction.Transactional;
import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.entities.CurrentAccount;
import ma.emsi.ebankbackend.entities.SavingAccount;
import ma.emsi.ebankbackend.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount =
                bankAccountRepository.findById("67c6839d-2bd2-4ace-8987-c4b8462f0956").orElse(null);
        if(bankAccount != null){
            System.out.println("*******************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreatedate());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println("Over Draft=>" + ((CurrentAccount) bankAccount).getOverdraft());
            }else if(bankAccount instanceof SavingAccount){
                System.out.println("Rate=>" + ((SavingAccount) bankAccount).getInterestRate());
            }
            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOpersationDate() + "\t" +op.getAamount());
            });
        }
    }
}
