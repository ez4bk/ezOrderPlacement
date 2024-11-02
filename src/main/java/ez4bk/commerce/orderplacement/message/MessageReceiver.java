package ez4bk.commerce.orderplacement.message;

import ez4bk.commerce.orderplacement.entity.Order;
import ez4bk.commerce.orderplacement.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static ez4bk.commerce.orderplacement.config.RabbitMQConfig.ORDER_QUEUE;

@Slf4j
@Component
public class MessageReceiver {
    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = ORDER_QUEUE)
    public void confirmPayment(Integer oid) {
        Byte curr = orderService.getOrderStatus(oid);
        if (curr.equals(Order.status.CLOSED)) {
            log.info("Order {} is paid, do nothing", oid);
        }
        log.warn("Order {} overdue, cancelling", oid);
        orderService.cancelOrder(oid);

    }
}
