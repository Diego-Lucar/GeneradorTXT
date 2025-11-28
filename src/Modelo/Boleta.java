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
    private int tipoOperacion;
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
    private double MontoNetoPendPago;
    private double MntTotGrat;
    private double MntNeto;
    private double MntTotal;
    private int montoImpuesto;
    
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
    LocalDate fechaAgregada  = fechaActual.plusDays(30);
    String fechaVencimiento = fechaAgregada.format(formatoFecha);
    
    private List<Producto> items;

    public Boleta(String tipoDocumento, String serie, String correlativo,
            int tipoOperacion, String tipoMoneda, int tipoRutReceptor, 
            int rutRecept, String rznSocRecep, String dirRecep, String formaPago,
            double MontoNetoPendPago, double MntTotGrat, double MntNeto, double MntTotal,
            int montoImpuesto, List<Producto> items, int cod_detrac, int porcentaje_detrac,
            double monto_detrac,String valor_detrac, String fechaEmision, String fechaVencimiento) {
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
        this.fechaVencimiento= fechaVencimiento;
    }
        
    public List<Producto> getProducto (){
        return items;
    }
            
    public String boletaMasiva (){
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
        if (tipoOperacion == 1001) {
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
        if(formaPago.equalsIgnoreCase("credito")){
            sb.append("A5;Cuota;1;1").append("\n");
            sb.append("A5;MontoCuota;1;100").append("\n");
            sb.append("A5;FechaVencCuota;1;").append(fechaVencimiento).append("\n");
        } 
        int index = 1;
        for(Producto p: items){
            sb.append(p.stringConNumeroItem(index));
            index++;
        }
        return sb.toString();
    }
}
