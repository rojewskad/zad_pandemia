package dev;

import java.util.List;
import java.util.Optional;

public class ProductRepository {
    public List<Product> findALL(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.createQuery("from Product", Product.class).list();

        transaction.commit();
        session.close();

        return result;
    }

    public Optional<Product> findById(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var result = session.get(Product.class, id);

        transaction.commit();
        session.close();
        return Optional.ofNullable(result);
    }
}
