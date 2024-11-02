package ez4bk.commerce.orderplacement.controller;

import ez4bk.commerce.orderplacement.common.R;
import ez4bk.commerce.orderplacement.entity.Order;
import ez4bk.commerce.orderplacement.mapper.MerchantMapper;
import ez4bk.commerce.orderplacement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/")
public class WebController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private MerchantMapper merchantMapper;

    @RequestMapping("/")
    public R<String> index() {
        Order testOrder = new Order();
        testOrder.setMerchantId(1);
        testOrder.setQuantity(10);
        testOrder.setAddressId(1);
        testOrder.setCustomerId(1);
        testOrder.setActualPayment(merchantMapper.selectByPrimaryKey(1).getPrice().multiply(BigDecimal.valueOf(testOrder.getQuantity())));
        orderService.createOrder(testOrder);
        orderService.makePayment(testOrder.getId());
        return R.success("Order created successfully");
    }
}
