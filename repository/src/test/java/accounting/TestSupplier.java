package accounting;

import accounting.dao.EntityDaoImplSupplier;
import accounting.entity.Supplier;
import accounting.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static accounting.Constant.*;

public class TestSupplier extends Assert {

    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final Supplier supplier = Utils.createSupplier(NAME_Of_SUPPLIER_1);

        EntityDaoImplSupplier daoImplSupplier
                = new EntityDaoImplSupplier();
        daoImplSupplier.insert(supplier);

        Supplier supplierFromDB = em.find(Supplier.class,
                supplier.getId());

        assertEquals("Id is not equals", supplier.getId(),
                supplierFromDB.getId());
        assertEquals("Names are not equals",
                supplier.getName(), supplierFromDB.getName());
        assertEquals("Addresses are not equals",
                supplier.getAddress(), supplierFromDB.getAddress());
        assertEquals("Phones are not equals",
                supplier.getPhone(), supplierFromDB.getPhone());
        daoImplSupplier.deleteById(supplier.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        final Supplier supplier = Utils.createSupplier(NAME_Of_SUPPLIER_2);
        EntityDaoImplSupplier daoImplSupplier
                = new EntityDaoImplSupplier();
        daoImplSupplier.insert(supplier);
        Integer id = supplier.getId();
        assertNotNull(daoImplSupplier.getEntity(id));
        daoImplSupplier.delete(supplier);
        assertNull("Supplier was not deleted",
                daoImplSupplier.getEntity(id));
    }

    @Test
    public void deleteById() {
        final Supplier supplier = Utils.createSupplier(NAME_Of_SUPPLIER_3);
        EntityDaoImplSupplier daoImplSupplier
                = new EntityDaoImplSupplier();
        daoImplSupplier.insert(supplier);
        Integer id = supplier.getId();
        assertNotNull(daoImplSupplier.getEntity(id));
        daoImplSupplier.deleteById(id);
        assertNull("Supplier was not deleted", daoImplSupplier.getEntity(id));
    }

    @Test
    public void update() {
        final Supplier supplier = Utils.createSupplier(NAME_Of_SUPPLIER_4);
        EntityDaoImplSupplier daoImplSupplier
                = new EntityDaoImplSupplier();
        daoImplSupplier.insert(supplier);
        supplier.setName(NAME_Of_SUPPLIER_5);
        daoImplSupplier.update(supplier);
        assertEquals("Supplier was not updated",
                NAME_Of_SUPPLIER_5,
                daoImplSupplier.getEntity(supplier.getId()).getName());
        daoImplSupplier.deleteById(supplier.getId());
    }

//    @Test
//    public void select() {
//        final Supplier supplier1 = Utils.createSupplier(NAME_Of_SUPPLIER_6);
//        final Supplier supplier2 = Utils.createSupplier(NAME_Of_SUPPLIER_7);
//        List<Supplier> list1 = new ArrayList();
//        list1.add(supplier1);
//        list1.add(supplier2);
//
//        EntityDaoImplSupplier daoImplSupplier
//                = new EntityDaoImplSupplier();
//        daoImplSupplier.insert(supplier1);
//        daoImplSupplier.insert(supplier2);
//        List<?> list2 = daoImplSupplier.select();
//        assertEquals("Suppliers were not equals",
//                list1, list2);
//        daoImplSupplier.deleteById(supplier1.getId());
//        daoImplSupplier.deleteById(supplier2.getId());
//    }

    @Test
    public void getEntityTest() {
        final Supplier supplier = Utils.createSupplier(NAME_Of_SUPPLIER_8);
        EntityDaoImplSupplier daoImplSupplier
                = new EntityDaoImplSupplier();
        daoImplSupplier.insert(supplier);
        assertEquals("Suppliers were not equals",
                supplier,
                daoImplSupplier.getEntity(supplier.getId()));
        daoImplSupplier.deleteById(supplier.getId());
    }
}
