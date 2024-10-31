package ez4bk.commerce.orderplacement.mapper;

import ez4bk.commerce.orderplacement.entity.Customer;
import ez4bk.commerce.orderplacement.entity.CustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerMapper {
    long countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Customer row);

    int insertSelective(Customer row);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Customer row, @Param("example") CustomerExample example);

    int updateByExample(@Param("row") Customer row, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer row);

    int updateByPrimaryKey(Customer row);
}