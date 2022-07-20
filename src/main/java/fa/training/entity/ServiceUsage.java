package fa.training.entity;

import fa.training.entity.pk.ServiceUsagePk;
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
@Table(name = "SUDUNGDICHVU")
public class ServiceUsage {
    @EmbeddedId
    private ServiceUsagePk serviceUsagePk;

    @ToString.Exclude
    @ManyToOne
    @MapsId(value = "customerId")
    @JoinColumn(name = "MaKH")
    private Customer customer;

    @ToString.Exclude
    @ManyToOne
    @MapsId(value = "serviceId")
    @JoinColumn(name = "MaDV")
    private Service service;

    @Column(name = "SoLuong")
    private int amount;

    public ServiceUsage(Customer customer, Service service, LocalDate dateUsage, LocalTime timeUsage, int amount) {
        this.customer = customer;
        this.service = service;
        this.amount = amount;
        this.serviceUsagePk = new ServiceUsagePk(customer, service, dateUsage, timeUsage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ServiceUsage)) {
            return false;
        }
        ServiceUsage that = (ServiceUsage) o;
        return Objects.equals(serviceUsagePk, that.serviceUsagePk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceUsagePk);
    }
}
