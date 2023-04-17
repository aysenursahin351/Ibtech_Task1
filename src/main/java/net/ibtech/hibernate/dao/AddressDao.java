package net.ibtech.hibernate.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.ibtech.hibernate.model.Address;
import net.ibtech.hibernate.util.HibernateUtil;

public class AddressDao {

    public void addAddress(Address address) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(address);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public void updateAddress(Address address) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(address);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public void deleteAddress(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Address address = session.get(Address.class, id);
            if (address != null) {
                session.delete(address);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public Address getAddressById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Address address = session.get(Address.class, id);
            return address;
        } catch (Exception e) {
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
            return null;
        }
    }

    public List<Address> getAllAddresses() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Address> addresses = session.createQuery("FROM Address", Address.class).list();
            return addresses;
        } catch (Exception e) {
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
            return null;
        }
    }
}