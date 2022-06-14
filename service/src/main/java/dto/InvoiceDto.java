package dto;

import accounting.entity.Equipment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDto {

    private Integer id;
    private int number;
    private LocalDate date;
    private String cause;
    private List<Equipment> equipmentList;
    private Integer idReceiver;
    private String receiverName;
    private Integer idSupplier;
    private String supplierName;
}
