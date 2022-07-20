package fa.training.entity.pk;

import fa.training.entity.Customer;
import fa.training.entity.Device;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Embeddable
public class DeviceUsagePk implements Serializable {

    private static final long serialVersionUID = -6791964682046574779L;
    private String customerId;
    private Integer deviceId;

    @Column(name = "NgayBatDauSuDung")
    private LocalDate startDateUsage;

    @Column(name = "GioBatDauSuDung")
    private LocalTime startTimeUsage;

    public DeviceUsagePk(Customer customer, Device device, LocalDate startDateUsage, LocalTime startTimeUsage) {
        this(customer.getCustomerId(), device.getDeviceId(), startDateUsage, startTimeUsage);
    }
}
