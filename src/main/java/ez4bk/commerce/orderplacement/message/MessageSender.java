package ez4bk.commerce.orderplacement.message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static ez4bk.commerce.orderplacement.config.RabbitMQConfig.ORDER_EXCHANGE;

@Component
public class MessageSender {
    @Autowired
    private RabbitTemplate rabbit;

    public void sendOrder(Integer orderId, long delay) {
        rabbit.convertAndSend(ORDER_EXCHANGE,
                "order.payment." + delay,
                orderId,
                msg -> {
                    msg.getMessageProperties().setHeader("x-delay", Math.max(delay, 0L));
                    return msg;
                });
    }
}
