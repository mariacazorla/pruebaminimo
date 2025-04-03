package edu.upc.dsa.models;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String nombre;
    private List<Pedido> pedidos;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.pedidos = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void añadirPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    @Override
    public String toString() {
        return "Usuario {" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pedidosRealizados=" + pedidos.size() +
                '}';
    }
}
