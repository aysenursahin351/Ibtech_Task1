package net.ibtech.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.SystemException;

import net.ibtech.hibernate.dao.AddressDao;
import net.ibtech.hibernate.dao.CustomerDao;
import net.ibtech.hibernate.dao.PhoneDao;
import net.ibtech.hibernate.model.Address;
import net.ibtech.hibernate.model.Customer;
import net.ibtech.hibernate.model.Phone;

public class App {

	

	public static void main(String[] args) throws IllegalStateException, SystemException {
	    CustomerDao customerDao = new CustomerDao();

	    // create and save customer
	    Customer customer = new Customer("John", "Doe", "johndoe@example.com", new ArrayList<>(), new ArrayList<>());
	    customerDao.saveCustomer(customer);

	    // get the saved customer
	    Customer savedCustomer = customerDao.getCustomer(customer.getId());

	    // add addresses to customer
	    Address address1 = new Address("123 Main St",  "Anytown", "TX", "12345");
	    Address address2 = new Address("456 Oak Ave",  "Somewhere", "CA", "67890");
	    savedCustomer.getAddresses().add(address1);
	    savedCustomer.getAddresses().add(address2);

	    // update the customer with new addresses
	    customerDao.updateCustomer(savedCustomer);

	    // get the updated customer
	    Customer updatedCustomer = customerDao.getCustomer(savedCustomer.getId());

	    // add phones to customer
	    Phone phone1 = new Phone("555-1234", "Home");
	    Phone phone2 = new Phone("555-5678", "Business");
	    updatedCustomer.getPhones().add(phone1);
	    updatedCustomer.getPhones().add(phone2);

	    // update the customer with new phones
	    customerDao.updateCustomer(updatedCustomer);

	    // get all customers
	    List<Customer> customers = customerDao.getCustomers();
	    customers.forEach(c -> System.out.println(c.getFirstName()));

	    // get single customer
	    Customer customer2 = customerDao.getCustomer(1);
	    System.out.println(customer2.getFirstName());

	    // delete customer
	    customerDao.deleteCustomer(1);
	    
	    PhoneDao phoneDao = new PhoneDao();

        // Yeni bir telefon ekleme
        Phone phone3 = new Phone();
        phone3.setNumber("5551234567");
        phone3.setType("Mobile");
        phoneDao.savePhone(phone3);
        
        
        AddressDao addressDao = new AddressDao();

        // Adres ekleme
        Address address3 = new Address("123 Main St", "Anytown", "CA", "12345");
        addressDao.addAddress(address3);

        // Adres güncelleme
        Address address4 = addressDao.getAddressById(1);
        address2.setZip("54321");
        addressDao.updateAddress(address4);

        // Adres silme
        addressDao.deleteAddress(1);

        // Tüm adresleri getirme
        List<Address> addresses = addressDao.getAllAddresses();
        for (Address address : addresses) {
            System.out.println(address.getStreet() + ", " + address.getCity() + ", " + address.getState() + " " + address.getZip());
        }
    }
	    
}