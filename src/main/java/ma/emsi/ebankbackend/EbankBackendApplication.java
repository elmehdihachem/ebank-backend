package ma.emsi.ebankbackend;

import ma.emsi.ebankbackend.dtos.BankAccountDTO;
import ma.emsi.ebankbackend.dtos.CurrentBankAccountDTO;
import ma.emsi.ebankbackend.dtos.CustomerDTO;
import ma.emsi.ebankbackend.dtos.SavingBankAccountDTO;
import ma.emsi.ebankbackend.entities.*;
import ma.emsi.ebankbackend.enums.AccountStatus;
import ma.emsi.ebankbackend.enums.OperationType;
import ma.emsi.ebankbackend.exception.BalanceNotSufficientException;
import ma.emsi.ebankbackend.exception.BankAccountNotFoundException;
import ma.emsi.ebankbackend.exception.CustomerNotFondException;
import ma.emsi.ebankbackend.repositories.AccountOperationRepository;
import ma.emsi.ebankbackend.repositories.BankAccountRepository;
import ma.emsi.ebankbackend.repositories.CustomerRepository;
import ma.emsi.ebankbackend.services.BankAccountService;
import ma.emsi.ebankbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class EbankBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountService bankAccountService) {
        return args -> {
            Stream.of("Hanaa","Houda","Nouhaila").forEach(name->{
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer -> {
                try {
                    bankAccountService.saveCurrentBankAccount(100+Math.random()*90000,9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(100+Math.random()*120000,5.5,customer.getId());

                } catch (CustomerNotFondException e) {
                    e.printStackTrace();
                }
            });
            List<BankAccountDTO> bankAccounts=bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount : bankAccounts) {
                for (int i = 0;i<10;i++){
                    String accuntId;
                    if (bankAccount instanceof SavingBankAccountDTO){
                        accuntId=((SavingBankAccountDTO)bankAccount).getId();
                    }else {
                        accuntId=((CurrentBankAccountDTO)bankAccount).getId();

                    }
                    bankAccountService.credit(accuntId,10000+Math.random()*120000,"Credit");
                    bankAccountService.debit(accuntId,10000+Math.random()*9000,"Debit");
                }
            }


        };
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository,
                            AccountOperationRepository accountOperationRepository) {
        return args -> {

            Stream.of("Mehdi", "Hajar","Tarik" ).forEach(name->{
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name+"@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust->{
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*900000);
                currentAccount.setCreatedate(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(cust);
                currentAccount.setOverdraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());

                savingAccount.setBalance(Math.random()*900000);
                savingAccount.setCreatedate(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(cust);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);
            });
            bankAccountRepository.findAll().forEach(acc->{
                for (int i=0;i<10;i++) {
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOpersationDate(new Date());
                    accountOperation.setAamount(Math.random()*12000);
                    accountOperation.setType(Math.random()<0.5? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepository.save(accountOperation);
                }


            });
        };
    }

}


