package Modelo;

import java.util.Map;

public class Producto {
    
    //VARIABLES DE LA SECCION PRODUCTO
    private String item;
    private int cantidad;
    private String codigo;
    private String nombre;
    private double precio_item;
    private double precio_item_sin_igv;
    private double indExe;
    private double igv;
    private double monto_item;
    
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

    public Producto(String item, int cantidad, String codigo, String nombre, double precio_item, double precio_item_sin_igv, double indExe, double igv, double monto_item) {
        this.item = item;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_item = precio_item;
        this.precio_item_sin_igv = precio_item_sin_igv;
        this.indExe = indExe;
        this.igv = igv;
        this.monto_item = monto_item;
    }

    public Producto(int cantidad, String codigo, String nombre, double precio_item, double precio_item_sin_igv, double indExe, double igv, double monto_item) {
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio_item = precio_item;
        this.precio_item_sin_igv = precio_item_sin_igv;
        this.indExe = indExe;
        this.igv = igv;
        this.monto_item = monto_item;
    }
    
    
    
    private String obtenerCodigoSegunIndExe(double indExe){
        String clave = String.valueOf((int) indExe);
        return CodigoTipoIGV.getOrDefault(clave, "0000");
    }
    // METODO STRING MASIVO
    public String stringConNumeroItem (int index){
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
}
