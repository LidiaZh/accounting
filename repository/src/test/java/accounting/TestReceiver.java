package accounting;

import accounting.dao.EntityDaoImplReceiver;
import accounting.entity.Receiver;
import accounting.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static accounting.Constant.*;

public class TestReceiver extends Assert {
    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final Receiver receiver = Utils.createReceiver(NAME_FOR_RECEIVER_1);
        EntityDaoImplReceiver daoImplReceiver
                = new EntityDaoImplReceiver();
        daoImplReceiver.insert(receiver);
        Receiver receiverFromDB = em.find(Receiver.class,
                receiver.getId());

        assertEquals("Id is not equals", receiver.getId(),
                receiverFromDB.getId());
        assertEquals("Names are not equals",
                receiver.getName(), receiverFromDB.getName());
        assertEquals("Addresses are not equals",
                receiver.getAddress(), receiverFromDB.getAddress());
        assertEquals("Phones are not equals",
                receiver.getPhone(), receiverFromDB.getPhone());
        daoImplReceiver.deleteById(receiver.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        final Receiver receiver = Utils.createReceiver(NAME_FOR_RECEIVER_2);
        EntityDaoImplReceiver daoImplReceiver
                = new EntityDaoImplReceiver();
        daoImplReceiver.insert(receiver);
        Integer id = receiver.getId();
        assertNotNull(daoImplReceiver.getEntity(id));
        daoImplReceiver.delete(receiver);
        assertNull("Receiver was not deleted",
                daoImplReceiver.getEntity(id));
    }

    @Test
    public void deleteById() {
        final Receiver receiver = Utils.createReceiver(NAME_FOR_RECEIVER_3);
        EntityDaoImplReceiver daoImplReceiver
                = new EntityDaoImplReceiver();
        daoImplReceiver.insert(receiver);
        Integer id = receiver.getId();
        assertNotNull(daoImplReceiver.getEntity(id));
        daoImplReceiver.deleteById(id);
        assertNull("receiver was not deleted", daoImplReceiver.getEntity(id));
    }

    @Test
    public void update() {
        final Receiver receiver = Utils.createReceiver(NAME_FOR_RECEIVER_4);
        EntityDaoImplReceiver daoImplReceiver
                = new EntityDaoImplReceiver();
        daoImplReceiver.insert(receiver);
        receiver.setName(NAME_FOR_RECEIVER_5);
        daoImplReceiver.update(receiver);
        assertEquals("Receiver was not updated",
                NAME_FOR_RECEIVER_5,
                daoImplReceiver.getEntity(receiver.getId()).getName());
        daoImplReceiver.deleteById(receiver.getId());
    }

//    @Test
//    public void select() {
//        final Receiver receiver1 = Utils.createReceiver(NAME_FOR_RECEIVER_6);
//        final Receiver receiver2 = Utils.createReceiver(NAME_FOR_RECEIVER_7);
//        List<Receiver> list1 = new ArrayList();
//        list1.add(receiver1);
//        list1.add(receiver2);
//
//        EntityDaoImplReceiver daoImplReceiver
//                = new EntityDaoImplReceiver();
//        daoImplReceiver.insert(receiver1);
//        daoImplReceiver.insert(receiver2);
//        List<?> list2 = daoImplReceiver.select();
//        assertEquals("Receivers were not equals",
//                list1, list2);
//        daoImplReceiver.deleteById(receiver1.getId());
//        daoImplReceiver.deleteById(receiver2.getId());
//    }

    @Test
    public void getEntityTest() {
        final Receiver receiver = Utils.createReceiver(NAME_FOR_RECEIVER_8);
        EntityDaoImplReceiver daoImplReceiver
                = new EntityDaoImplReceiver();
        daoImplReceiver.insert(receiver);
        assertEquals("Receivers were not equals",
                receiver,
                daoImplReceiver.getEntity(receiver.getId()));
        daoImplReceiver.deleteById(receiver.getId());
    }
}
