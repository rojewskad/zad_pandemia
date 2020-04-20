package dev;

import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class StockRepository {
    public List<Stock> findALL(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Stock", Stock.class).list();


        transaction.commit();
        session.close();

        return result;
    }

    public List<Stock> findByStoreId(Integer storeId){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        Query query = session.createQuery("from Stock where store_id = :storeId", Stock.class);
        query.setParameter("storeId", storeId);
        var result = query.list();

        transaction.commit();
        session.close();
        return result;
    }

    public void updateStock(Integer store_id, Integer product_id, Integer delta){
      /*  var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.createQuery("from Stock order by store_id ASC", Stock.class);

        transaction.commit();
        session.close();*/
    }
}
