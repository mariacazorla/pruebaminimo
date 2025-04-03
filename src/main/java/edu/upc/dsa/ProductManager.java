package edu.upc.dsa;

import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Usuario;

import java.util.List;

    public interface ProductManager {

        // ✅ Añadir un nuevo producto al sistema
        Producto addProducto(String id, String nombre, double precio);

        // ✅ Añadir un nuevo usuario
        Usuario addUsuario(String id, String nombre);

        // ✅ Realizar un pedido (crear pedido con productos y cantidades)
        Pedido realizarPedido(String idUsuario, List<String> idsProductos, List<Integer> cantidades);

        // ✅ Marcar pedido como servido
        void servirPedido(String idPedido);

        // ✅ Listar productos ordenados por número de ventas
        List<Producto> getProductosOrdenadosPorVentas();

        // ✅ Obtener lista de pedidos de un usuario
        List<Pedido> getPedidosUsuario(String idUsuario);

        // ✅ Obtener un producto por ID
        Producto getProducto(String id);

        // ✅ Obtener un usuario por ID
        Usuario getUsuario(String id);

        // ✅ Tamaño del sistema (número total de productos, usuarios o pedidos)
        int size();
    }

