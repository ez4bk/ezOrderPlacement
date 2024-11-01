package ez4bk.commerce.orderplacement.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Merchant implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public boolean deductStock(Integer delta) {
        if (stock == null || stock - delta < 0) {
            return false;
        }
        stock -= delta;
        return true;
    }

    public void addStock(Integer delta) {
        stock += delta;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}