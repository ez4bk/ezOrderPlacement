package ez4bk.commerce.orderplacement.service;

import ez4bk.commerce.orderplacement.entity.Order;
import ez4bk.commerce.orderplacement.mapper.OrderMapper;
import ez4bk.commerce.orderplacement.message.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MessageSender messageSender;

    public void createOrder(Order order) {
        orderMapper.insertSelective(order);
        messageSender.sendOrder(order.getId(), 1000 * 60 * 15);
    }

    public void updateOrder(String orderId, Byte status) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
        }
        assert order != null;
        order.setStatus(status);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    public Byte getOrderStatus(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return null;
        }
        return order.getStatus();
    }

}
