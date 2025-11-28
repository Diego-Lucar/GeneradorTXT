
package Servicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import javax.swing.JOptionPane;
import Modelo.Boleta;


public class GeneradorTXTMasivo {
    
    public static void validarCola(Queue<Boleta> cola, String destino){
        while (!cola.isEmpty()) {            
            Boleta b = cola.poll();
            generarTXT(b, destino);
        }
    }

    private static void generarTXT(Boleta b, String destino) {
        
        String nombre = b.getTipoDocumento() + "_" + b.getSerie() + "_" + b.getCorrelativo()+".txt";
        
        File archivo = new File(destino, nombre);
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))){
            bw.write(b.boletaMasiva());
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
