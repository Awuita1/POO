package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Negocio {
    private String codigo;
    private String nombre;
    private List<Articulo> stock;
    private List<Factura> facturas;

    public Negocio(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    public Articulo agregarArticulo(int codigo, String descripcion, double precio, int cantidad) {
        Articulo articulo = new Articulo(codigo, descripcion, precio, cantidad);
        stock.add(articulo);
        return articulo;
    }

    public Factura agregarFactura(int numero, LocalDate fecha, Articulo articulo, int cantidad)
            throws StockInsuficienteException, ArticuloRepetidoException {
        Factura factura = new Factura(numero, fecha.toString());
        factura.agregarItem(articulo, cantidad);
        facturas.add(factura);
        return factura;
    }

    public void cambiarPrecio(double porcCambio) {
        for (Articulo articulo : stock) {
            double nuevoPrecio = articulo.getPrecio() * (1 + porcCambio / 100);
            articulo.setPrecio(nuevoPrecio);
        }
    }

    public double stockValorizado() {
        double total = 0;
        for (Articulo articulo : stock) {
            total += articulo.getPrecio() * articulo.getCantidad();
        }
        return total;
    }
}
