package ma.emsi.ebankbackend.dtos;


import lombok.Data;
import ma.emsi.ebankbackend.enums.OperationType;
import java.util.Date;


@Data

public class AccountOperationDTO {
    private Long id;
    private Date OpersationDate;
    private Double amount;
    private OperationType type;
    private String description;
}
