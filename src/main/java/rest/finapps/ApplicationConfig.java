package rest.finapps;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Esta clase se encarga de configurar Jersey, la librería que permite
 * crear servicios REST.
 */
public class ApplicationConfig extends ResourceConfig {




    /**
     * Main constructor
     * @param a provided address book
     */
    public ApplicationConfig() {
    	register(ArticleService.class);
		register(ClientService.class);
		register(SaleService.class);
		register(CORSFilter.class);
    	register(MOXyJsonProvider.class);

	}	

}
