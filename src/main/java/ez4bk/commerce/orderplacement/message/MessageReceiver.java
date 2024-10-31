package ez4bk.commerce.orderplacement.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static ez4bk.commerce.orderplacement.config.RabbitMQConfig.ORDER_QUEUE;

@Component
public class MessageReceiver {
    @Autowired
    private RabbitTemplate rabbit;

    @RabbitListener(queues = ORDER_QUEUE)
    public void confirmPayment(Object message) {
        try {
            String oid = (String) message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
