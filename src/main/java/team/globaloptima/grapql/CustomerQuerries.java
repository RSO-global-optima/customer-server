package team.globaloptima.grapql;


import com.kumuluz.ee.logs.cdi.Log;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import team.globaloptima.Customer;
import team.globaloptima.CustomerService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;


/**
 * @author Benjamin Kastelic
 * @since 2.3.0
 */
@Log
@RequestScoped
@GraphQLApi
public class CustomerQuerries {

    @Inject
    private CustomerService customerBean;

    @Query
    public List<Customer> getAllCustomers() {
        return customerBean.getCustomers();
    }

    @Query
    public Customer getCustomer(@Name("customerId") Integer customerId) {
        return customerBean.getCustomer(customerId);
    }

    @Mutation
    public Customer addNewCustomer(@Name("customer") Customer customer) {
        customerBean.saveCustomer(customer);
        return customer;
    }

    @Mutation
    public  Boolean deleteCustomer(@Name("customerId") Integer customerId) {
        return customerBean.deleteCustomer(customerId);
    }
}

