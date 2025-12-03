package Servicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import javax.swing.JOptionPane;
import Modelo.Boleta;
import Modelo.Producto;
import java.util.List;

public class GeneradorTXTMasivo {

    public static void validarCola(Queue<Boleta> cola, String destino) {
        while (!cola.isEmpty()) {
            Boleta b = cola.poll();
            generarTXT(b, destino);
        }
    }

    private static void generarTXT(Boleta b, String destino) {

        List<Producto> productos = b.getProducto();
        
        Integer igv = b.getMontoImpuesto();

        String nombre = b.getTipoDocumento() + "_" + b.getSerie() + "_" + b.getCorrelativo() + ".txt";

        File archivo = new File(destino, nombre);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            //NOTA DE DEBITO
            if (b.getTipoDocumento().equalsIgnoreCase("08")) {
                bw.write(b.NotaDebito());
                //NOTA DE CREDITO
            } else if (b.getTipoDocumento().equalsIgnoreCase("07")) {
                bw.write(b.NotaCredito());
                //FACTURA DESCUENTO ITEM
            } else if (b.getFormaPago().isEmpty()) {
                bw.write(b.FacturaDescuentoItem());
                //FACTURA INAFECTO CONTADO
            } else if (b.getMontoImpuesto()==0){
                bw.write(b.FacturaInafectoContado());
                //FACTURA EXPORTACION
            } else if (productos != null && !productos.isEmpty()) {
                boolean tieneCodDesc = false;
                for (Producto p : productos) {
                    String codDescItem = p.getCod_desc_item();
                    if (codDescItem != null && !codDescItem.isEmpty()) {
                        tieneCodDesc = true;
                        break;
                    }
                }
                if(tieneCodDesc){bw.write(b.FacturaExportacion());}
                else{bw.write(b.boletaMasiva());}
                             
            }else {
                bw.write(b.boletaMasiva());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

    }
}
