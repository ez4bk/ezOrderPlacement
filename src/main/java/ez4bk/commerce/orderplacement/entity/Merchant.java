package ez4bk.commerce.orderplacement.entity;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class Merchant implements Serializable {
    private Integer id;

    private String name;

    private String description;

    private Integer stock;

    private Integer status;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", stock=").append(stock);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}