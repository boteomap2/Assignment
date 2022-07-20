package fa.training.dao;

import fa.training.entity.Device;
import fa.training.entity.DeviceUsage;
import fa.training.entity.pk.DeviceUsagePk;
import java.util.List;

public interface DeviceUsageDao {
    public boolean saveDeviceUsage(DeviceUsage deviceUsage, Device device);
    public boolean updateDeviceUsage(DeviceUsage deviceUsage);
    public boolean deleteDeviceUsage(DeviceUsagePk id);
    public DeviceUsage getDeviceUsage(DeviceUsagePk id);
    public List<DeviceUsage> getAllDeviceUsage();
}
