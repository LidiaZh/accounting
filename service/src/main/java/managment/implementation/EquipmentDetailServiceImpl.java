package managment.implementation;

import accounting.dao.EntityDaoImplEquipmentDetail;
import accounting.entity.EquipmentDetail;
import managment.interfaces.EquipmentDetailService;

import java.util.List;

public class EquipmentDetailServiceImpl implements EquipmentDetailService {

    private final EntityDaoImplEquipmentDetail daoImplEquipmentDetail
            = new EntityDaoImplEquipmentDetail();

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
        daoImplEquipmentDetail.insert(equipmentDetail);
        return equipmentDetail;
    }

    @Override
    public void delEquipmentDetail(Integer id) {
        daoImplEquipmentDetail.deleteById(id);
    }

    @Override
    public void updateEquipmentDetail(Integer id,
                                      String name, String desc,
                                      String producer, int termOfUse) {
        EquipmentDetail equipmentDetail = getEquipmentDetail(id);
        equipmentDetail.setName(name);
        equipmentDetail.setDesc(desc);
        equipmentDetail.setProducer(producer);
        equipmentDetail.setTermOfUse(termOfUse);
        daoImplEquipmentDetail.update(equipmentDetail);
    }

    @Override
    public List<EquipmentDetail> getAllEquipmentDetail() {
        return daoImplEquipmentDetail.select();
    }

    @Override
    public EquipmentDetail getEquipmentDetail(Integer id) {
        return daoImplEquipmentDetail.getEntity(id);
    }
}
