package fa.training.dao.impl;

import fa.training.config.HibernateConfig;
import fa.training.dao.ServiceUsageDao;
import fa.training.entity.ServiceUsage;
import fa.training.entity.pk.ServiceUsagePk;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceUsageDaoImpl implements ServiceUsageDao {

    @Override
    public boolean saveServiceUsage(ServiceUsage serviceUsage) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(serviceUsage);

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
    public boolean updateServiceUsage(ServiceUsage serviceUsage) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(serviceUsage);

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
    public boolean deleteServiceUsage(ServiceUsagePk id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            ServiceUsage serviceUsage = session.get(ServiceUsage.class, id);
            if (serviceUsage != null) {
                session.delete(serviceUsage);
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
    public ServiceUsage getServiceUsage(ServiceUsagePk id) {
        Transaction transaction = null;
        ServiceUsage serviceUsage = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            serviceUsage = session.get(ServiceUsage.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return serviceUsage;
    }

    @Override
    public List<ServiceUsage> getAllServiceUsage() {
        Transaction transaction = null;
        List<ServiceUsage> listOfServiceUsage = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfServiceUsage = session.createQuery("from ServiceUsage").getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfServiceUsage;
    }
}
