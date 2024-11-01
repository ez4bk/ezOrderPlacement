package ez4bk.commerce.orderplacement.mapper;

import ez4bk.commerce.orderplacement.entity.Wallet;
import ez4bk.commerce.orderplacement.entity.WalletExample;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

@Repository
public interface WalletMapper {
    long countByExample(WalletExample example);

    int deleteByExample(WalletExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wallet row);

    int insertSelective(Wallet row);

    List<Wallet> selectByExample(WalletExample example);

    Wallet selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Wallet row, @Param("example") WalletExample example);

    int updateByExample(@Param("row") Wallet row, @Param("example") WalletExample example);

    int updateByPrimaryKeySelective(Wallet row);

    int updateByPrimaryKey(Wallet row);
}