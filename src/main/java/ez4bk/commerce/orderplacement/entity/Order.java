package ez4bk.commerce.orderplacement.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Order implements Serializable {
    private String id;

    private String description;

    private Integer customerId;

    private Integer addressId;

    private Integer merchantId;

    private Long actualPayment;

    // 0 - closed, 1 - pending, -1 - error
    private Byte status;

    // 0 - paid, 1 - unpaid, -1 - error
    private Byte paymentStatus;

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
        sb.append(", customerId=").append(customerId);
        sb.append(", addressId=").append(addressId);
        sb.append(", merchantId=").append(merchantId);
        sb.append(", actualPayment=").append(actualPayment);
        sb.append(", status=").append(status);
        sb.append(", paymentStatus=").append(paymentStatus);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public static class status {
        public static final Byte CLOSED = 0;
        public static final Byte PENDING = 1;
        public static final Byte ERROR = -1;
    }

    public static class paymentStatus {
        public static final Byte PAID = 0;
        public static final Byte UNPAID = 1;
        public static final Byte ERROR = -1;
    }
}