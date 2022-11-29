package team.globaloptima;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("customers")
public class CustomerResource {

    @Inject
    CustomerService customerBean;

    @GET
    @Operation(summary = "Get customers list", description = "Returns a list of all customers.")
    @APIResponses({
            @APIResponse(description = "List of customers", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Customer.class,
                                                        type = SchemaType.ARRAY)))
    })
    public Response getAllCustomers() {
        List<Customer> customers = customerBean.getCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Operation(summary = "Get customer details", description = "Return details of one customer with selected id")
    @APIResponses({
            @APIResponse(description = "Customer", responseCode = "200",
                      content = @Content(schema = @Schema(implementation = Customer.class)))
    })
    @Path("{customerId}")
    public Response getCustomer(@PathParam("customerId") Integer customerId) {
        Customer customer = customerBean.getCustomer(customerId);
        return customer != null
                ? Response.ok(customer).build()
                : Response.ok(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Operation(summary = "Create new customer", description = "Create new customer")
    @APIResponses({
            @APIResponse(responseCode = "204",
                    description = "New customer created"
            )
    })
    public Response addNewCustomer(@RequestBody(
            description = "JSON object with Customer data",
            required = true, content = @Content(
            schema = @Schema(implementation = Customer.class))) Customer customer) {
        customerBean.saveCustomer(customer);
        return Response.noContent().build();
    }

    @DELETE
    @Operation(description = "Delete Customer", summary = "Delete customer")
    @APIResponses({
            @APIResponse(
                    responseCode = "204",
                    description = "Customer successfully deleted."
            )
    })
    @Path("{customerId}")
    public Response deleteCustomer(@Parameter(description = "Customer ID.", required = true)
                                       @PathParam("customerId") Integer customerId) {
        customerBean.deleteCustomer(customerId);
        return Response.noContent().build();
    }
}
