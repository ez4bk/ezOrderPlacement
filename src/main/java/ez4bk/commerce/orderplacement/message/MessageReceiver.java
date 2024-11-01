package ez4bk.commerce.orderplacement.message;

import ez4bk.commerce.orderplacement.entity.Order;
import ez4bk.commerce.orderplacement.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static ez4bk.commerce.orderplacement.config.RabbitMQConfig.ORDER_QUEUE;

@Slf4j
@Component
public class MessageReceiver {
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = ORDER_QUEUE)
    public void confirmPayment(Object message) {
        String oid = null;
        try {
            oid = (String) message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Byte curr = orderService.getOrderStatus(oid);
        if (curr.equals(Order.status.UNPAID)) {
            log.warn("Order {} time exceeds, closing...", oid);
            orderService.updateOrder(oid, Order.status.CLOSED);
        } else if (curr.equals(Order.status.PAID)) {
            log.info("Order {} has been paid", oid);
        }
    }
}
