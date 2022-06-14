package managment.interfaces;

import accounting.entity.Equipment;
import dto.EquipmentDto;

import java.time.LocalDate;
import java.util.List;

public interface EquipmentService {


    Equipment addEquipment(Integer account_number,
                           String serial_number, String status, Float price,
                           Integer idInvoice, Integer idEquipmentDetail);

    void delEquipment(Integer id);

    void updateEquipment(Integer idEquipment,
                         Integer account_number, String s_number,
                         Integer idEquipmentDetail, String status,
                         Float price, Integer idInvoice, Integer idInvoiceCurrent,
                         Integer idResPerson, LocalDate start_date);

    List<Equipment> getAllEquipment();

    Equipment getEquipment(Integer id);

    List<EquipmentDto> getEquipmentDto();
}
