package ma.emsi.ebankbackend.services;

import ma.emsi.ebankbackend.dtos.*;
import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.exception.BalanceNotSufficientException;
import ma.emsi.ebankbackend.exception.BankAccountNotFoundException;
import ma.emsi.ebankbackend.exception.CustomerNotFondException;

import java.util.List;

public interface BankAccountService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft , Long customerId) throws CustomerNotFondException;
    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate , Long customerId) throws CustomerNotFondException;

    List<CustomerDTO> listCustomers();

    BankAccountDTO getBankAccount(String accountId) throws BankAccountNotFoundException;
    void debit(String accountId, double amount,String description) throws BankAccountNotFoundException, BalanceNotSufficientException;
    void credit(String accountId, double amount,String description) throws BankAccountNotFoundException;
    void transfert(String accountIdSource,String accountIdDestination, double amount ) throws BankAccountNotFoundException, BalanceNotSufficientException;

    List<BankAccountDTO> bankAccountList();

    CustomerDTO getCustomer(Long customerId) throws CustomerNotFondException;

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);
}
