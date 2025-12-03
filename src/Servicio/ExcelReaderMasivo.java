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

            for (int fila = 2; fila <= s.getLastRowNum(); fila++) {
                Row f = s.getRow(fila);
                if (f == null) {
                    continue;
                }
                String fechaEmision = getFecha(f.getCell(3));
                /////
                String tipoDocumento = getString(f, 0);
                String serie = getString(f, 1);
                String correlativo = getString(f, 2);
                int tipoRutReceptor = getInt(f, 4);
                int rutRecept = getInt(f, 5);
                String rznSocRecep = getString(f, 6);
                String dirRecep = getString(f, 7);
                String tipoOperacion = getString(f, 8);
                String tipoMoneda = getString(f, 9); // MONEDA
                double MntTotGrat = getDouble(f, 10); // TOT GRATUITO
                double MntNeto = getDouble(f, 11); // TOT GRAVADA
                int montoImpuesto = getInt(f, 13); // IGV
                double MntTotal = getDouble(f, 14);
                String formaPago = getString(f, 33);
                String fechaVencimiento = "";
                if (formaPago.equalsIgnoreCase("credito")) {
                    fechaVencimiento = LocalDate.parse(fechaEmision).plusDays(30).toString();
                }
                //if (formaPago.isEmpty() == true) {
                    //formaPago = "Contado";
                //}
                String cuota = getString(f, 34);
                String montoNetoPendPago = getString(f, 35);
                String fechaCuota = getString(f, 36);
                int cod_detrac = getInt(f, 18);
                int porcentaje_detrac = getInt(f, 19);
                double monto_detrac = getDouble(f, 20);
                String valor_detrac = getString(f, 21); // Cuenta bancaria
                String tipoNotaCredito = getString(f, 22); // TIPO_NC_ND
                String sustento = getString(f, 23); // MOTIVO_NC
                String TpoDocRef = getString(f, 24);
                String SerieRef = getString(f, 25);
                int FolioRef = getInt(f, 26); // CORRELATIVO REF

                List<Producto> items = new ArrayList<>();
                int colItemBase = 37;
                for (int i = 0; i < 10; i++) {
                    int colItem = colItemBase + (i * 13);
                    int cantidad = getInt(f, colItem);
                    String codigo = getString(f, colItem + 1);
                    String nombre = getString(f, colItem + 2);
                    int cod_sunat = getInt(f, colItem+3);
                    double precio_item = getDouble(f, colItem + 4);
                    double precio_item_sin_igv = getDouble(f, colItem + 5);
                    double indExe = getDouble(f, colItem + 6);
                    double igv = getDouble(f, colItem + 7);
                    double monto_item = getDouble(f, colItem + 8);
                    double descuentoItem = getDouble(f, colItem +9);
                    String cod_desc_item = getString(f, colItem+10);
                    if(!nombre.isEmpty()&& !nombre.isBlank()){
                        items.add(new Producto(cantidad, codigo, nombre, precio_item, precio_item_sin_igv,
                                indExe, igv, monto_item, cod_sunat,descuentoItem,cod_desc_item));
                    }
                }
                Boleta b = new Boleta(tipoDocumento, serie, correlativo, tipoOperacion, tipoMoneda,
                        tipoRutReceptor, rutRecept, rznSocRecep, dirRecep, formaPago,
                        montoNetoPendPago, MntTotGrat, MntNeto, MntTotal,
                        montoImpuesto, items, cod_detrac, porcentaje_detrac, monto_detrac, valor_detrac,
                        fechaEmision, fechaVencimiento, tipoNotaCredito, sustento, 
                        TpoDocRef,SerieRef,FolioRef, cuota, fechaCuota);

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
        switch (c.getCellType()) {
        case STRING:
            return c.getStringCellValue().trim();
        case NUMERIC:
            // Si es número, lo convertimos a String sin perder valor
            return String.valueOf(c.getNumericCellValue());
        case FORMULA:
            try {
                return c.getStringCellValue().trim();
            } catch (IllegalStateException e) {
                return String.valueOf(c.getNumericCellValue());
            }
        default:
            return "";
        }
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
