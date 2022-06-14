package dto;

import accounting.entity.ResponsiblePerson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquipmentDto {

    private Integer id;
    private int account_number;
    private String s_number;
    private Integer invoiceNumberCurrent;
    private Integer idInvoiceCurrent;
    private String equipmentName;
    private String status;
    private LocalDate start_date;
    private float price;
    private ResponsiblePerson resPerson;
}
