package edu.upc.dsa;

import edu.upc.dsa.models.*;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager {

    private static ProductManager instance;

    protected List<Producto> productos;
    protected List<Usuario> usuarios;
    protected List<Pedido> pedidos;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);

    private ProductManagerImpl() {
        this.productos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public static ProductManager getInstance() {
        if (instance == null) instance = new ProductManagerImpl();
        return instance;
    }

    @Override
    public Producto addProducto(String id, String nombre, double precio) {
        Producto p = new Producto(id, nombre, precio);
        this.productos.add(p);
        logger.info("Producto añadido: " + p);
        return p;
    }

    @Override
    public Usuario addUsuario(String id, String nombre) {
        Usuario u = new Usuario(id, nombre);
        this.usuarios.add(u);
        logger.info("Usuario añadido: " + u);
        return u;
    }

    @Override
    public Pedido realizarPedido(String idUsuario, List<String> idsProductos, List<Integer> cantidades) {
        Usuario u = getUsuario(idUsuario);
        if (u == null || idsProductos.size() != cantidades.size()) {
            logger.error("Datos inválidos para realizar el pedido");
            return null;
        }

        String idPedido = UUID.randomUUID().toString();
        Pedido pedido = new Pedido(idPedido, idUsuario);

        for (int i = 0; i < idsProductos.size(); i++) {
            Producto p = getProducto(idsProductos.get(i));
            if (p != null) {
                pedido.añadirProducto(p, cantidades.get(i));
                p.incrementarVentas(cantidades.get(i));
            }
        }

        this.pedidos.add(pedido);
        u.añadirPedido(pedido);
        logger.info("Pedido realizado: " + pedido);
        return pedido;
    }

    @Override
    public void servirPedido(String idPedido) {
        for (Pedido p : this.pedidos) {
            if (p.getId().equals(idPedido)) {
                p.setServido(true);
                logger.info("Pedido marcado como servido: " + idPedido);
                return;
            }
        }
        logger.warn("Pedido no encontrado: " + idPedido);
    }

    @Override
    public List<Producto> getProductosOrdenadosPorVentas() {
        List<Producto> copia = new ArrayList<>(this.productos);
        copia.sort(Comparator.comparingInt(Producto::getNVentas).reversed());
        logger.info("Productos ordenados por ventas");
        return copia;
    }

    @Override
    public List<Pedido> getPedidosUsuario(String idUsuario) {
        Usuario u = getUsuario(idUsuario);
        if (u != null) return u.getPedidos();
        logger.warn("Usuario no encontrado: " + idUsuario);
        return new ArrayList<>();
    }

    @Override
    public Producto getProducto(String id) {
        for (Producto p : this.productos) {
            if (p.getId().equals(id)) return p;
        }
        return null;
    }

    @Override
    public Usuario getUsuario(String id) {
        for (Usuario u : this.usuarios) {
            if (u.getId().equals(id)) return u;
        }
        return null;
    }

    @Override
    public int size() {
        logger.info("Tamaño total del sistema: " + (productos.size() + usuarios.size() + pedidos.size()));
        return productos.size() + usuarios.size() + pedidos.size();
    }
} 