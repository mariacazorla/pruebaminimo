
    package edu.upc.dsa;

import edu.upc.dsa.models.Pedido;
import edu.upc.dsa.models.Producto;
import edu.upc.dsa.models.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

    public class ProductManagerImplTest {

        private ProductManager manager;

        @Before
        public void setUp() {
            manager = ProductManagerImpl.getInstance();

            // Añadir datos iniciales
            manager.addProducto("p1", "Bocadillo", 3.5);
            manager.addProducto("p2", "Café", 1.5);
            manager.addUsuario("u1", "Miguel");
        }

        @After
        public void tearDown() {
            // Reiniciar instancia para que los datos no se mantengan entre tests
            manager = null;
        }

        @Test
        public void testAddProducto() {
            Producto p = manager.addProducto("p3", "Zumo", 2.0);
            assertNotNull(p);
            assertEquals("p3", p.getId());
            assertEquals("Zumo", p.getNombre());
            assertEquals(2.0, p.getPrecio(), 0.01);
        }

        @Test
        public void testRealizarPedido() {
            List<String> productos = Arrays.asList("p1", "p2");
            List<Integer> cantidades = Arrays.asList(2, 1);
            Pedido pedido = manager.realizarPedido("u1", productos, cantidades);

            assertNotNull(pedido);
            assertEquals("u1", pedido.getIdUsuario());
            assertEquals(2, pedido.getProductos().size());
        }

        @Test
        public void testProductosOrdenadosPorVentas() {
            // Forzamos pedidos para generar ventas
            manager.realizarPedido("u1", Arrays.asList("p1"), Arrays.asList(3));
            manager.realizarPedido("u1", Arrays.asList("p2"), Arrays.asList(1));

            List<Producto> productosOrdenados = manager.getProductosOrdenadosPorVentas();
            assertEquals("p1", productosOrdenados.get(0).getId());
            assertTrue(productosOrdenados.get(0).getNVentas() >= productosOrdenados.get(1).getNVentas());
        }

        @Test
        public void testServirPedido() {
            Pedido pedido = manager.realizarPedido("u1", Arrays.asList("p1"), Arrays.asList(1));
            manager.servirPedido(pedido.getId());
            assertTrue(pedido.isServido());
        }
    }


