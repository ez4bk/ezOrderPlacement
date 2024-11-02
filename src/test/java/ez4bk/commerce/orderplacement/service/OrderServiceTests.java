package ez4bk.commerce.orderplacement.service;

import ez4bk.commerce.orderplacement.entity.Order;
import ez4bk.commerce.orderplacement.mapper.MerchantMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MerchantMapper merchantMapper;

    @Test
    public void testCreateOrder() throws InterruptedException {
        Order testOrder = new Order();
        testOrder.setMerchantId(1);
        testOrder.setQuantity(10);
        testOrder.setAddressId(1);
        testOrder.setCustomerId(1);
        testOrder.setActualPayment(merchantMapper.selectByPrimaryKey(1).getPrice().multiply(BigDecimal.valueOf(testOrder.getQuantity())));
        assert orderService.createOrder(testOrder);
//        sleep(1000);
    }
}
