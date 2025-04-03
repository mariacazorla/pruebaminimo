package edu.upc.dsa.models;

import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private String id;
    private String idUsuario;
    private Map<Producto, Integer> productos;
    private boolean servido;

    public Pedido(String id, String idUsuario) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.productos = new HashMap<>();
        this.servido = false;
    }

    public String getId() { return id; }
    public String getIdUsuario() { return idUsuario; }
    public Map<Producto, Integer> getProductos() { return productos; }
    public boolean isServido() { return servido; }

    public void setServido(boolean servido) { this.servido = servido; }
    public void añadirProducto(Producto producto, int cantidad) {
        this.productos.put(producto, cantidad);
    }

    @Override
    public String toString() {
        return "Pedido {" +
                "id='" + id + '\'' +
                ", usuario='" + idUsuario + '\'' +
                ", productos=" + productos +
                ", servido=" + servido +
                '}';
    }

    String IdPedido;
}
