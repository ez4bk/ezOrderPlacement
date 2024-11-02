package ez4bk.commerce.orderplacement.mapper;

import ez4bk.commerce.orderplacement.entity.Merchant;
import ez4bk.commerce.orderplacement.entity.MerchantExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MerchantMapper {
    long countByExample(MerchantExample example);

    int deleteByExample(MerchantExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Merchant row);

    int insertSelective(Merchant row);

    List<Merchant> selectByExample(MerchantExample example);

    Merchant selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Merchant row, @Param("example") MerchantExample example);

    int updateByExample(@Param("row") Merchant row, @Param("example") MerchantExample example);

    int updateByPrimaryKeySelective(Merchant row);

    int updateByPrimaryKey(Merchant row);
}