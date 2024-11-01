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

    public void createOrder(Order order) {
        Merchant merchant = merchantMapper.selectByPrimaryKey(order.getMerchantId());
        merchant.setStock(merchant.getStock() - 1);
        orderMapper.insertSelective(order);
        messageSender.initOrder(order.getId(), 1000 * 60 * 15);
    }

    public void cancelOrder(String orderId, Byte status) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return;
        }
        order.setStatus(status);
        Customer customer = order.getCustomer();
        Merchant merchant = order.getMerchant();
//        customerService.refund(customer.getId(), merchant.getPrice());
        orderMapper.updateByPrimaryKeySelective(order);
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
