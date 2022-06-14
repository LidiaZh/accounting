package accounting;

import accounting.dao.EntityDaoImplEquipment;
import accounting.entity.Equipment;
import accounting.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static accounting.Constant.*;

public class TestEquipment extends Assert {

    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final Equipment equipment
                = Utils.createEquipment(ACCOUNT_NUMBER_1, SERIAL_NUMBER_1);
        EntityDaoImplEquipment daoImplEquipment = new EntityDaoImplEquipment();
        daoImplEquipment.insert(equipment);

        Equipment equipmentFromDB = em.find(Equipment.class, equipment.getId());

        assertEquals("Id is not equals", equipment.getId(),
                equipmentFromDB.getId());
        assertEquals("Account_numbers are not equals",
                equipment.getAccount_number(), equipmentFromDB.getAccount_number());
        assertEquals("Serial_numbers are not equals",
                equipment.getS_number(), equipmentFromDB.getS_number());
        assertEquals("Start_dates are not equals",
                equipment.getStart_date(), equipmentFromDB.getStart_date());
        assertEquals("Depreciations are not equals",
                equipment.getDepreciation(), equipmentFromDB.getDepreciation(), DELTA);
        assertEquals("Statuses are not equals",
                equipment.getStatus(), equipmentFromDB.getStatus());
        daoImplEquipment.deleteById(equipment.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        final Equipment equipment
                = Utils.createEquipment(ACCOUNT_NUMBER_2, SERIAL_NUMBER_2);
        EntityDaoImplEquipment daoImplEquipment = new EntityDaoImplEquipment();
        daoImplEquipment.insert(equipment);
        Integer id = equipment.getId();
        assertNotNull(daoImplEquipment.getEntity(id));
        daoImplEquipment.delete(equipment);
        assertNull("Equipment was not deleted", daoImplEquipment.getEntity(id));
    }

    @Test
    public void deleteById() {
        final Equipment equipment
                = Utils.createEquipment(ACCOUNT_NUMBER_3, SERIAL_NUMBER_3);
        EntityDaoImplEquipment daoImplEquipment = new EntityDaoImplEquipment();
        daoImplEquipment.insert(equipment);
        Integer id = equipment.getId();
        assertNotNull(daoImplEquipment.getEntity(id));
        daoImplEquipment.deleteById(id);
        assertNull("Equipment was not deleted",
                daoImplEquipment.getEntity(id));
    }

    @Test
    public void update() {
        final Equipment equipment
                = Utils.createEquipment(ACCOUNT_NUMBER_4, SERIAL_NUMBER_4);
        EntityDaoImplEquipment daoImplEquipment = new EntityDaoImplEquipment();
        daoImplEquipment.insert(equipment);
        equipment.setAccount_number(ACCOUNT_NUMBER_5);
        daoImplEquipment.update(equipment);
        assertEquals("Equipment was not updated",
                ACCOUNT_NUMBER_5,
                daoImplEquipment.getEntity(equipment.getId()).getAccount_number(), DELTA);
        daoImplEquipment.deleteById(equipment.getId());
    }

    @Test
    public void select() {
        final Equipment equipment1
                = Utils.createEquipment(ACCOUNT_NUMBER_6, SERIAL_NUMBER_6);
        final Equipment equipment2
                = Utils.createEquipment(ACCOUNT_NUMBER_7, SERIAL_NUMBER_7);
        List<Equipment> list1 = new ArrayList();
        list1.add(equipment1);
        list1.add(equipment2);

        EntityDaoImplEquipment daoImplEquipment = new EntityDaoImplEquipment();
        daoImplEquipment.insert(equipment1);
        daoImplEquipment.insert(equipment2);
        List<?> list2 = daoImplEquipment.select();
        assertEquals("Equipments were not equals",
                list1, list2);
        daoImplEquipment.deleteById(equipment1.getId());
        daoImplEquipment.deleteById(equipment2.getId());
    }

    @Test
    public void getEntityTest() {
        final Equipment equipment
                = Utils.createEquipment(ACCOUNT_NUMBER_8, SERIAL_NUMBER_8);
        EntityDaoImplEquipment daoImplEquipment = new EntityDaoImplEquipment();
        daoImplEquipment.insert(equipment);
        assertEquals("Equipments were not equals",
                equipment,
                daoImplEquipment.getEntity(equipment.getId()));
        daoImplEquipment.deleteById(equipment.getId());
    }
}
