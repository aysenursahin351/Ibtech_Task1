package net.ibtech.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.ibtech.hibernate.model.Account;
import net.ibtech.hibernate.util.HibernateUtil;

public class AccountDao {

    public void addAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public void updateAccount(Account account) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(account);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
        }
    }

    public void deleteAccount(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Account account = session.get(Account.class, id);
            if (account != null) {
                session.delete(account);
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

    public Account getAccountById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Account account = session.get(Account.class, id);

            return account;
        } catch (Exception e) {
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
            return null;
        }
    }

    public List<Account> getAllAccounts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            List<Account> accounts = session.createQuery("from Account", Account.class).list();

            return accounts;
        } catch (Exception e) {
            e.printStackTrace();
            // Daha ayrıntılı hata işleme yapılabilir
            return null;
        }
    }
}