package ez4bk.commerce.orderplacement.service;

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
        merchantMapper.updateByPrimaryKeySelective(merchant);
        orderMapper.insertSelective(order);
        messageSender.initOrder(order.getId(), 1000 * 5);
        return true;
    }

    public boolean cancelOrder(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return false;
        }
        order.setStatus(Order.status.CLOSED);
        orderMapper.updateByPrimaryKeySelective(order);
        log.info("Order {} closed", orderId);
        return true;
    }

    public void makePayment(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return;
        }
        if (!Objects.equals(order.getStatus(), Order.status.CLOSED)) {
            log.info("Refunding order {}", orderId);
            boolean refundRes = customerService.refund(order.getCustomerId(), order.getActualPayment());
            assert refundRes;
            log.info("Restocking merchant {} for order {}", order.getMerchantId(), orderId);
            Merchant refundMerchant = order.getMerchant();
            refundMerchant.addStock(order.getQuantity());
            merchantMapper.updateByPrimaryKeySelective(refundMerchant);
            return;
        }
        order.setPaymentStatus(Order.paymentStatus.PAID);
        orderMapper.updateByPrimaryKeySelective(order);
    }

    public Byte getOrderStatus(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return null;
        }
        return order.getStatus();
    }

    public Byte getPaymentStatus(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if (order == null) {
            log.error("Order not found: {}", orderId);
            return null;
        }
        return order.getPaymentStatus();
    }



}
