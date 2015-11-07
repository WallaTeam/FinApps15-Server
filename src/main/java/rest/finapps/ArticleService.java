package rest.finapps;

import bd.Database;
import com.google.gson.Gson;
import logica.Article;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 * Servicio que manipula personas en una lista de contactos.
 *
 */
@Path("/articles")
public class ArticleService {

	/**
	 * The (shared) address book object.
	 */

	Database database = new Database();
	LedMatrix led = new LedMatrix();

	/**
	 * A GET /contacts request should return the address book in JSON.
	 * @return a JSON representation of the address book.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticles() {
		Gson gson = new Gson();
		return Response.ok(gson.toJson(database.obtenerListadoArticulos())).build();
	}

	/**
	 * A POST /contacts request should add a new entry to the address book.
	 * @param info the URI information of the request
	 * @return a JSON representation of the new entry that should be available at /contacts/person/{id}.
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticle(@Context UriInfo info, String cadena) {
		Gson gson = new Gson();
		Article article = gson.fromJson(cadena,Article.class);
		System.out.println(article.getCode());
		boolean nuevo = database.insertarArticulo(article);
		if (nuevo == true){
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	/**
	 * A GET /contacts/person/{id} request should return a entry from the address book
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new entry or 404
	 */
	@GET
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticle(@PathParam("id") int id) {
		Article nuevo = database.obtenerArticulo(String.format("%d",id));
		if (nuevo==null){
			led.doFail();
			return Response.status(Status.NOT_FOUND).build();
		} else {
			led.doOk();
			Gson gson = new Gson();
			return Response.ok(gson.toJson(nuevo)).build();
		}
	}

	/**
	 * A GET /contacts/person/{id} request should return a entry from the address book
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new entry or 404
	 */
	@GET
	@Path("/product/detail/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getArticleDetail(@PathParam("id") int id) {
		String identificador = String.format("%d",id);
		Article nuevo = database.obtenerArticulo(identificador);
		if (nuevo==null){
			return Response.status(Status.NOT_FOUND).build();
		} else {
			Gson gson = new Gson();
			return Response.ok(gson.toJson(nuevo)).build();
		}
	}

	/**
	 * A PUT /contacts/person/{id} should update a entry if exists
	 * @param info the URI information of the request
	 * @param id the unique identifier of a person
	 * @return a JSON representation of the new updated entry or 400 if the id is not a key
	 */
	@PUT
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateArticle(@Context UriInfo info,
								 @PathParam("id") int id, String cadena) {
		Gson gson = new Gson();
		Article article = gson.fromJson(cadena,Article.class);
		boolean nuevo = database.actualizarArticulo(article);
		if (nuevo == true){
			return Response.ok(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	/**
	 * A DELETE /contacts/person/{id} should delete a entry if exists
	 * @param id the unique identifier of a person
	 * @return 204 if the request is successful, 404 if the id is not a key
	 */
	@DELETE
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateArticle(@PathParam("id") int id) {
		return Response.status(Status.NOT_FOUND).build();
	}

}