package dev;

import java.util.List;
import java.util.Optional;

public class StoreRepository {
    public List<Store> findALL(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Store", Store.class).list();

        transaction.commit();
        session.close();

        return result;
    }

}
