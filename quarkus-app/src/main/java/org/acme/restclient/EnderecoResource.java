package org.acme.restclient;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/address")
public class EnderecoResource {
	@Inject
	@RestClient
	EnderecoService flavorClient;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Endereco> findAll() {
		return flavorClient.findAll();
	}
	
	
	
}
