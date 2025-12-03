package Modelo;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Boleta {

    private String tipoDocumento;
    private String serie;
    private String correlativo;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public String getSerie() {
        return serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    //private String fechaEmision;
    private String tipoOperacion;

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    private String tipoMoneda;

    private String rutEmis;
    private static final int tipoRucEMIS = 6;
    private String nomComer;
    private String razoSocEmi;
    private static final int codigoLocalAnexo = 0000;

    public void setRutEmis(String rutEmis) {
        this.rutEmis = rutEmis;
    }

    public void setNomComer(String nomComer) {
        this.nomComer = nomComer;
    }

    public void setRazoSocEmi(String razoSocEmi) {
        this.razoSocEmi = razoSocEmi;
    }

    public String getRutEmis() {
        return rutEmis;
    }

    public String getNomComer() {
        return nomComer;
    }

    public String getRazoSocEmi() {
        return razoSocEmi;
    }

    private int tipoRutReceptor;
    private int rutRecept;

    public int getRutRecept() {
        return rutRecept;
    }

    private String rznSocRecep;
    private String dirRecep;

    private String formaPago;

    public String getFormaPago() {
        return formaPago;
    }

    private String MontoNetoPendPago;

    public String getMontoNetoPendPago() {
        return MontoNetoPendPago;
    }

    private double MntTotGrat;
    private double MntNeto;
    private double MntTotal;
    private int montoImpuesto;

    public int getMontoImpuesto() {
        return montoImpuesto;
    }


    private int cod_detrac;
    private int porcentaje_detrac;
    private double monto_detrac;
    private String valor_detrac; //CUENTA BANCARIA

    LocalTime horaActual = LocalTime.now();
    DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm:ss");
    String horaEmision = horaActual.format(formatoHora);

    DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate fechaActual = LocalDate.now();

    String fechaEmision = fechaActual.format(formatoFecha);
    LocalDate fechaAgregada = fechaActual.plusDays(30);
    String fechaVencimiento = fechaAgregada.format(formatoFecha);

    //NUEVAS VARIABLES PARA NOTA DE DEBITO
    private String tipoNotaCredito; //TIPO_NC_ND
    private String sustento; //MOTIVO_NC_ND
    private String TpoDocRef;

    public String getTpoDocRef() {
        return TpoDocRef;
    }

    private String SerieRef;
    private int FolioRef;

    //NUEVAS VARIABLES PARA NOTA DE CREDITO
    private String cuota;
    //private String montoCuota;
    private String fechaCuota;

    public String getCuota() {
        return cuota;
    }

    public String getFechaCuota() {
        return fechaCuota;
    }

    private List<Producto> items;

    public Boleta(String tipoDocumento, String serie, String correlativo,
            String tipoOperacion, String tipoMoneda, int tipoRutReceptor,
            int rutRecept, String rznSocRecep, String dirRecep, String formaPago,
            String MontoNetoPendPago, double MntTotGrat, double MntNeto, double MntTotal,
            int montoImpuesto, List<Producto> items, int cod_detrac, int porcentaje_detrac,
            double monto_detrac, String valor_detrac, String fechaEmision, String fechaVencimiento,
            String tipoNotaCredito, String sustento, String TpoDocRef, String serieRef, int FolioRef,
            String cuota, String fechaCuota) {
        this.tipoDocumento = tipoDocumento;
        this.serie = serie;
        this.correlativo = correlativo;
        this.tipoOperacion = tipoOperacion;
        this.tipoMoneda = tipoMoneda;
        this.tipoRutReceptor = tipoRutReceptor;
        this.rutRecept = rutRecept;
        this.rznSocRecep = rznSocRecep;
        this.dirRecep = dirRecep;
        this.formaPago = formaPago;
        this.MontoNetoPendPago = MontoNetoPendPago;
        this.MntTotGrat = MntTotGrat;
        this.MntNeto = MntNeto;
        this.MntTotal = MntTotal;
        this.montoImpuesto = montoImpuesto;
        this.items = items;
        this.valor_detrac = valor_detrac;
        this.monto_detrac = monto_detrac;
        this.porcentaje_detrac = porcentaje_detrac;
        this.cod_detrac = cod_detrac;
        this.fechaEmision = fechaEmision;
        this.fechaVencimiento = fechaVencimiento;
        this.tipoNotaCredito = tipoNotaCredito;
        this.sustento = sustento;
        this.TpoDocRef = TpoDocRef;
        this.SerieRef = SerieRef;
        this.FolioRef = FolioRef;
        this.cuota = cuota;
        this.fechaCuota = fechaCuota;
    }

    public List<Producto> getProducto() {
        return items;
    }
    
    public String boletaMasiva() {
        StringBuilder sb = new StringBuilder();
        sb.append("A;CODI_EMPR;;1").append("\n");
        sb.append("A;TipoDTE;;").append(tipoDocumento).append("\n");
        sb.append("A;Serie;;").append(serie).append("\n");
        sb.append("A;Correlativo;;").append(correlativo).append("\n");
        sb.append("A;FchEmis;;").append(fechaEmision).append("\n");
        sb.append("A;HoraEmision;;").append(horaEmision).append("\n");
        sb.append("A;TipoMoneda;;").append(tipoMoneda).append("\n");
        sb.append("A;FormaPago;;").append(formaPago).append("\n");
        if (formaPago.equalsIgnoreCase("credito")) {
            sb.append("A;MontoNetoPendPago;").append(MontoNetoPendPago).append("\n");
        }
        sb.append("A;RUTEmis;;").append(getRutEmis()).append("\n");
        sb.append("A;TipoRucEmis;;").append(tipoRucEMIS).append("\n");
        sb.append("A;NomComer;;").append(getNomComer()).append("\n");
        sb.append("A;RznSocEmis;;").append(getRazoSocEmi()).append("\n");
        sb.append("A;CodigoLocalAnexo;;").append(codigoLocalAnexo).append("\n");
        sb.append("A;TipoRutReceptor;;").append(tipoRutReceptor).append("\n");
        sb.append("A;RUTRecep;;").append(rutRecept).append("\n");
        sb.append("A;RznSocRecep;;").append(rznSocRecep).append("\n");
        sb.append("A;DirRecep;;").append(dirRecep).append("\n");
        sb.append("A;TipoOperacion;;").append(tipoOperacion).append("\n");
        sb.append("A;MntNeto;;").append(MntNeto).append("\n");
        sb.append("A;MntExe;;0.00").append("\n");
        sb.append("A;MntExo;;0.00").append("\n");
        sb.append("A;MntTotGrat;;").append(MntTotGrat).append("\n");
        sb.append("A;MntTotal;;").append(MntTotal).append("\n");
        sb.append("A2;CodigoImpuesto;1;1000").append("\n");
        sb.append("A2;MontoImpuesto;1;").append(montoImpuesto).append("\n");
        sb.append("A2;MontoImpuestoBase;1;200.00").append("\n");
        sb.append("A2;TasaImpuesto;1;18").append("\n");
        if (tipoOperacion.equalsIgnoreCase("1001")) {
            sb.append("A3;codiDetraccion;1;").append(cod_detrac).append("\n");
            sb.append("A3;valorDetraccion;1;-").append("\n");
            sb.append("A3;MntDetraccion;1;").append(monto_detrac).append("\n");
            sb.append("A3;PorcentajeDetraccion;1;").append(porcentaje_detrac).append("\n");
            sb.append("A3;codiDetraccion;2;3001 ").append("\n");
            sb.append("A3;valorDetraccion;2;").append(valor_detrac).append("\n");
            sb.append("A3;MntDetraccion;2;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;2;0 ").append("\n");
            sb.append("A3;codiDetraccion;3;3000").append("\n");
            sb.append("A3;ValorDetraccion;3;025").append("\n");
            sb.append("A3;MntDetraccion;3;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;3;0").append("\n");

        }
        if (formaPago.equalsIgnoreCase("credito")) {
            sb.append("A5;Cuota;1;1").append("\n");
            sb.append("A5;MontoCuota;1;100").append("\n");
            sb.append("A5;FechaVencCuota;1;").append(fechaVencimiento).append("\n");
        }
        int index = 1;
        for (Producto p : items) {
            sb.append(p.stringConNumeroItem(index));
            index++;
        }

        return sb.toString();
    }
    
    public String FacturaInafectoContado(){
        StringBuilder sb = new StringBuilder();
        sb.append("A;CODI_EMPR;;1").append("\n");
        sb.append("A;TipoDTE;;").append(tipoDocumento).append("\n");
        sb.append("A;Serie;;").append(serie).append("\n");
        sb.append("A;Correlativo;;").append(correlativo).append("\n");
        sb.append("A;FchEmis;;").append(fechaEmision).append("\n");
        sb.append("A;HoraEmision;;").append(horaEmision).append("\n");
        sb.append("A;TipoMoneda;;").append(tipoMoneda).append("\n");
        sb.append("A;RUTEmis;;").append(getRutEmis()).append("\n");
        sb.append("A;TipoRucEmis;;").append(tipoRucEMIS).append("\n");
        sb.append("A;NomComer;;").append(getNomComer()).append("\n");
        sb.append("A;RznSocEmis;;").append(getRazoSocEmi()).append("\n");
        sb.append("A;CodigoLocalAnexo;;").append(codigoLocalAnexo).append("\n");
        sb.append("A;TipoRutReceptor;;").append(tipoRutReceptor).append("\n");
        sb.append("A;RUTRecep;;").append(rutRecept).append("\n");
        sb.append("A;RznSocRecep;;").append(rznSocRecep).append("\n");
        sb.append("A;DirRecep;;").append(dirRecep).append("\n");
        sb.append("A;MntNeto;;").append(MntNeto).append("\n");
        sb.append("A;MntExe;;0.00").append("\n");
        sb.append("A;MntExo;;0.00").append("\n");
        sb.append("A;MntTotGrat;;").append(MntTotGrat).append("\n");
        sb.append("A;MntTotal;;").append(MntTotal).append("\n");
        sb.append("A;TipoOperacion;;").append(tipoOperacion).append("\n");
        sb.append("A;FormaPago;;").append(formaPago).append("\n");
        if (tipoOperacion.equalsIgnoreCase("1001")) {
            sb.append("A3;codiDetraccion;1;").append(cod_detrac).append("\n");
            sb.append("A3;valorDetraccion;1;-").append("\n");
            sb.append("A3;MntDetraccion;1;").append(monto_detrac).append("\n");
            sb.append("A3;PorcentajeDetraccion;1;").append(porcentaje_detrac).append("\n");
            sb.append("A3;codiDetraccion;2;3001 ").append("\n");
            sb.append("A3;valorDetraccion;2;").append(valor_detrac).append("\n");
            sb.append("A3;MntDetraccion;2;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;2;0 ").append("\n");
            sb.append("A3;codiDetraccion;3;3000").append("\n");
            sb.append("A3;ValorDetraccion;3;025").append("\n");
            sb.append("A3;MntDetraccion;3;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;3;0").append("\n");

        }
        int index = 1;
        for(Producto p: items){
            sb.append(p.facInafContado(index));
        }
        //sb.append(TpoDocRef).append("\n");
        if (getTpoDocRef().equalsIgnoreCase("801")) {
            sb.append("D;NroLinRef;1;1\n");
            sb.append("D;TpoDocRef;1;").append(TpoDocRef).append("\n");
            sb.append("D;FolioRef;1;").append(FolioRef).append("\n");
        }
        return sb.toString();
    }
    
    public String FacturaExportacion() {
        StringBuilder sb = new StringBuilder();
        sb.append("A;Serie;;").append(serie).append("\n");
        sb.append("A;Correlativo;;").append(correlativo).append("\n");
        sb.append("A;FchEmis;;").append(fechaEmision).append("\n");
        sb.append("A;RznSocEmis;;").append(razoSocEmi).append("\n");
        sb.append("A;CODI_EMPR;;1").append("\n");
        sb.append("A;RUTEmis;;").append(rutEmis).append("\n");
        sb.append("A;ComuEmis;;150131\n");
        sb.append("A;NomComer;;").append(nomComer).append("\n");
        sb.append("A;TipoRucEmis;;").append(tipoRucEMIS).append("\n");
        sb.append("A;TipoDTE;;").append(tipoDocumento).append("\n");
        sb.append("A;TipoRutReceptor;;").append(tipoRutReceptor).append("\n");
        sb.append("A;RUTRecep;;").append(rutRecept).append("\n");
        sb.append("A;RznSocRecep;;").append(rznSocRecep).append("\n");
        sb.append("A;DirRecep;;").append(dirRecep).append("\n");
        sb.append("A;DirRecepProvincia;;\n");
        sb.append("A;DirRecepDepartamento;;\n");
        sb.append("A;DirRecepDistrito;;\n");
        sb.append("A;DirRecepCodPais;;PE\n");
        sb.append("A;CodigoLocalAnexo;;").append(codigoLocalAnexo).append("\n");
        sb.append("A;HoraEmision;;").append(horaEmision);
        sb.append("A;TipoMoneda;;").append(tipoMoneda).append("\n");
        sb.append("A;MntNeto;;").append(MntNeto).append("\n");
        sb.append("A;MntExe;;0.00").append("\n");
        sb.append("A;MntExo;;0.00").append("\n");
        sb.append("A;MntTotalIgv;;0.00\n");
        sb.append("A;MntTotal;;").append(MntTotal).append("\n");
        sb.append("A;FechVencFact;;").append(fechaVencimiento).append("\n");
        sb.append("A;TipoOperacion;;").append(tipoOperacion).append("\n");
        sb.append("A;FormaPago;;").append(formaPago).append("\n");
        sb.append("A2;CodigoImpuesto;1;9995").append("\n");
        sb.append("A2;MontoImpuesto;1;").append(montoImpuesto).append("\n");
        sb.append("A2;TasaImpuesto;1;18").append("\n");
        sb.append("A2;MontoImpuestoBase;1;").append(MntTotal).append("\n");
        if (tipoOperacion.equalsIgnoreCase("1001")) {
            sb.append("A3;codiDetraccion;1;").append(cod_detrac).append("\n");
            sb.append("A3;valorDetraccion;1;-").append("\n");
            sb.append("A3;MntDetraccion;1;").append(monto_detrac).append("\n");
            sb.append("A3;PorcentajeDetraccion;1;").append(porcentaje_detrac).append("\n");
            sb.append("A3;codiDetraccion;2;3001 ").append("\n");
            sb.append("A3;valorDetraccion;2;").append(valor_detrac).append("\n");
            sb.append("A3;MntDetraccion;2;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;2;0 ").append("\n");
            sb.append("A3;codiDetraccion;3;3000").append("\n");
            sb.append("A3;ValorDetraccion;3;025").append("\n");
            sb.append("A3;MntDetraccion;3;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;3;0").append("\n");

        }
        int index=1;
        for(Producto p: items){
            sb.append(p.facExporItem(index));
        }
        if (getTpoDocRef().equalsIgnoreCase("801")) {
            sb.append("D;NroLinRef;1;1\n");
            sb.append("D;TpoDocRef;1;").append(TpoDocRef).append("\n");
            sb.append("D;FolioRef;1;").append(FolioRef).append("\n");
        }
        return sb.toString();
    }

    public String FacturaDescuentoItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("A;CODI_EMPR;;1").append("\n");
        sb.append("A;TipoDTE;;").append(tipoDocumento).append("\n");
        sb.append("A;Serie;;").append(serie).append("\n");
        sb.append("A;Correlativo;;").append(correlativo).append("\n");
        sb.append("A;FchEmis;;").append(fechaEmision).append("\n");
        sb.append("A;HoraEmision;;").append(horaEmision);
        sb.append("A;TipoMoneda;;").append(tipoMoneda).append("\n");
        sb.append("A;RUTEmis;;").append(rutEmis).append("\n");
        sb.append("A;TipoRucEmis;;").append(tipoRucEMIS).append("\n");
        sb.append("A;NomComer;;").append(nomComer).append("\n");
        sb.append("A;RznSocEmis;;").append(razoSocEmi).append("\n");
        sb.append("A;CodigoLocalAnexo;;").append(codigoLocalAnexo).append("\n");
        sb.append("A;TipoRutReceptor;;").append(tipoRutReceptor).append("\n");
        sb.append("A;RUTRecep;;").append(rutRecept).append("\n");
        sb.append("A;RznSocRecep;;").append(rznSocRecep).append("\n");
        sb.append("A;MntNeto;;").append(MntNeto).append("\n");
        sb.append("A;MntExe;;0.00").append("\n");
        sb.append("A;MntExo;;0.00").append("\n");
        sb.append("A;MntTotal;;").append(MntTotal).append("\n");
        sb.append("A;TipoOperacion;;").append(tipoOperacion).append("\n");
        sb.append("A2;CodigoImpuesto;1;1000").append("\n");
        sb.append("A2;MontoImpuesto;1;").append(montoImpuesto).append("\n");
        sb.append("A2;TasaImpuesto;1;18").append("\n");
        sb.append("A2;MontoImpuestoBase;1;1900.00").append("\n");
        if (tipoOperacion.equalsIgnoreCase("1001")) {
            sb.append("A3;codiDetraccion;1;").append(cod_detrac).append("\n");
            sb.append("A3;valorDetraccion;1;-").append("\n");
            sb.append("A3;MntDetraccion;1;").append(monto_detrac).append("\n");
            sb.append("A3;PorcentajeDetraccion;1;").append(porcentaje_detrac).append("\n");
            sb.append("A3;codiDetraccion;2;3001 ").append("\n");
            sb.append("A3;valorDetraccion;2;").append(valor_detrac).append("\n");
            sb.append("A3;MntDetraccion;2;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;2;0 ").append("\n");
            sb.append("A3;codiDetraccion;3;3000").append("\n");
            sb.append("A3;ValorDetraccion;3;025").append("\n");
            sb.append("A3;MntDetraccion;3;0").append("\n");
            sb.append("A3;PorcentajeDetraccion;3;0").append("\n");

        }
        int index = 1;
        for (Producto p : items) {
            sb.append(p.productoNotaCredito(index));
            index++;
        }
        int indice = 1;
        for (Producto b : items) {
            sb.append(b.facDescItem(indice));
            indice++;
        }
        if (getTpoDocRef().equalsIgnoreCase("801")) {
            sb.append("D;NroLinRef;1;1\n");
            sb.append("D;TpoDocRef;1;").append(TpoDocRef).append("\n");
            sb.append("D;FolioRef;1;").append(FolioRef).append("\n");
        }
        return sb.toString();
    }

    public String NotaDebito() {
        StringBuilder sb = new StringBuilder();
        sb.append("A;CODI_EMPR;;1").append("\n");
        sb.append("A;TipoDTE;;").append(tipoDocumento).append("\n");
        sb.append("A;Serie;;").append(serie).append("\n");
        sb.append("A;Correlativo;;").append(correlativo).append("\n");
        sb.append("A;FchEmis;;").append(fechaEmision).append("\n");
        sb.append("A;HoraEmision;;").append(horaEmision);
        sb.append("A;TipoMoneda;;").append(tipoMoneda).append("\n");
        sb.append("A;RUTEmis;;").append(rutEmis).append("\n");
        sb.append("A;TipoRucEmis;;").append(tipoRucEMIS).append("\n");
        sb.append("A;NomComer;;").append(nomComer).append("\n");
        sb.append("A;RznSocEmis;;").append(razoSocEmi).append("\n");
        sb.append("A;CodigoLocalAnexo;;").append(codigoLocalAnexo).append("\n");
        sb.append("A;TipoRutReceptor;;").append(tipoRutReceptor).append("\n");
        sb.append("A;RUTRecep;;").append(rutRecept).append("\n");
        sb.append("A;RznSocRecep;;").append(rznSocRecep).append("\n");
        sb.append("A;Sustento;;").append(sustento).append("\n");
        sb.append("A;TipoNotaCredito;;").append(tipoNotaCredito).append("\n");
        sb.append("A;MntNeto;;").append(MntNeto).append("\n");
        sb.append("A;MntExe;;0.00").append("\n");
        sb.append("A;MntExo;;0.00").append("\n");
        sb.append("A;MntTotal;;").append(MntTotal).append("\n");
        sb.append("A2;CodigoImpuesto;1;1000").append("\n");
        sb.append("A2;MontoImpuesto;1;").append(montoImpuesto).append("\n");
        sb.append("A2;MontoImpuestoBase;1;200.00").append("\n");
        sb.append("A2;TasaImpuesto;1;18").append("\n");
        int index = 1;
        for (Producto p : items) {
            sb.append(p.stringConNumeroItem(index));
            index++;
        }
        sb.append("D;NroLinRef;1;1\n");
        sb.append("D;TpoDocRef;1;").append(TpoDocRef).append("\n");
        sb.append("D;SerieRef;1;").append(SerieRef).append("\n");
        sb.append("D;FolioRef;1;").append(FolioRef).append("\n");
        return sb.toString();
    }

    public String NotaCredito() {
        StringBuilder sb = new StringBuilder();
        sb.append("A;CODI_EMPR;;1\n");
        sb.append("A;TipoDTE;;").append(tipoDocumento).append("\n");
        sb.append("A;Serie;;").append(serie).append("\n");
        sb.append("A;Correlativo;;").append(correlativo).append("\n");
        sb.append("A;FchEmis;;").append(fechaEmision).append("\n");
        sb.append("A;HoraEmision;;").append(horaEmision);
        sb.append("A;TipoMoneda;;").append(tipoMoneda).append("\n");
        sb.append("A;RUTEmis;;").append(rutEmis).append("\n");
        sb.append("A;TipoRucEmis;;").append(tipoRucEMIS).append("\n");
        sb.append("A;NomComer;;").append(nomComer).append("\n");
        sb.append("A;RznSocEmis;;").append(razoSocEmi).append("\n");
        sb.append("A;CodigoLocalAnexo;;").append(codigoLocalAnexo).append("\n");
        sb.append("A;TipoRutReceptor;;").append(tipoRutReceptor).append("\n");
        sb.append("A;RUTRecep;;").append(rutRecept).append("\n");
        sb.append("A;RznSocRecep;;").append(rznSocRecep).append("\n");
        sb.append("A;Sustento;;").append(sustento).append("\n");
        sb.append("A;TipoNotaCredito;;").append(tipoNotaCredito).append("\n");
        sb.append("A;MntNeto;;").append(MntNeto).append("\n");
        sb.append("A;MntExe;;0.00").append("\n");
        sb.append("A;MntExo;;0.00").append("\n");
        sb.append("A;MntTotal;;").append(MntTotal).append("\n");
        sb.append("A;FormaPago;;").append(formaPago).append("\n");
        String[] partesCuotas = getMontoNetoPendPago().split("\\|");
        int suma = 0;
        for (String parte : partesCuotas) {
            suma += Integer.parseInt(parte);
        }
        sb.append("A;MontoNetoPendPago;").append(suma).append("\n");
        sb.append("A2;CodigoImpuesto;1;1000").append("\n");
        sb.append("A2;MontoImpuesto;1;").append(montoImpuesto).append("\n");
        sb.append("A2;MontoImpuestoBase;1;200.00").append("\n");
        sb.append("A2;TasaImpuesto;1;18").append("\n");
        //VARIABLE QUE RECORREN LA CELDA SEPARANDOLAS POR EL |
        String[] montos = getMontoNetoPendPago().split("\\|");
        String[] fechas = getFechaCuota().split("\\|");
        //BUCLE FOR PARA RECORRER CADA CARACTER DE LA CELDA MONTO CUOTA Y FECHA CUOTA

        for (int i = 0; i < montos.length; i++) {
            int indice = i + 1;
            sb.append("A5;Cuota").append(indice).append(";").append(indice).append("\n");
            sb.append("A5;MontoCuota;").append(indice).append(";").append((int) Double.parseDouble(montos[i])).append("\n");
            sb.append("A5;FechaVenCuota;").append(indice).append(";").append(fechas[i]).append("\n");
        }
        int index = 1;
        for (Producto p : items) {
            sb.append(p.productoNotaCredito(index));
            index++;
        }
        sb.append("D;NroLinRef;1;1\n");
        sb.append("D;TpoDocRef;1;").append(TpoDocRef).append("\n");
        sb.append("D;SerieRef;1;").append(SerieRef).append("\n");
        sb.append("D;FolioRef;1;").append(FolioRef).append("\n");
        return sb.toString();
    }
}
