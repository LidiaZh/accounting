package accounting.dao;

import accounting.entity.EquipmentDetail;
import accounting.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EntityDaoImplEquipmentDetail
        extends EntityDaoImpl<EquipmentDetail> {

    public EntityDaoImplEquipmentDetail() {
        super(EquipmentDetail.class);
    }

}
