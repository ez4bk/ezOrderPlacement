package ez4bk.commerce.orderplacement.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private String id;

    private String description;

    private Integer userId;

    private Integer addressId;

    private Integer merchantId;

    // 0 - closed, 1 - unpaid, 2 - paid, -1 - error
    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Customer customer;

    private Address address;

    private Merchant merchant;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", description=").append(description);
        sb.append(", userId=").append(userId);
        sb.append(", addressId=").append(addressId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static class status {
        public static final Byte CLOSED = 0;
        public static final Byte UNPAID = 1;
        public static final Byte PAID = 2;
        public static final Byte ERROR = -1;
    }
}