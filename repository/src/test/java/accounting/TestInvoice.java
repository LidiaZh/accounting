package accounting;

import accounting.dao.EntityDaoImplInvoice;
import accounting.dao.EntityDaoImplReceiver;
import accounting.dao.EntityDaoImplSupplier;
import accounting.entity.Invoice;
import accounting.entity.Receiver;
import accounting.entity.Supplier;
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

public class TestInvoice extends Assert {

    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final Supplier supplier = Utils.createSupplier(NAME_Of_SUPPLIER);
        EntityDaoImplSupplier daoImplSupplier
                = new EntityDaoImplSupplier();
        daoImplSupplier.insert(supplier);

        final Receiver receiver = Utils.createReceiver(NAME_FOR_RECEIVER);
        EntityDaoImplReceiver daoImplReceiver
                = new EntityDaoImplReceiver();
        daoImplReceiver.insert(receiver);

        final Invoice invoice = Utils.createInvoice(INVOICE_NUMBER_1,
                supplier, receiver);
        EntityDaoImplInvoice daoImplInvoice = new EntityDaoImplInvoice();
        daoImplInvoice.insert(invoice);

        Invoice invoiceFromDB = em.find(Invoice.class, invoice.getId());

        assertEquals("Id is not equals", invoice.getId(),
                invoiceFromDB.getId());
        assertEquals("Numbers are not equals",
                invoice.getNumber(), invoiceFromDB.getNumber());
        assertEquals("Causes are not equals",
                invoice.getCause(), invoiceFromDB.getCause());
        assertEquals("Dates are not equals",
                invoice.getDate(), invoiceFromDB.getDate());
        assertEquals("Prices are not equals",
                invoice.getPrice(), invoiceFromDB.getPrice(), DELTA);
        assertEquals("Receivers are not equals",
                invoice.getReceiver(), invoiceFromDB.getReceiver());
        assertEquals("Suppliers are not equals",
                invoice.getSupplier(), invoiceFromDB.getSupplier());
        daoImplReceiver.deleteById(receiver.getId());
        daoImplSupplier.deleteById(supplier.getId());
        daoImplInvoice.deleteById(invoice.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        EntityManager em = HibernateUtil.getEntityManager();
        final Supplier supplier = Utils.createSupplier(NAME_Of_SUPPLIER);
        EntityDaoImplSupplier daoImplSupplier
                = new EntityDaoImplSupplier();
        daoImplSupplier.insert(supplier);

        final Receiver receiver = Utils.createReceiver(NAME_FOR_RECEIVER);
        EntityDaoImplReceiver daoImplReceiver
                = new EntityDaoImplReceiver();
        daoImplReceiver.insert(receiver);

        final Invoice invoice = Utils.createInvoice(INVOICE_NUMBER_2,
                supplier, receiver);
        EntityDaoImplInvoice daoImplInvoice = new EntityDaoImplInvoice();
        daoImplInvoice.insert(invoice);

        Integer id = invoice.getId();
        assertNotNull(daoImplInvoice.getEntity(id));

        /**
         * Try to delete Object when it has
         * connections with other Tables
         */
        daoImplInvoice.delete(invoice);
        boolean checkDelete = false;
        try {
            em.find(Invoice.class, invoice.getId());
        } catch (Exception ex) {
            checkDelete = true;
        }
        assertFalse(checkDelete);

        /**
         * Try to delete Object when it has no
         * connections with other Tables
         */
        daoImplReceiver.delete(receiver);
        daoImplSupplier.delete(supplier);
        daoImplInvoice.delete(invoice);
        assertNull("Invoice was not deleted", daoImplInvoice.getEntity(id));
        em.close();
    }

    @Test
    public void deleteById() {
        final Invoice invoice = Utils.createTestInvoice(INVOICE_NUMBER_3);
        EntityDaoImplInvoice daoImplInvoice = new EntityDaoImplInvoice();
        daoImplInvoice.insert(invoice);
        Integer id = invoice.getId();
        assertNotNull(daoImplInvoice.getEntity(id));
        daoImplInvoice.deleteById(id);
        assertNull("Invoice was not deleted", daoImplInvoice.getEntity(id));
    }

    @Test
    public void update() {
        final Invoice invoice = Utils.createTestInvoice(INVOICE_NUMBER_4);
        EntityDaoImplInvoice daoImplInvoice = new EntityDaoImplInvoice();
        daoImplInvoice.insert(invoice);
        invoice.setCause(CAUSE);
        daoImplInvoice.update(invoice);
        assertEquals("Invoice was not updated",
                CAUSE,
                daoImplInvoice.getEntity(invoice.getId()).getCause());
        daoImplInvoice.deleteById(invoice.getId());
    }


    @Test
    public void select() {
        final Invoice invoice1 = Utils.createTestInvoice(INVOICE_NUMBER_5);
        final Invoice invoice2 = Utils.createTestInvoice(INVOICE_NUMBER_6);
        List<Invoice> list1 = new ArrayList();
        list1.add(invoice1);
        list1.add(invoice2);

        EntityDaoImplInvoice daoImplInvoice = new EntityDaoImplInvoice();
        daoImplInvoice.insert(invoice1);
        daoImplInvoice.insert(invoice2);
        List<?> list2 = daoImplInvoice.select();
        assertEquals("Invoices were not equals",
                list1, list2);
        daoImplInvoice.deleteById(invoice1.getId());
        daoImplInvoice.deleteById(invoice2.getId());
    }

    @Test
    public void getEntityTest() {
        final Invoice invoice = Utils.createTestInvoice(INVOICE_NUMBER_7);
        EntityDaoImplInvoice daoImplInvoice = new EntityDaoImplInvoice();
        daoImplInvoice.insert(invoice);
        assertEquals("Invoices were not equals",
                invoice,
                daoImplInvoice.getEntity(invoice.getId()));
        daoImplInvoice.deleteById(invoice.getId());
    }
}
