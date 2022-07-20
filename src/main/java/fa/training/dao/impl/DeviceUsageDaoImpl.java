package fa.training.dao.impl;

import fa.training.config.HibernateConfig;
import fa.training.dao.DeviceUsageDao;
import fa.training.entity.Device;
import fa.training.entity.DeviceUsage;
import fa.training.entity.pk.DeviceUsagePk;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeviceUsageDaoImpl implements DeviceUsageDao {

    @Override
    public boolean saveDeviceUsage(DeviceUsage deviceUsage, Device device) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(deviceUsage);
            device.setStatus("active");
            session.update(device);

            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDeviceUsage(DeviceUsage deviceUsage) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(deviceUsage);

            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDeviceUsage(DeviceUsagePk id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            DeviceUsage deviceUsage = session.get(DeviceUsage.class, id);
            if (deviceUsage != null) {
                session.delete(deviceUsage);
            } else {
                throw new Exception("No item found in db.");
            }

            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DeviceUsage getDeviceUsage(DeviceUsagePk id) {
        Transaction transaction = null;
        DeviceUsage deviceUsage = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            deviceUsage = session.get(DeviceUsage.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return deviceUsage;
    }

    @Override
    public List<DeviceUsage> getAllDeviceUsage() {
        Transaction transaction = null;
        List<DeviceUsage> listOfDeviceUsage = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfDeviceUsage = session.createQuery("from DeviceUsage").getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfDeviceUsage;
    }
}
