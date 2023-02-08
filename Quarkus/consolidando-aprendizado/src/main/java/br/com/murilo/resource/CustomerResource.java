package br.com.murilo.resource;

import br.com.murilo.model.Customer;
import br.com.murilo.service.CustomerService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerService customerService;

    @GET
    public Response findAllCustomers(){
        return Response.status(Response.Status.OK.getStatusCode()).entity(customerService.findAllCustomer()).build();
    }
//
//    @GET
//    public List<Customer> findAllCustomers(){
//        return customerService.findAllCustomer();
//    }

    @PUT
    public Response updateUser(){
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }


    @POST
    @Transactional
    public Response addCustomer(Customer customer){
        customerService.addCustomer(customer);
        return Response.status(Response.Status.OK.getStatusCode()).build();
    }

}
