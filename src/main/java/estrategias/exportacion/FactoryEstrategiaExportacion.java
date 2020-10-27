package estrategias.exportacion;

import estrategias.exportacion.excel.ExportarAExcel;
import estrategias.exportacion.pdf.AdapterApachePDFBox;
import estrategias.exportacion.pdf.ExportarAPDF;

public class FactoryEstrategiaExportacion {

    public static EstrategiaDeExportacion crearEstrategia(FormaDeExportacion formaDeExportacion, String nombreDelArchivo){
        EstrategiaDeExportacion estrategia = null;
        switch (formaDeExportacion){
            case EXCEL:
                estrategia = new ExportarAExcel(nombreDelArchivo + ".xlsx");
                break;
            case PDF:
                estrategia = new ExportarAPDF(new AdapterApachePDFBox(nombreDelArchivo + ".pdf"));
                break;
        }
        return estrategia;
    }
}
