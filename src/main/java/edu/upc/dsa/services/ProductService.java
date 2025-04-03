package edu.upc.dsa.services;

import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Usuario;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/product", description = "Endpoint to Product & Pedido Service")
@Path("/product")
public class ProductService {

    private final ProductManager pm;

    public ProductService() {
        this.pm = ProductManagerImpl.getInstance();
    }

    // ✅ Añadir un producto
    @POST
    @Path("/addProducto")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProducto(Producto p) {
        Producto result = this.pm.addProducto(p.getId(), p.getNombre(), p.getPrecio());
        return Response.status(201).entity(result).build();
    }

    // ✅ Añadir un usuario
    @POST
    @Path("/addUsuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUsuario(Usuario u) {
        Usuario result = this.pm.addUsuario(u.getId(), u.getNombre());
        return Response.status(201).entity(result).build();
    }

    // ✅ Obtener lista de productos ordenados por ventas
    @GET
    @Path("/productos/ordenados")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductosOrdenados() {
        List<Producto> productos = this.pm.getProductosOrdenadosPorVentas();
        GenericEntity<List<Producto>> entity = new GenericEntity<List<Producto>>(productos) {};
        return Response.status(200).entity(entity).build();
    }

    // ✅ Obtener pedidos de un usuario
    @GET
    @Path("/pedidos/{idUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPedidosUsuario(@PathParam("idUsuario") String idUsuario) {
        List<Pedido> pedidos = this.pm.getPedidosUsuario(idUsuario);
        GenericEntity<List<Pedido>> entity = new GenericEntity<List<Pedido>>(pedidos) {};
        return Response.status(200).entity(entity).build();
    }

    // ✅ Realizar un pedido
    @POST
    @Path("/realizarPedido")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response realizarPedido(PedidoRequest req) {
        Pedido pedido = this.pm.realizarPedido(req.getIdUsuario(), req.getIdsProductos(), req.getCantidades());
        return Response.status(201).entity(pedido).build();
    }

    // ✅ Marcar pedido como servido
    @PUT
    @Path("/servirPedido/{idPedido}")
    public Response servirPedido(@PathParam("idPedido") String idPedido) {
        this.pm.servirPedido(idPedido);
        return Response.status(200).entity("Pedido servido").build();
    }

    // ✅ Obtener producto por ID
    @GET
    @Path("/producto/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducto(@PathParam("id") String id) {
        Producto p = this.pm.getProducto(id);
        if (p == null) return Response.status(404).build();
        return Response.status(200).entity(p).build();
    }

    // ✅ Obtener usuario por ID
    @GET
    @Path("/usuario/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsuario(@PathParam("id") String id) {
        Usuario u = this.pm.getUsuario(id);
        if (u == null) return Response.status(404).build();
        return Response.status(200).entity(u).build();
    }

    // ✅ Tamaño total
    @GET
    @Path("/size")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSize() {
        return Response.status(200).entity(String.valueOf(this.pm.size())).build();
    }

    // ✅ Clase auxiliar para realizar pedidos
    public static class PedidoRequest {
        private String idUsuario;
        private List<String> idsProductos;
        private List<Integer> cantidades;

        public String getIdUsuario() { return idUsuario; }
        public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

        public List<String> getIdsProductos() { return idsProductos; }
        public void setIdsProductos(List<String> idsProductos) { this.idsProductos = idsProductos; }

        public List<Integer> getCantidades() { return cantidades; }
        public void setCantidades(List<Integer> cantidades) { this.cantidades = cantidades; }
    }
}

