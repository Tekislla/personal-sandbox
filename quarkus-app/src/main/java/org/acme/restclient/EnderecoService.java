package org.acme.restclient;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/address")
@RegisterRestClient
public interface EnderecoService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("find")
	public List<Endereco> findAll();

}
