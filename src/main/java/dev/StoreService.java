package dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class StoreService {
    private final Logger logger = LoggerFactory.getLogger(StoreService.class);
    private StoreRepository repository;
    private StockRepository stock_repository;
    private List <Store> stores;
    private List <Stock> stocks;

    StoreService(){
        this(new StoreRepository(), new StockRepository());
    }

    StoreService(StoreRepository repository, StockRepository stock_repository){
        this.repository = repository;
        this.stock_repository = stock_repository;
    }

    public double distance(Coordinates a, Coordinates b){
        return  Math.sqrt(Math.pow(b.getY()-a.getY(),2) + Math.pow(b.getX()-a.getX(),2));
    }

    List<Store> storesSortedList(Coordinates c){
        List<Store> temp = repository.findALL();

        for (Store a: temp
             ) {
            double dist = distance(a.getCoordinates(), c);
            a.setDistanceFromCustomer(dist);
        }
        Comparator<? super Store> distanceFromCustomerSorter = Comparator.comparingDouble(Store::getDistanceFromCustomer);
        temp.sort(distanceFromCustomerSorter);

        return temp;
    }

    String storesList(Coordinates c){
        stores = storesSortedList(c);
        for (Store a: stores
             ) {
            System.out.println(a.toString());
        }

        return "abc";
    }


    String handleOrderList(List<Order> OrderItemList, Coordinates c){
        for (Stock s: prepareHandleOrder(OrderItemList, c)
             ) {
            System.out.println(s.toString());
        }
        return "abc2";
    }

    List<Stock> prepareHandleOrder(List<Order> OrderItemList, Coordinates c){
        List<Store> store_list = storesSortedList(c);
        List<Stock> finalStock = new ArrayList<Stock>();
        System.out.println(OrderItemList.size());

        for (Store a: store_list
             ) {
            System.out.println("Loop Store - id:" + a.getId());
            List<Stock> storeStock = stock_repository.findByStoreId(a.getId());
            List<Order> temp = List.copyOf(OrderItemList);
            for (Order o: temp)
            {
                System.out.println("Loop Order - id:" + o.getProduct_id() + " qnt:" + o.getQuantity());

                for (Stock s: storeStock
                     ) {
                    System.out.println("Loop Stock - store.id:" + s.getStore_id() +" product.id:" + s.getProduct_id() + " qnt:" + s.getQuantity());
                    if (o.getProduct_id()==s.getProduct_id() && o.getQuantity() <= s.getQuantity()){
                        System.out.println("I am in if");
                        finalStock.add(new Stock(a.getId(), o.getProduct_id(), o.getQuantity()));
                        OrderItemList.remove(o);
                        stock_repository.updateStock(a.getId(), o.getProduct_id(), o.getQuantity());
                        break;
                    }
                }
                //List<Stock> tempStock = temp.stream().filter(stock -> stock.getProduct_id().equals(o.getProduct_id())).collect(Collectors.toList());
            }
            if (OrderItemList.size() == 0){
                System.out.println("I am in break");
                break;
            }
        }
        return finalStock;
    }


}
