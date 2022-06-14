package accounting;

import accounting.dao.EntityDaoImplBranch;
import accounting.entity.Branch;
import accounting.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static accounting.Constant.*;

public class TestBranch extends Assert {

    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final Branch branch = Utils.createBranch(NAME_OF_BRANCH_1);

        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch);

        Branch branchFromDB = em.find(Branch.class, branch.getId());

        assertEquals("Id is not equals", branch.getId(),
                branchFromDB.getId());
        assertEquals("Addresses are not equals",
                branch.getAddress(), branchFromDB.getAddress());
        assertEquals("Names are not equals",
                branch.getName(), branchFromDB.getName());
        assertEquals("Phones are not equals",
                branch.getPhone(), branchFromDB.getPhone());
        daoImplBranch.deleteById(branch.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        final Branch branch = Utils.createBranch(NAME_OF_BRANCH_2);
        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch);
        Integer id = branch.getId();
        assertNotNull(daoImplBranch.getEntity(id));
        daoImplBranch.delete(branch);
        assertNull("Branch was not deleted", daoImplBranch.getEntity(id));
    }

    @Test
    public void deleteById() {
        final Branch branch = Utils.createBranch(NAME_OF_BRANCH_3);
        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch);
        Integer id = branch.getId();
        assertNotNull(daoImplBranch.getEntity(id));
        daoImplBranch.deleteById(id);
        assertNull("Branch was not deleted", daoImplBranch.getEntity(id));
    }

    @Test
    public void update() {
        final Branch branch = Utils.createBranch(NAME_OF_BRANCH_4);
        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch);
        branch.setName(NAME_OF_BRANCH_5);
        daoImplBranch.update(branch);
        assertEquals("Branch was not updated",
                NAME_OF_BRANCH_5, daoImplBranch.getEntity(branch.getId()).getName());
        daoImplBranch.deleteById(branch.getId());
    }


    @Test
    public void select() {
        final Branch branch1 = Utils.createBranch(NAME_OF_BRANCH_6);
        final Branch branch2 = Utils.createBranch(NAME_OF_BRANCH_7);
        List<Branch> list1 = new ArrayList();
        list1.add(branch1);
        list1.add(branch2);

        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch1);
        daoImplBranch.insert(branch2);
        List<?> list2 = daoImplBranch.select();
        assertEquals("Branches were not equals",
                list1, list2);
        daoImplBranch.deleteById(branch1.getId());
        daoImplBranch.deleteById(branch2.getId());
    }

    @Test
    public void getEntityTest() {
        final Branch branch = Utils.createBranch(NAME_OF_BRANCH_8);
        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch);
        assertEquals("Branches were not equals",
                branch,
                daoImplBranch.getEntity(branch.getId()));
        daoImplBranch.deleteById(branch.getId());
    }
}
