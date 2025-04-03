package edu.upc.dsa.models;

import java.util.Objects;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int nVentas;

    public Producto(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.nVentas = 0;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getNVentas() { return nVentas; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void incrementarVentas(int cantidad) { this.nVentas += cantidad; }

    @Override
    public String toString() {
        return "Producto {" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", nVentas=" + nVentas +
                '}';
    }

    // Necesario para usar como clave en Map
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}





