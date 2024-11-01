package ez4bk.commerce.orderplacement.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Customer implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private List<Address> addresses;

    private Wallet wallet;

    private static final long serialVersionUID = 1L;

    public BigDecimal getBalance() {
        if (wallet == null) {
            return null;
        }
        return wallet.getBalance();
    }

    public boolean addBalance(BigDecimal delta) {
        if (wallet == null) {
            return false;
        }
        wallet.setBalance(wallet.getBalance().add(delta));
        return true;
    }

    public boolean deductBalance(BigDecimal delta) {
        if (wallet == null) {
            return false;
        }
        wallet.setBalance(wallet.getBalance().subtract(delta));
        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}