package org.acme;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.entity.Person;

import io.quarkus.security.Authenticated;

@Authenticated
@Path("/person")
public class PersonResource {

	@POST
	@Transactional
	public Person create(Person person) {
		person.persist();
		return person;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Person> read() {
		return Person.listAll();
	}

	@PUT
	@Transactional
	@Path("/{id}")
	public Person update(@PathParam("id") Long id, Person person) {
		Person personDatabase = Person.findById(id);
		personDatabase.name = person.name;
		personDatabase.email = person.email;

		return personDatabase;
	}

	@DELETE
	@Transactional
	@Path("/{id}")
	public void delete(@PathParam("id") Long id) {
		Person.deleteById(id);
	}

}