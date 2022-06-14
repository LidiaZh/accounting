package accounting.dao;

import accounting.entity.Invoice;
import accounting.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class EntityDaoImplInvoice extends EntityDaoImpl<Invoice> {

    public EntityDaoImplInvoice() {
        super(Invoice.class);
    }
}
