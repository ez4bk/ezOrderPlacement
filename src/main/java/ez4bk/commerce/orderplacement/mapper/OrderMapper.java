package ez4bk.commerce.orderplacement.mapper;

import ez4bk.commerce.orderplacement.entity.Order;
import ez4bk.commerce.orderplacement.entity.OrderExample;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

@Repository
public interface OrderMapper {
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(String id);

    int insert(Order row);

    int insertSelective(Order row);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("row") Order row, @Param("example") OrderExample example);

    int updateByExample(@Param("row") Order row, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order row);

    int updateByPrimaryKey(Order row);
}