package net.ibtech.hibernate.dao;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.Transaction;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.util.HibernateUtil;

public class CustomerDao {

	public CustomerDao() {
		// TODO Auto-generated constructor stub
	}

	public void saveCustomer(Customer customer) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();
	        session.save(customer);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	}

	public void insertCustomer() {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        String hql = "INSERT INTO Customer (firstName, lastName, email) "
	                + "SELECT firstName, lastName, email FROM Customer";
	        Query query = session.createQuery(hql);
	        int result = query.executeUpdate();
	        System.out.println("Rows affected: " + result);

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	}

	public void updateCustomer(Customer customer) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        // Initialize collections
	        Hibernate.initialize(customer.getAddresses());
	        Hibernate.initialize(customer.getPhones());

	        String hql = "UPDATE Customer set firstName = :firstName, lastName = :lastName, email = :email WHERE id = :customerId";
	        Query query = session.createQuery(hql);
	        query.setParameter("firstName", customer.getFirstName());
	        query.setParameter("lastName", customer.getLastName());
	        query.setParameter("email", customer.getEmail());
	        query.setParameter("customerId", customer.getId());
	        int result = query.executeUpdate();
	        System.out.println("Rows affected: " + result);

	        session.flush(); // Verilerin veritabanına kaydedilmesini sağlar
	        session.clear(); // Session önbelleğini temizler
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	}
	public void deleteCustomer(int id) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        Customer customer = session.get(Customer.class, id);
	        if (customer != null) {
	            String hql = "DELETE FROM Customer WHERE id = :customerId";
	            Query query = session.createQuery(hql);
	            query.setParameter("customerId", id);
	            int result = query.executeUpdate();
	            System.out.println("Rows affected: " + result);
	        }

	        session.flush(); // Verilerin veritabanına kaydedilmesini sağlar
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	}

	public Customer getCustomer(int id) {
	    Transaction transaction = null;
	    Customer customer = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        customer = session.get(Customer.class, id);
	        Hibernate.initialize(customer.getAddresses());

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	        // Daha ayrıntılı hata işleme yapılabilir
	    }
	    return customer;
	}

	public List<Customer> getCustomers() {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        return session.createQuery("FROM Customer", Customer.class).list();
	    }
	}
}