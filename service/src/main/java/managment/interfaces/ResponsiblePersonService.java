package managment.interfaces;

import accounting.entity.Equipment;
import accounting.entity.EquipmentDetail;
import accounting.entity.Invoice;
import accounting.entity.ResponsiblePerson;

import java.util.List;

public interface ResponsiblePersonService {


    void updateEquipmentSerialNumber(Integer id, String serial_number);

    void updateEquipmentStatus(Integer id, String status);

    Equipment getEquipment(Integer id);

    List<?> getAllEquipment();

    EquipmentDetail addEquipmentDetail(String name, String desc,
                                       String producer, int termOfUse);

    void updateEquipmentDetail(Integer id, String name, String desc,
                               String producer, int termOfUse);

    List<?> getAllEquipmentDetail();

    EquipmentDetail getEquipmentDetail(Integer id);

}
