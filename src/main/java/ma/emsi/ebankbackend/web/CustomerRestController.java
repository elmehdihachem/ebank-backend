package ma.emsi.ebankbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.emsi.ebankbackend.dtos.CustomerDTO;
import ma.emsi.ebankbackend.entities.BankAccount;
import ma.emsi.ebankbackend.entities.Customer;
import ma.emsi.ebankbackend.exception.CustomerNotFondException;
import ma.emsi.ebankbackend.repositories.BankAccountRepository;
import ma.emsi.ebankbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }
    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id")Long customerId) throws CustomerNotFondException {
        return bankAccountService.getCustomer(customerId);

    }
    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerNotFondException {
       return bankAccountService.saveCustomer(customerDTO);
    }
    @PostMapping("/customers/{customerId}")
    public CustomerDTO updateCustomer(@PathVariable Long customerId,@RequestBody CustomerDTO customerDTO)  {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }
    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id)  {
        bankAccountService.deleteCustomer(id);
    }

}
