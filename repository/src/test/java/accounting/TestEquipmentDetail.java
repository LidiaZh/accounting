package accounting;

import accounting.dao.EntityDaoImplEquipmentDetail;
import accounting.entity.EquipmentDetail;
import accounting.util.HibernateUtil;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static accounting.Constant.*;

public class TestEquipmentDetail extends Assert {

    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final EquipmentDetail eqDetail
                = Utils.createEquipmentDetail(EQUIPMENT_DETAIL_1);
        EntityDaoImplEquipmentDetail daoImplEqDetail
                = new EntityDaoImplEquipmentDetail();
        daoImplEqDetail.insert(eqDetail);

        EquipmentDetail equipmentFromDB
                = em.find(EquipmentDetail.class, eqDetail.getId());

        assertEquals("Id is not equals", eqDetail.getId(),
                equipmentFromDB.getId());
        assertEquals("Names are not equals",
                eqDetail.getName(), equipmentFromDB.getName());
        assertEquals("Descriptions are not equals",
                eqDetail.getName(), equipmentFromDB.getName());
        assertEquals("Producers are not equals",
                eqDetail.getProducer(), equipmentFromDB.getProducer());
        assertEquals("TermOfUses are not equals",
                eqDetail.getTermOfUse(), equipmentFromDB.getTermOfUse());
        daoImplEqDetail.deleteById(eqDetail.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        final EquipmentDetail eqDetail
                = Utils.createEquipmentDetail(EQUIPMENT_DETAIL_2);
        EntityDaoImplEquipmentDetail daoImplEqDetail
                = new EntityDaoImplEquipmentDetail();
        daoImplEqDetail.insert(eqDetail);
        Integer id = eqDetail.getId();
        assertNotNull(daoImplEqDetail.getEntity(id));
        daoImplEqDetail.delete(eqDetail);
        assertNull("EqDetail was not deleted", daoImplEqDetail.getEntity(id));
    }

    @Test
    public void deleteById() {
        final EquipmentDetail eqDetail
                = Utils.createEquipmentDetail(EQUIPMENT_DETAIL_3);
        EntityDaoImplEquipmentDetail daoImplEqDetail
                = new EntityDaoImplEquipmentDetail();
        daoImplEqDetail.insert(eqDetail);
        Integer id = eqDetail.getId();
        assertNotNull(daoImplEqDetail.getEntity(id));
        daoImplEqDetail.deleteById(id);
        assertNull("EqDetail was not deleted", daoImplEqDetail.getEntity(id));
    }

    @Test
    public void update() {
        final EquipmentDetail eqDetail
                = Utils.createEquipmentDetail(EQUIPMENT_DETAIL_4);
        EntityDaoImplEquipmentDetail daoImplEqDetail
                = new EntityDaoImplEquipmentDetail();
        daoImplEqDetail.insert(eqDetail);
        eqDetail.setName(EQUIPMENT_DETAIL_5);
        daoImplEqDetail.update(eqDetail);
        assertEquals("EqDetail was not updated",
                EQUIPMENT_DETAIL_5,
                daoImplEqDetail.getEntity(eqDetail.getId()).getName());
        daoImplEqDetail.deleteById(eqDetail.getId());
    }

    @Test
    public void select() {
        final EquipmentDetail eqDetail1
                = Utils.createEquipmentDetail(EQUIPMENT_DETAIL_6);
        final EquipmentDetail eqDetail2
                = Utils.createEquipmentDetail(EQUIPMENT_DETAIL_7);
        List<EquipmentDetail> list1 = new ArrayList();
        list1.add(eqDetail1);
        list1.add(eqDetail2);

        EntityDaoImplEquipmentDetail daoImplEqDetail
                = new EntityDaoImplEquipmentDetail();
        daoImplEqDetail.insert(eqDetail1);
        daoImplEqDetail.insert(eqDetail2);
        List<?> list2 = daoImplEqDetail.select();
        assertEquals("EqDetails were not equals",
                list1, list2);
        daoImplEqDetail.deleteById(eqDetail1.getId());
        daoImplEqDetail.deleteById(eqDetail2.getId());
    }

    @Test
    public void getEntityTest() {
        final EquipmentDetail eqDetail
                = Utils.createEquipmentDetail(EQUIPMENT_DETAIL_8);
        EntityDaoImplEquipmentDetail daoImplEqDetail
                = new EntityDaoImplEquipmentDetail();
        daoImplEqDetail.insert(eqDetail);
        assertEquals("EqDetails were not equals",
                eqDetail,
                daoImplEqDetail.getEntity(eqDetail.getId()));
        daoImplEqDetail.deleteById(eqDetail.getId());
    }
}
