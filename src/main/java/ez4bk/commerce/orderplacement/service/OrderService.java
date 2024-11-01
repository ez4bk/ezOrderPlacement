package ez4bk.commerce.orderplacement.service;

import ez4bk.commerce.orderplacement.entity.Customer;
import ez4bk.commerce.orderplacement.entity.Merchant;
import ez4bk.commerce.orderplacement.entity.Order;
import ez4bk.commerce.orderplacement.mapper.MerchantMapper;
import ez4bk.commerce.orderplacement.mapper.OrderMapper;
import ez4bk.commerce.orderplacement.message.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private MessageSender messageSender;
    @Autowired
    private CustomerService customerService;

    public boolean createOrder(Order order) {
        Merchant merchant = merchantMapper.selectByPrimaryKey(order.getMerchantId());
        if (!merchant.deductStock(order.getQuantity())) {
            log.error("Merchant {} stock not enough", merchant.getId());
            return false;
        }
        orderMapper.insertSelective(order);
        messageSender.initOrder(order.getId(), 1000 * 60 * 15);
        return true;
    }

    public boolean cancelOrder(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return false;
        }
        order.setStatus(Order.status.CLOSED);

        if (Objects.equals(order.getPaymentStatus(), Order.paymentStatus.PAID)) {
            boolean refundRes = customerService.refund(order.getCustomerId(), order.getActualPayment());
            assert refundRes;
        }
        orderMapper.updateByPrimaryKeySelective(order);
        return true;
    }

    public void makePayment(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return;
        }
        order.setPaymentStatus(Order.paymentStatus.PAID);
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

    public Byte getPaymentStatus(String orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return null;
        }
        return order.getPaymentStatus();
    }



}
