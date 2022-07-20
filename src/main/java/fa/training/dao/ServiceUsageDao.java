package fa.training.dao;

import fa.training.entity.ServiceUsage;
import fa.training.entity.pk.ServiceUsagePk;
import java.util.List;

public interface ServiceUsageDao {
    public boolean saveServiceUsage(ServiceUsage serviceUsage);
    public boolean updateServiceUsage(ServiceUsage serviceUsage);
    public boolean deleteServiceUsage(ServiceUsagePk id);
    public ServiceUsage getServiceUsage(ServiceUsagePk id);
    public List<ServiceUsage> getAllServiceUsage();
}
