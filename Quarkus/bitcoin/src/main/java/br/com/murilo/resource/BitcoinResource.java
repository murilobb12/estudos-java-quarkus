package br.com.murilo.resource;

import br.com.murilo.model.Bitcoin;
import br.com.murilo.model.TipoEnum;
import br.com.murilo.repository.BitcoinRepository;


import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/bitcoins")
public class BitcoinResource {

    @Inject
    BitcoinRepository bitcoinRepository;

    @Inject
    Bitcoin bitcoin;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Bitcoin> listar() {
        return bitcoinRepository.listAll();
    }

    @Transactional
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Bitcoin salvarBitcoin(@Valid Bitcoin bitcoin) {

        bitcoin.persist();

        return bitcoin;

    }


}