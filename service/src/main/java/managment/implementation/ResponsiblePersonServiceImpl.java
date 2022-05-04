package managment.implementation;

import accounting.dao.EntityDaoImplEquipment;
import accounting.dao.EntityDaoImplEquipmentDetail;
import accounting.entity.Equipment;
import accounting.entity.EquipmentDetail;
import managment.interfaces.ResponsiblePersonService;

import java.util.List;

public class ResponsiblePersonServiceImpl
        implements ResponsiblePersonService {

    EntityDaoImplEquipment entityDaoImplEquipment
            = new EntityDaoImplEquipment();

    EntityDaoImplEquipmentDetail entityDaoImplEquipmentDetail
            = new EntityDaoImplEquipmentDetail();

    @Override
    public void updateEquipmentSerialNumber(Integer id, String serial_number) {
        Equipment equipment = getEquipment(id);
        equipment.setS_number(serial_number);
        entityDaoImplEquipment.update(equipment);
    }

    @Override
    public void updateEquipmentStatus(Integer id, String status) {
        Equipment equipment = getEquipment(id);
        equipment.setStatus(status);
        entityDaoImplEquipment.update(equipment);
    }

    @Override
    public Equipment getEquipment(Integer id) {
        return entityDaoImplEquipment.getEntity(id);
    }

    @Override
    public List<?> getAllEquipment() {
        return entityDaoImplEquipment.select();
    }

    @Override
    public EquipmentDetail addEquipmentDetail(String name,
                                              String desc, String producer,
                                              int termOfUse) {
        EquipmentDetail equipmentDetail = EquipmentDetail.builder()
                .name(name)
                .desc(desc)
                .producer(producer)
                .termOfUse(termOfUse)
                .build();
        entityDaoImplEquipment.insert(equipmentDetail);
        return equipmentDetail;
    }

    @Override
    public void updateEquipmentDetail(Integer id, String name, String desc, String producer, int termOfUse) {
        EquipmentDetail equipmentDetail = getEquipmentDetail(id);
        equipmentDetail.setName(name);
        equipmentDetail.setDesc(desc);
        equipmentDetail.setProducer(producer);
        equipmentDetail.setTermOfUse(termOfUse);
        entityDaoImplEquipmentDetail.update(equipmentDetail);
    }

    @Override
    public List<?> getAllEquipmentDetail() {
        return entityDaoImplEquipmentDetail.select();
    }

    @Override
    public EquipmentDetail getEquipmentDetail(Integer id) {
        return entityDaoImplEquipmentDetail.getEntity(id);
    }
}
