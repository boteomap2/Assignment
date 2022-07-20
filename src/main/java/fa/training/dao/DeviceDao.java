package fa.training.dao;

import fa.training.entity.Device;
import java.util.List;

public interface DeviceDao {
    public boolean saveDevice(Device device);
    public boolean updateDevice(Device device);
    public boolean deleteDevice(int id);
    public Device getDevice(int id);
    public List<Device> getAllDevice();
    public List<Device> getAllDeviceInactive();
}
