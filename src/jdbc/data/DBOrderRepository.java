package jdbc.data;

import jdbc.entities.Order;

public class DBOrderRepository implements OrderRepository{
    @Override
    //Questo metodo può fare al massimo 2 query uno per il customer ed una fra order e line order
    public Iterable<Order> findByCustomerId(int custId) {
        return null;
    }
}
