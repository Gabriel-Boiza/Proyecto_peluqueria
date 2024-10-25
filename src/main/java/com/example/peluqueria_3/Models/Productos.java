package com.example.peluqueria_3.Models;

public class Productos {
    private int idProducto;
    private String nombre;
    private String marca;
    private String descripcion;
    private float precio;
    private int stock;
    private String codigoBarras;

    public Productos(int idProducto, String nombre, String marca, String descripcion, float precio, int stock, String codigoBarras) {
        this.nombre = nombre;
        this.idProducto = idProducto;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.codigoBarras = codigoBarras;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", marca='" + marca + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", codigoBarras='" + codigoBarras + '\'' +
                '}';
    }
}
