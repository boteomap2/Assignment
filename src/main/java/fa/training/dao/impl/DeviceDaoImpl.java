package fa.training.dao.impl;

import fa.training.config.HibernateConfig;
import fa.training.dao.DeviceDao;
import fa.training.entity.Device;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DeviceDaoImpl implements DeviceDao {
    public boolean saveDevice(Device device) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(device);

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

    public boolean updateDevice(Device device) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

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

    public boolean deleteDevice(int id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Device device = session.get(Device.class, id);
            if (device != null) {
                session.delete(device);
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

    public Device getDevice(int id) {
        Transaction transaction = null;
        Device device = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            device = session.get(Device.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return device;
    }

    @SuppressWarnings("unchecked")
    public List<Device> getAllDevice() {
        Transaction transaction = null;
        List<Device> listOfDevice = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfDevice = session.createQuery("from Device").getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfDevice;
    }

    @Override
    public List<Device> getAllDeviceInactive() {
        Transaction transaction = null;
        List<Device> listOfDevice = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfDevice = session.createQuery("from Device D where D.status = 'inactive'").getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfDevice;
    }
}
