package accounting.dao;

import accounting.entity.Branch;
import accounting.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityDaoImplBranch extends EntityDaoImpl<Branch> {

    private static EntityManager em;

    public EntityDaoImplBranch() {
        super(Branch.class);
    }

    public List<Department> showDepartmentInBranch(Integer idBranch) {
        Query query = em.createQuery("SELECT b.department FROM Branch b where b.id = :idBranch ");
        query.setParameter("idBranch", idBranch);
        return query.getResultList();
    }
}
