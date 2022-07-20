package fa.training.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "DICHVU")
public class Service {
    @Id
    @GenericGenerator(name = "serIdGen", strategy = "fa.training.utils.ServiceIdGenerator")
    @GeneratedValue(generator = "serIdGen")
    @Column(name = "MaDV")
    private String serviceId;

    @Column(name = "TenDV")
    private String name;

    @Column(name = "DonViTinh")
    private String unit;

    @Column(name = "DonGia")
    private int price;

    @JsonIgnore
    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceUsage> serviceUsages = new ArrayList<>();

    public Service(String name, String unit, int price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Service)) {
            return false;
        }
        Service service = (Service) o;
        return Objects.equals(serviceId, service.serviceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceId);
    }
}
