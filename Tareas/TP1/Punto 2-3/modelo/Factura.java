package modelo;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int numero;
    private String fechaVenta;
    private List<ItemFactura> items;

    public Factura(int numero, String fechaVenta) {
        this.numero = numero;
        this.fechaVenta = fechaVenta;
        this.items = new ArrayList<>();
    }

    public double importeTotal() {
        double total = 0;
        for (ItemFactura item : items) {
            total += item.getPrecio() * item.getCantidad();
        }
        return total;
    }

    public void agregarItem(Articulo articulo, int cantidad) throws StockInsuficienteException, ArticuloRepetidoException {
        if (cantidad > articulo.getCantidad()) {
            throw new StockInsuficienteException("No hay stock suficiente para el artículo: " + articulo.getDescripcion());
        }

        for (ItemFactura item : items) {
            if (item.getArticulo().equals(articulo)) {
                throw new ArticuloRepetidoException("El artículo ya ha sido agregado a la factura: " + articulo.getDescripcion());
            }
        }

        ItemFactura newItem = new ItemFactura(articulo, cantidad, articulo.getPrecio());
        items.add(newItem);
        articulo.setCantidad(articulo.getCantidad() - cantidad);
    }
}
