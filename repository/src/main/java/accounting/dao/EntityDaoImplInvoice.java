package accounting.dao;

import accounting.entity.Invoice;
import accounting.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityDaoImplInvoice extends EntityDaoImpl<Invoice> {

    private static EntityManager em;

    public EntityDaoImplInvoice() {
        super(Invoice.class);
    }

//    public Float getPriceForDepriciation(Integer id) {
//        em = HibernateUtil.getEntityManager();
//        String queryString = "SELECT i.price FROM Invoice i WHERE i.id = " + id;
//        Query query = em.createQuery(queryString);
//        Float price = (Float) query.getSingleResult();
//        System.out.println(price);
//        em.close();
//        return price;
//    }

}
