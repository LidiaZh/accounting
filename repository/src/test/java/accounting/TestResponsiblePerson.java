package accounting;

import accounting.dao.EntityDaoImplBranch;
import accounting.dao.EntityDaoImplDepartment;
import accounting.dao.EntityDaoImplResponsiblePerson;
import accounting.entity.Branch;
import accounting.entity.Department;
import accounting.entity.ResponsiblePerson;
import accounting.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static accounting.Constant.*;

public class TestResponsiblePerson extends Assert {

    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final Department department = Utils.createDepartment(DEPARTMENT);
        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department);

        final Branch branch = Utils.createBranch(NAME_OF_BRANCH);
        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch);

        final ResponsiblePerson person
                = Utils.createPerson(NAME_OF_RESPONSIBLE_PERSON_1,
                branch, department);
        EntityDaoImplResponsiblePerson daoImplPerson = new EntityDaoImplResponsiblePerson();
        daoImplPerson.insert(person);

        ResponsiblePerson personFromDB = em.find(ResponsiblePerson.class, person.getId());

        assertEquals("Id is not equals", person.getId(),
                personFromDB.getId());
        assertEquals("Names are not equals",
                person.getName(), personFromDB.getName());
        assertEquals("Surnames are not equals",
                person.getName(), personFromDB.getName());
        assertEquals("Branches are not equals",
                person.getBranch(), personFromDB.getBranch());
        assertEquals("Departments are not equals",
                person.getDepartment(), personFromDB.getDepartment());
        daoImplBranch.deleteById(branch.getId());
        daoImplDepartment.deleteById(department.getId());
        daoImplPerson.deleteById(person.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        EntityManager em = HibernateUtil.getEntityManager();
        final Department department = Utils.createDepartment(DEPARTMENT_1);
        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department);

        final Branch branch = Utils.createBranch(NAME_OF_BRANCH_1);
        EntityDaoImplBranch daoImplBranch = new EntityDaoImplBranch();
        daoImplBranch.insert(branch);

        final ResponsiblePerson person
                = Utils.createPerson(NAME_OF_RESPONSIBLE_PERSON_2,
                branch, department);
        EntityDaoImplResponsiblePerson daoImplPerson = new EntityDaoImplResponsiblePerson();
        daoImplPerson.insert(person);

        Integer id = person.getId();
        assertNotNull(daoImplPerson.getEntity(id));

        /**
         * Try to delete Object when it has
         * connections with other Tables
         */
        daoImplPerson.delete(person);
        boolean checkDelete = false;
        try {
            em.find(ResponsiblePerson.class, person.getId());
        } catch (Exception ex) {
            checkDelete = true;
        }
        assertFalse(checkDelete);

        /**
         * Try to delete Object when it has no
         * connections with other Tables
         */
        daoImplBranch.delete(branch);
        daoImplDepartment.delete(department);
        daoImplPerson.delete(person);
        assertNull("Person was not deleted", daoImplPerson.getEntity(id));
        em.close();
    }

    @Test
    public void deleteById() {
        final ResponsiblePerson person
                = Utils.createRPerson(NAME_OF_RESPONSIBLE_PERSON_3);
        EntityDaoImplResponsiblePerson daoImplPerson
                = new EntityDaoImplResponsiblePerson();
        daoImplPerson.insert(person);
        Integer id = person.getId();
        assertNotNull(daoImplPerson.getEntity(id));
        daoImplPerson.deleteById(id);
        assertNull("Person was not deleted", daoImplPerson.getEntity(id));
    }

    @Test
    public void update() {
        final ResponsiblePerson person
                = Utils.createRPerson(NAME_OF_RESPONSIBLE_PERSON_4);
        EntityDaoImplResponsiblePerson daoImplPerson
                = new EntityDaoImplResponsiblePerson();
        daoImplPerson.insert(person);
        person.setName(NAME_OF_RESPONSIBLE_PERSON_5);
        daoImplPerson.update(person);
        assertEquals("Person was not updated",
                NAME_OF_RESPONSIBLE_PERSON_5,
                daoImplPerson.getEntity(person.getId()).getName());
        daoImplPerson.deleteById(person.getId());
    }

    @Test
    public void select() {
        final ResponsiblePerson person1
                = Utils.createRPerson(NAME_OF_RESPONSIBLE_PERSON_6);
        final ResponsiblePerson person2
                = Utils.createRPerson(NAME_OF_RESPONSIBLE_PERSON_7);
        List<ResponsiblePerson> list1 = new ArrayList();
        list1.add(person1);
        list1.add(person2);

        EntityDaoImplResponsiblePerson daoImplPerson = new EntityDaoImplResponsiblePerson();
        daoImplPerson.insert(person1);
        daoImplPerson.insert(person2);
        List<?> list2 = daoImplPerson.select();
        assertEquals("Persons were not equals",
                list1, list2);
        daoImplPerson.deleteById(person1.getId());
        daoImplPerson.deleteById(person2.getId());
    }

    @Test
    public void getEntityTest() {
        final ResponsiblePerson person
                = Utils.createRPerson(NAME_OF_RESPONSIBLE_PERSON_8);
        EntityDaoImplResponsiblePerson daoImplPerson
                = new EntityDaoImplResponsiblePerson();
        daoImplPerson.insert(person);
        assertEquals("Persons were not equals",
                person,
                daoImplPerson.getEntity(person.getId()));
        daoImplPerson.deleteById(person.getId());
    }
}
