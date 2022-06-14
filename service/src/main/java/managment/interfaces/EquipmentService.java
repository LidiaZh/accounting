package managment.interfaces;

import accounting.entity.Equipment;
import accounting.entity.EquipmentDetail;
import accounting.entity.Invoice;
import accounting.entity.ResponsiblePerson;
import dto.EquipmentDto;

import java.util.List;

public interface EquipmentService {


    Equipment addEquipment(Integer account_number,
                           String serial_number, String status,
                           Invoice invoice, ResponsiblePerson person,
                           EquipmentDetail equipmentDetail);

    void delEquipment(Integer id);

    void updateEquipment(Integer id, String serial_number,
                         String status, Invoice invoice,
                         EquipmentDetail equipmentDetail);

    void updateEquipmentAccountNumber(Integer id, Integer account_number);

    void updateEquipmentResponsiblePerson(Integer id, ResponsiblePerson person);

    void updateEquipmentStatus(Integer id, String status);

    List<Equipment> getAllEquipment();

    Equipment getEquipment(Integer id);

    List<EquipmentDto> getEquipmentDto();
}
