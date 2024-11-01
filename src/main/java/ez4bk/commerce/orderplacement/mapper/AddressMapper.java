package ez4bk.commerce.orderplacement.mapper;

import ez4bk.commerce.orderplacement.entity.Address;
import ez4bk.commerce.orderplacement.entity.AddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address row);

    int insertSelective(Address row);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Address row, @Param("example") AddressExample example);

    int updateByExample(@Param("row") Address row, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address row);

    int updateByPrimaryKey(Address row);
}