package fa.training.entity.pk;

import fa.training.entity.Customer;
import fa.training.entity.Service;
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
public class ServiceUsagePk implements Serializable {
    private String customerId;
    private String serviceId;

    @Column(name = "NgaySuDung")
    private LocalDate dateUsage;

    @Column(name = "GioSuDung")
    private LocalTime timeUsage;

    public ServiceUsagePk(Customer customer, Service service, LocalDate dateUsage, LocalTime timeUsage) {
        this(customer.getCustomerId(), service.getServiceId(), dateUsage, timeUsage);
    }
}
