package fa.training.dao.impl;

import fa.training.config.HibernateConfig;
import fa.training.dao.CustomerDao;
import fa.training.entity.Customer;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean saveCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(customer);

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
    public boolean updateCustomer(Customer customer) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(customer);

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
    public boolean deleteCustomer(String id) {
        Transaction transaction = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

//            Customer customer = session.get(Customer.class, id);
//            if (customer != null) {
//                String deleteDevice = "delete from DeviceUsage du where du.deviceUsagePk.customerId = :id";
//                session.createQuery(deleteDevice).setParameter("id", id).executeUpdate();
//                String deleteService = "delete from ServiceUsage su where su.serviceUsagePk.customerId = :id";
//                session.createQuery(deleteService).setParameter("id", id).executeUpdate();
//                session.delete(customer);
//            } else {
//                throw new Exception("No item found in db.");
//            }

            String updateDeviceStatus = "update MAY set TrangThai = 'inactive' from MAY m join SUDUNGMAY sdm on m.MaMay = sdm.MaMay where sdm.MaKH = :id";
            session.createNativeQuery(updateDeviceStatus).setParameter("id", id).executeUpdate();

            String deleteDevice = "delete from SUDUNGMAY where MaKH = :id";
            session.createNativeQuery(deleteDevice).setParameter("id", id).executeUpdate();

            String deleteService = "delete from SUDUNGDICHVU where MaKH = :id";
            session.createNativeQuery(deleteService).setParameter("id", id).executeUpdate();

            String deleteCustomer = "delete from KHACHHANG where MaKH = :id";
            session.createNativeQuery(deleteCustomer).setParameter("id", id).executeUpdate();

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
    public Customer getCustomer(String id) {
        Transaction transaction = null;
        Customer customer = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            customer = session.get(Customer.class, id);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomer() {
        Transaction transaction = null;
        List<Customer> listOfCustomer = null;
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            listOfCustomer = session.createQuery("from Customer").getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return listOfCustomer;
    }
}
