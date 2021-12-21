package org.modifica.db;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.modifica.Auto;
import org.modifica.db.DBMongo;
import org.jboss.logging.Logger;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;

@ApplicationScoped
public class DBMongo implements DBInterface{
	
	@Inject
	MongoClient mongoClient;
	private static final Logger LOG = Logger.getLogger(DBMongo.class);
	
	@Override
	public void modificaAuto(int id, String campo, String parametro) {
		Bson filtro = eq("id", id);
		Bson operazione = set(campo,parametro);
		try {
			mongoClient.getDatabase("automobili").getCollection("garage").updateOne(filtro, operazione);
		} catch (Exception e) {
			LOG.error(e.getStackTrace());

		}
	}
	
	@Override
	public Auto getAuto(int id) {
		
		Gson gson = new Gson();
		try {
			String json = mongoClient.getDatabase("automobili").getCollection("garage")
					.find(new Document("id",id)).first().toJson();
			Auto auto = gson.fromJson(json, Auto.class);
			LOG.debug(json);
			return auto;
		} catch (NullPointerException n) {
			LOG.error("ID " + id + " FUORI RANGE, NULLPOINTER EXCEPTION");
			return null;
		}catch (Exception e) {
			LOG.error(e.getStackTrace());
			return null;
		}
	}


}
