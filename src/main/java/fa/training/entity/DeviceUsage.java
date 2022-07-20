package fa.training.entity;

import fa.training.entity.pk.DeviceUsagePk;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "SUDUNGMAY")
public class DeviceUsage {
    @EmbeddedId
    private DeviceUsagePk deviceUsagePk;

    @ToString.Exclude
    @ManyToOne
    @MapsId(value = "customerId")
    @JoinColumn(name = "MaKH")
    private Customer customer;

    @ToString.Exclude
    @ManyToOne
    @MapsId(value = "deviceId")
    @JoinColumn(name = "MaMay")
    private Device device;

    @Column(name = "ThoiGianSuDung")
    private int timeUsage;

    public DeviceUsage(Customer customer, Device device, LocalDate startDateUsage, LocalTime startTimeUsage, int timeUsage) {
        this.customer = customer;
        this.device = device;
        this.timeUsage = timeUsage;
        this.deviceUsagePk = new DeviceUsagePk(customer, device, startDateUsage, startTimeUsage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeviceUsage)) {
            return false;
        }
        DeviceUsage that = (DeviceUsage) o;
        return Objects.equals(deviceUsagePk, that.deviceUsagePk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceUsagePk);
    }
}
