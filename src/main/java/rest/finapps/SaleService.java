package rest.finapps;

import bd.Database;
import com.google.gson.Gson;
import logica.Sale;

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
@Path("/sales")
public class SaleService {

    /**
     * The (shared) address book object.
     */

    Database database = new Database();

    /**
     * A GET /contacts request should return the address book in JSON.
     * @return a JSON representation of the address book.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSales() {
        Gson gson = new Gson();
        return Response.ok(gson.toJson(database.obtenerListadoVentas())).build();
    }

    /**
     * A POST /contacts request should add a new entry to the address book.
     * @param info the URI information of the request
     * @return a JSON representation of the new entry that should be available at /contacts/person/{id}.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addSale(@Context UriInfo info, Sale sale) {
        boolean nuevo = database.insertarVenta(sale);
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
    @Path("/sale/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSale(@PathParam("id") int id) {
        Sale nuevo = database.obtenerVenta(id);
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
    @Path("/sale/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSale(@Context UriInfo info,
                                 @PathParam("id") int id, Sale sale) {
        boolean nuevo = database.modificarVenta(sale);
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
    @Path("/sale/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSale(@PathParam("id") int id) {
        boolean nuevo = database.eliminarVenta(id);
        if(nuevo == true){
            return Response.status(Status.OK).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}