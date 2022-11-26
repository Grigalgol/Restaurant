package com.example.restaurant.Service;

import com.example.restaurant.dto.MenuDto;
import com.example.restaurant.dto.OrderDto;
import com.example.restaurant.models.*;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.TablesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final TablesRepository tablesRepository;
    private final MenuRepository menuRepository;

    public OrderServiceImpl(OrderRepository orderRepository, TablesRepository tablesRepository, MenuRepository menuRepository) {
        this.orderRepository = orderRepository;
        this.tablesRepository = tablesRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public void save(OrderDto orderDto) {
        Order order = new Order(
                orderDto.getDate_order(),
                orderDto.getClient(),
                orderDto.getWaiter(),
                StatusEnum.NOT_STARTED,
                tablesRepository.findTablesById(orderDto.getTablesId()),
                new ArrayList<>()
        );
        orderRepository.save(order);
    }

    @Override
    public void setCurrentStatus(long id) {
        Order order = orderRepository.findOrderById(id);
        order.setStatus(StatusEnum.CURRENT);
        orderRepository.save(order);
    }

    @Override
    public void setFinishedStatus(long id) {
        Order order = orderRepository.findOrderById(id);
        order.setStatus(StatusEnum.FINISHED);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getAllByClient(Client client) {
        return orderRepository
                .findAll()
                .stream()
                .filter(o -> o.getClient().getPhone().equals(client.getPhone()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllByWaiterWithNotStartedStatus(Waiter waiter) {
        return orderRepository
                .findAll()
                .stream()
                .filter(o -> o.getWaiter().getPhone().equals(waiter.getPhone()))
                .filter(o -> o.getStatus().equals(StatusEnum.NOT_STARTED))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllByWaiterWithCurrentStatus(Waiter waiter) {
        return orderRepository
                .findAll()
                .stream()
                .filter(o -> o.getWaiter().getPhone().equals(waiter.getPhone()))
                .filter(o -> o.getStatus().equals(StatusEnum.CURRENT))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAllByWaiterWithFinishedStatus(Waiter waiter) {
        return orderRepository
                .findAll()
                .stream()
                .filter(o -> o.getWaiter().getPhone().equals(waiter.getPhone()))
                .filter(o -> o.getStatus().equals(StatusEnum.FINISHED))
                .collect(Collectors.toList());
    }

    @Override
    public void serveClient(MenuDto menuDto) {
        List<Menu> menu = menuRepository.findAll();
        menu = menu.stream().sorted((a,b) -> (int) (a.getId() - b.getId())).collect(Collectors.toList());
        Order order = orderRepository.findOrderById(menuDto.getOrderId());
        List<Menu> list = new ArrayList<>(order.getMenus());
        for (int i = 0; i < menuDto.getCountChickenShaurma(); i++) {
            list.add(menu.get(0));
        }
        for (int i = 0; i < menuDto.getCountPigShaurma(); i++) {
            list.add(menu.get(1));
        }
        for (int i = 0; i < menuDto.getCountXXLShaurma(); i++) {
            list.add(menu.get(2));
        }
        order.setMenus(list);
        orderRepository.save(order);
    }

    @Override
    public List<Menu> getMenusByOrderId(long id) {
        return new ArrayList<>(orderRepository.findOrderById(id).getMenus());
    }
}
