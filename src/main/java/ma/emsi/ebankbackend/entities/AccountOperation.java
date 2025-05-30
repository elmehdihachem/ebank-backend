package ma.emsi.ebankbackend.entities;

import jakarta.persistence.*;
import jdk.dynalink.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.ebankbackend.enums.OperationType;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date OpersationDate;
    private Double aamount;
    @Enumerated(EnumType.STRING)
    private OperationType type;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;
}
