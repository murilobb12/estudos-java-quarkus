package br.com.crud.controller;

import br.com.crud.dto.ProductDto;
import br.com.crud.model.Product;
import br.com.crud.repository.ProductRepository;
import br.com.crud.service.ProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;


    @GET
    public Response listProduct(){
/*        List<Product> product = productService.listProduct();

        return Response.ok(product).build();*/

        return Response.ok(productService.listProduct()).status(Response.Status.OK).build();
    }

    @POST
    public Response insertProduct(ProductDto dto){

        Product insertProduct = productService.insertProduct(dto);

        return Response.ok(insertProduct).status(201).build();

    }

    @PUT
    @Path("{id}")
    public Response updateProduct(@PathParam("id")Long id, ProductDto dto){

        productService.updateProduct(id, dto);

        return Response.status(204).build();


    }

    @DELETE
    @Path("{id}")
    public Response deleteProduct(@PathParam("id")Long id){

        productService.deleteProduct(id);

        return Response.status(204).build();

    }

}
