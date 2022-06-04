package managment.interfaces;

import accounting.entity.EquipmentDetail;

import java.util.List;

public interface EquipmentDetailService {

    EquipmentDetail addEquipmentDetail(String name, String desc,
                                       String producer, int termOfUse);

    void delEquipmentDetail(Integer id);

    void updateEquipmentDetail(Integer id, String name, String desc,
                               String producer, int termOfUse);

    List<?> getAllEquipmentDetail();

    EquipmentDetail getEquipmentDetail(Integer id);
}
