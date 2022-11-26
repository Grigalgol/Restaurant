package com.example.restaurant.Service;


import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.models.*;

import java.util.List;

public interface OrderService {
    void save(OrderDto orderDto);
    void setCurrentStatus(long id);
    void setFinishedStatus(long id);
    List<Order> getAll();
    List<Order> getAllByClient(Client client);
    List<Order> getAllByWaiterWithNotStartedStatus(Waiter waiter);
    List<Order> getAllByWaiterWithCurrentStatus(Waiter waiter);
    List<Order> getAllByWaiterWithFinishedStatus(Waiter waiter);
    void serveClient(MenuDto menuDto);
    List<Menu> getMenusByOrderId(long id);
}
