package accounting;

import accounting.dao.EntityDaoImplDepartment;
import accounting.entity.Department;
import accounting.util.HibernateUtil;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static accounting.Constant.*;

public class TestDepartment extends Assert {

    @Test
    public void insert() {
        EntityManager em = HibernateUtil.getEntityManager();
        final Department department = Utils.createDepartment(DEPARTMENT_1);

        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department);

        Department departmentFromDB = em.find(Department.class,
                department.getId());

        assertEquals("Id is not equals", department.getId(),
                departmentFromDB.getId());
        assertEquals("Names are not equals",
                department.getName(), departmentFromDB.getName());

        daoImplDepartment.deleteById(department.getId());
        em.close();
    }

    @Test
    public void delete() throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {
        final Department department = Utils.createDepartment(DEPARTMENT_2);
        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department);
        Integer id = department.getId();
        assertNotNull(daoImplDepartment.getEntity(id));
        daoImplDepartment.delete(department);
        assertNull("Department was not deleted",
                daoImplDepartment.getEntity(id));
    }

    @Test
    public void deleteById() {
        final Department department = Utils.createDepartment(DEPARTMENT_3);
        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department);
        Integer id = department.getId();
        assertNotNull(daoImplDepartment.getEntity(id));
        daoImplDepartment.deleteById(id);
        assertNull("Department was not deleted", daoImplDepartment.getEntity(id));
    }

    @Test
    public void update() {
        final Department department = Utils.createDepartment(DEPARTMENT_4);
        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department);
        department.setName(DEPARTMENT_5);
        daoImplDepartment.update(department);
        assertEquals("Department was not updated",
                DEPARTMENT_5,
                daoImplDepartment.getEntity(department.getId()).getName());
        daoImplDepartment.deleteById(department.getId());
    }

    @Test
    public void select() {
        final Department department1 = Utils.createDepartment(DEPARTMENT_6);
        final Department department2 = Utils.createDepartment(DEPARTMENT_7);
        List<Department> list1 = new ArrayList();
        list1.add(department1);
        list1.add(department2);

        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department1);
        daoImplDepartment.insert(department2);
        List<?> list2 = daoImplDepartment.select();
        assertEquals("Departments were not equals",
                list1, list2);
        daoImplDepartment.deleteById(department1.getId());
        daoImplDepartment.deleteById(department2.getId());
    }

    @Test
    public void getEntityTest() {
        final Department department = Utils.createDepartment(DEPARTMENT_8);
        EntityDaoImplDepartment daoImplDepartment
                = new EntityDaoImplDepartment();
        daoImplDepartment.insert(department);
        assertEquals("Departments were not equals",
                department.toString(),
                daoImplDepartment.getEntity(department.getId()).toString());
        daoImplDepartment.deleteById(department.getId());
    }
}

