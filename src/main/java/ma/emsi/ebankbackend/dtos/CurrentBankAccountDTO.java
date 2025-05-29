package ma.emsi.ebankbackend.dtos;


import lombok.Data;
import ma.emsi.ebankbackend.enums.AccountStatus;
import java.util.Date;



@Data
public class SavingBankAccountDTO {
    private String id;
    private double balance;
    private Date createdate;
    private AccountStatus status;
    private CustomerDTO customerDTO;
   private double interestRate;
}
