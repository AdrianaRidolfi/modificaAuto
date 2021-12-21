package org.modifica.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.modifica.db.DBMongo;

import org.jboss.logging.Logger;

@Path("/garage") 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ModificaResource {
	
	@Inject
	DBMongo garage;

	private static final Logger LOG = Logger.getLogger(ModificaResource.class);
	
	@PATCH
	@Path("/auto/{id}/modifica-{campo}/{parametro}")
	public Response modificaAuto(@PathParam("id") int id, @PathParam("campo") String campo, @PathParam("parametro") String parametro) {
		
		if(garage.getAuto(id) != null) {
			garage.modificaAuto(id, campo, parametro);
			LOG.info("Modifica auto riuscita");
			LOG.debug("Modifica auto " + campo + " auto " + id + " in " + parametro);
			return Response.ok().build();
		}
		
		LOG.error("Modifica dell'auto non riuscita, l'id dell'auto da modificare non e' esistente");
		return Response.status(Status.NOT_FOUND).build();
	}

}
