package Modelo;

import java.util.Map;

public class Producto {

    //VARIABLES DE LA SECCION PRODUCTO
    private int cantidad;
    private String codigo;
    private String nombre;
    private double precio_item;
    private double precio_item_sin_igv;
    private double indExe;
    private double igv;
    private double monto_item;
    //Nueva variable para factura exportacion
    private String cod_desc_item;//VLR

    public String getCod_desc_item() {
        return cod_desc_item;
    }

    public double getMonto_item() {
        return monto_item;
    }

    //NUEVA VARIABLE PARA NOTA DE CREDITO
    private int cod_sunat;
    //NUEVA VARIABLE PARA FACTURA DESCUENTO ITEM
    private double descuentoItem;

    public double getDescuentoItem() {
        return descuentoItem;
    }

    // MAPEO DEL IndExe
    private static final Map<String, String> CodigoTipoIGV = Map.ofEntries(
            Map.entry("10", "1000"),
            Map.entry("11", "9996"),
            Map.entry("12", "9996"),
            Map.entry("13", "9996"),
            Map.entry("14", "9996"),
            Map.entry("15", "9996"),
            Map.entry("16", "9996"),
            Map.entry("17", "1016"),
            Map.entry("20", "9997"),
            Map.entry("21", "9996"),
            Map.entry("30", "9998"),
            Map.entry("31", "9996"),
            Map.entry("32", "9996"),
            Map.entry("33", "9996"),
            Map.entry("34", "9996"),
            Map.entry("35", "9996"),
            Map.entry("36", "9996"),
            Map.entry("37", "9996"),
            Map.entry("40", "9995")
    );

    public Producto() {
    }

    public Producto(int cantidad, String codigo, String nombre, double precio_item,
            double precio_item_sin_igv, double indExe, double igv,
            double monto_item, int cod_sunat, double descuentoItem, String cod_desc_item) {
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_item = precio_item;
        this.precio_item_sin_igv = precio_item_sin_igv;
        this.indExe = indExe;
        this.igv = igv;
        this.monto_item = monto_item;
        this.cod_sunat = cod_sunat;
        this.descuentoItem = descuentoItem;
        this.cod_desc_item = cod_desc_item;
    }

    private String obtenerCodigoSegunIndExe(double indExe) {
        String clave = String.valueOf((int) indExe);
        return CodigoTipoIGV.getOrDefault(clave, "0000");
    }

    // METODO STRING MASIVO
    public String stringConNumeroItem(int index) {
        StringBuilder sb = new StringBuilder();
        String codigoTipoIgv = obtenerCodigoSegunIndExe(indExe);
        sb.append("B;NroLinDet;").append(index).append(";").append(index).append("\n");
        sb.append("B;QtyItem;").append(index).append(";").append(cantidad).append("\n");
        sb.append("B;UnmdItem;").append(index).append(";NIU").append("\n");
        sb.append("B;VlrCodigo").append(index).append(";").append(codigo).append("\n");
        sb.append("B;NmbItem;").append(index).append(";").append(nombre).append("\n");
        sb.append("B;PrcItem;").append(index).append(";").append(precio_item).append("\n");
        sb.append("B;PrcItemSinIgv;").append(index).append(";").append(precio_item_sin_igv).append("\n");
        sb.append("B;MontoItem;").append(index).append(";").append(monto_item).append("\n");
        sb.append("B;IndExe;").append(index).append(";").append(indExe).append("\n");
        sb.append("B;CodigoTipoIgv;").append(index).append(";").append(codigoTipoIgv).append("\n");
        sb.append("B;TasaIgv;").append(index).append(";").append(igv).append("\n");
        sb.append("B;ImpuestoIgv;").append(index).append(";").append(igv).append("\n");
        return sb.toString();
    }

    public String productoNotaCredito(int index) {
        StringBuilder sb = new StringBuilder();
        String codigoTipoIgv = obtenerCodigoSegunIndExe(indExe);
        sb.append("B;NroLinDet;").append(index).append(";").append(index).append("\n");
        sb.append("B;QtyItem;").append(index).append(";").append(cantidad).append("\n");
        sb.append("B;UnmdItem;").append(index).append(";NIU").append("\n");
        if (!codigo.isEmpty() && !codigo.isBlank()) {
            sb.append("B;VlrCodigo").append(index).append(";").append(codigo).append("\n");
        }
        sb.append("B;NmbItem;").append(index).append(";").append(nombre).append("\n");
        sb.append("B;PrcItem;").append(index).append(";").append(precio_item).append("\n");
        sb.append("B;PrcItemSinIgv;").append(index).append(";").append(precio_item_sin_igv).append("\n");
        sb.append("B;MontoItem;").append(index).append(";").append(monto_item).append("\n");
        sb.append("B;IndExe;").append(index).append(";").append(indExe).append("\n");
        sb.append("B;CodigoTipoIgv;").append(index).append(";").append(codigoTipoIgv).append("\n");
        sb.append("B;TasaIgv;").append(index).append(";").append(igv).append("\n");
        sb.append("B;ImpuestoIgv;").append(index).append(";").append(igv).append("\n");
        sb.append("B;CodigoProductoSunat;").append(index).append(";").append(cod_sunat).append("\n");
        return sb.toString();
    }

    //Bloque B1 de Factura descuento item
    public String facDescItem(int indice) {
        StringBuilder sb = new StringBuilder();
        double montoBaseCargoDescuento = (getMonto_item() + getDescuentoItem());
        double factorCargoDescuento = (getDescuentoItem() / montoBaseCargoDescuento);
        sb.append("B1;NroLinDet;").append(indice).append(";").append(indice).append("\n");
        sb.append("B1;IndCargoDescuento;").append(indice).append(";0\n");
        sb.append("B1;CodigoCargoDescuento;").append(indice).append(";00\n");
        sb.append("B1;FactorCargoDescuento;").append(indice).append(";").append(factorCargoDescuento).append("\n");
        sb.append("B1;MontoCargoDescuento;").append(indice).append(";").append(descuentoItem).append("\n");
        sb.append("B1;MBaseCargoDescuento;").append(indice).append(";").append(montoBaseCargoDescuento).append("\n");
        return sb.toString();
    }

    public String facExporItem(int index) {
        StringBuilder sb = new StringBuilder();
        String codigoTipoIgv = obtenerCodigoSegunIndExe(indExe);
        sb.append("B;NroLinDet;").append(index).append(";").append(index).append("\n");
        sb.append("B;QtyItem;").append(index).append(";").append(cantidad).append("\n");
        sb.append("B;UnmdItem;").append(index).append(";NIU").append("\n");
        sb.append("B;VlrCodigo").append(index).append(";").append(cod_desc_item).append("\n");
        sb.append("B;CodigoProductoSunat;").append(index).append(";").append(cod_sunat).append("\n");
        sb.append("B;NmbItem;").append(index).append(";").append(nombre).append("\n");
        sb.append("B;PrcItem;").append(index).append(";").append(precio_item).append("\n");
        sb.append("B;PrcItemSinIgv;").append(index).append(";").append(precio_item_sin_igv).append("\n");
        sb.append("B;MontoItem;").append(index).append(";").append(monto_item).append("\n");
        sb.append("B;IndExe;").append(index).append(";").append(indExe).append("\n");
        sb.append("B;CodigoTipoIgv;").append(index).append(";").append(codigoTipoIgv).append("\n");
        sb.append("B;TasaIgv;").append(index).append(";").append("18\n");
        sb.append("B;ImpuestoIgv;").append(index).append("0.00\n");
        return sb.toString();
    }

    public String facInafContado(int index) {
        StringBuilder sb = new StringBuilder();
        String codigoTipoIgv = obtenerCodigoSegunIndExe(indExe);
        sb.append("B;NroLinDet;").append(index).append(";").append(index).append("\n");
        sb.append("B;QtyItem;").append(index).append(";").append(cantidad).append("\n");
        sb.append("B;UnmdItem;").append(index).append(";NIU").append("\n");
        sb.append("B;VlrCodigo").append(index).append(";").append(cod_desc_item).append("\n");
        sb.append("B;NmbItem;").append(index).append(";").append(nombre).append("\n");
        sb.append("B;PrcItem;").append(index).append(";").append(precio_item).append("\n");
        sb.append("B;PrcItemSinIgv;").append(index).append(";").append(precio_item_sin_igv).append("\n");
        sb.append("B;MontoItem;").append(index).append(";").append(monto_item).append("\n");
        sb.append("B;IndExe;").append(index).append(";").append(indExe).append("\n");
        sb.append("B;CodigoTipoIgv;").append(index).append(";").append(codigoTipoIgv).append("\n");
        sb.append("B;TasaIgv;").append(index).append(";").append("18\n");
        sb.append("B;ImpuestoIgv;").append(index).append("0.00\n");
        return sb.toString();
    }
}
