package ez4bk.commerce.orderplacement.service;

import ez4bk.commerce.orderplacement.entity.Customer;
import ez4bk.commerce.orderplacement.mapper.CustomerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    public boolean pay(Integer id, BigDecimal amount) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        log.info("Customer {} paying with amount {}", customer.getName(), amount);
        return customer.deductBalance(amount);
    }

    public boolean refund(Integer id, BigDecimal amount) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        log.info("Refunding customer {} with amount {}", customer.getName(), amount);
        return customer.addBalance(amount);
    }
}
