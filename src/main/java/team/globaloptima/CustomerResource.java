package team.globaloptima;


import com.kumuluz.ee.logs.cdi.Log;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Log
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("customers")
public class CustomerResource {

    @Inject
    CustomerService customerBean;

    @GET
    public Response getAllCustomers() {
        List<Customer> customers = customerBean.getCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("{customerId}")
    public Response getCustomer(@PathParam("customerId") Integer customerId) {
        Customer customer = customerBean.getCustomer(customerId);
        return customer != null
                ? Response.ok(customer).build()
                : Response.ok(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewCustomer(Customer customer) {
        customerBean.saveCustomer(customer);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{customerId}")
    public Response deleteCustomer(@PathParam("customerId") Integer customerId) {
        customerBean.deleteCustomer(customerId);
        return Response.noContent().build();
    }
}
