package edu.upc.dsa;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class Main {
    // Dirección base del servidor
    public static final String BASE_URI = "http://localhost:8080/dsaApp/";

    // Inicia el servidor HTTP con configuración Swagger
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("edu.upc.dsa.services");

        // Swagger
        rc.register(io.swagger.jaxrs.listing.ApiListingResource.class);
        rc.register(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setTitle("API de Gestión de Pedidos");
        config.setVersion("1.0.0");
        config.setBasePath("/dsaApp");
        config.setResourcePackage("edu.upc.dsa.services");
        config.setScan(true);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    // Método main para ejecutar el servidor
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        StaticHttpHandler staticHttpHandler = new StaticHttpHandler("./public/");
        server.getServerConfiguration().addHttpHandler(staticHttpHandler, "/");

        System.out.println("\n🚀 Servidor activo en: " + BASE_URI);
        System.out.println("🌐 Swagger UI: http://localhost:8080/dsaApp/swagger\n");
        System.out.println("Pulsa ENTER para detener el servidor...");
        System.in.read();
        server.shutdownNow();
    }
}
