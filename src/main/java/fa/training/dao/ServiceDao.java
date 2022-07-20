package fa.training.dao;

import fa.training.entity.Service;
import java.util.List;

public interface ServiceDao {
    public boolean saveService(Service service);
    public boolean updateService(Service service);
    public boolean deleteService(String id);
    public Service getService(String id);
    public List<Service> getAllService();
}
