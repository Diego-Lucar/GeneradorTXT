package Servicio;

import Modelo.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExcelReaderMasivo {

    public static Queue<Boleta> leerBoletas(String ruta) {

        Queue<Boleta> cola = new LinkedList<>();

        try (FileInputStream fi = new FileInputStream(new File(ruta)); Workbook wb = new XSSFWorkbook(fi)) {
            Sheet s = wb.getSheet("FAC_BOL");

            for (int fila = 1; fila <= s.getLastRowNum(); fila++) {
                Row f = s.getRow(fila);
                if (f == null)continue;
                String fechaEmision = getFecha(f.getCell(3));/////
                String tipoDocumento = getString(f, 0);
                String serie = getString(f, 1);
                String correlativo = getString(f, 2);
                int tipoRutReceptor = getInt(f, 4);
                int rutRecept = getInt(f, 5);
                String rznSocRecep = getString(f, 6);
                String dirRecep = getString(f, 7);
                int tipoOperacion = getInt(f, 8);
                String tipoMoneda = getString(f, 9);
                double MntTotGrat = getDouble(f, 10);
                double MntNeto = getDouble(f, 11);
                int montoImpuesto = getInt(f, 13);
                double MntTotal = getDouble(f, 14);
                String formaPago = getString(f, 15);
                String fechaVencimiento = "";
                if (formaPago.equalsIgnoreCase("credito")) {
                    fechaVencimiento = LocalDate.parse(fechaEmision).plusDays(30).toString();
                }
                if (formaPago.isEmpty()==true)formaPago="Contado";
                double montoNetoPendPago = getDouble(f, 16);
                int cod_detrac = getInt(f, 20);
                int porcentaje_detrac = getInt(f, 21);
                double monto_detrac = getDouble(f, 22);
                String valor_detrac = getString(f, 23);
                
                List<Producto> items = new ArrayList<>();
                int colItemBase = 39;
                for (int i = 0; i < 10; i++) {
                    int colItem= colItemBase + (i * 13);
                    int cantidad = getInt(f, colItem);
                    String codigo = getString(f, colItem + 1);
                    String nombre = getString(f, colItem + 2);
                    double precio_item  = getDouble(f, colItem + 4);
                    double precio_item_sin_igv = getDouble(f, colItem + 5);
                    double indExe = getDouble(f, colItem + 6);
                    double igv = getDouble(f, colItem + 7);
                    double monto_item = getDouble(f, colItem + 8);
                    
                    if (codigo != null && !codigo.isBlank()) {
                        items.add(new Producto(cantidad, codigo, nombre, precio_item, precio_item_sin_igv,
                            indExe, igv, monto_item));
                    }
                }
                Boleta b = new Boleta(tipoDocumento, serie, correlativo, tipoOperacion, tipoMoneda,
                        tipoRutReceptor, rutRecept, rznSocRecep, dirRecep, formaPago,
                        montoNetoPendPago, MntTotGrat, MntNeto, MntTotal,
                        montoImpuesto, items, cod_detrac, porcentaje_detrac, monto_detrac, valor_detrac,
                fechaEmision,fechaVencimiento);
                cola.add(b);
            }
        } catch (IOException e) {
            throw new RuntimeException("ERROR" + e.getMessage());
        }
        return cola;
    }

    private static String getString(Row r, int col) {
        Cell c = r.getCell(col);
        if (c == null) {
            return "";
        }
        c.setCellType(CellType.STRING);
        return c.getStringCellValue().trim();
    }

    private static int getInt(Row r, int col) {
        Cell c = r.getCell(col);
        if (c == null) {
            return 0;
        }
        if (c.getCellType() == CellType.STRING) {
            return Integer.parseInt(c.getStringCellValue());
        }
        return (int) c.getNumericCellValue();
    }

    private static double getDouble(Row r, int col) {
        Cell c = r.getCell(col);
        if (c == null) {
            return 0;
        }
        if (c.getCellType() == CellType.STRING) {
            return Double.parseDouble(c.getStringCellValue());
        }
        return c.getNumericCellValue();
    }
    
    private static String getFecha(Cell cell) {
        if (cell == null) {
            return "";
        }

        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            LocalDate date = cell.getLocalDateTimeCellValue().toLocalDate();
            return date.toString(); // yyyy-MM-dd
        }

        // Si ya viene como texto
        return cell.toString().trim();
    }


}
