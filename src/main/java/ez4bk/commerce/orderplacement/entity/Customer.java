package ez4bk.commerce.orderplacement.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Customer implements Serializable {
    private Integer id;

    private String name;

    private String phone;

    private List<Address> addresses;

    private static final long serialVersionUID = 1L;

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