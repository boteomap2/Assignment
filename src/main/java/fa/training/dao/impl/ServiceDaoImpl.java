package fa.training.dao.impl;

import fa.training.config.HibernateConfig;
import fa.training.dao.ServiceDao;
import fa.training.entity.Service;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ServiceDaoImpl implements ServiceDao {

    @Override
    public boolean saveService(Service service) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(service);

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
    public boolean updateService(Service service) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(service);

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
    public boolean deleteService(String id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

//            Service service = session.get(Service.class, id);
//            if (service != null) {
//                session.delete(service);
//            } else {
//                throw new Exception("No item found in db.");
//            }

            String deleteServiceUsage = "delete from SUDUNGDICHVU where MaDV = :id";
            session.createNativeQuery(deleteServiceUsage).setParameter("id", id).executeUpdate();

            String deleteDevice = "delete from DICHVU where MaDV = :id";
            session.createNativeQuery(deleteDevice).setParameter("id", id).executeUpdate();

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
    public Service getService(String id) {
        Transaction transaction = null;
        Service service = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            service = session.get(Service.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return service;
    }

    @Override
    public List<Service> getAllService() {
        Transaction transaction = null;
        List<Service> listOfService = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfService = session.createQuery("from Service").getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfService;
    }
}
