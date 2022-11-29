package team.globaloptima;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class CustomerService {

    @PersistenceContext(unitName = "team-globaloptima-customer")
    private EntityManager em;

    public Customer getCustomer(Integer customerId) {
        return em.find(Customer.class, customerId);
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = em
                .createNamedQuery("Customer.findCustomers", Customer.class)
                .getResultList();

        return customers;
    }

    @Transactional
    public void saveCustomer(Customer customer) {
        if (customer != null) {
            em.persist(customer);
        }

    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteCustomer(Integer customerId) {
        Customer customer = em.find(Customer.class, customerId);
        if (customer != null) {
            em.remove(customer);
        }
    }

}
