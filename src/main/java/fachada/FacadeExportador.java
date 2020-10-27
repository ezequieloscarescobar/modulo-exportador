package fachada;

import estrategias.exportacion.EstrategiaDeExportacion;
import estrategias.exportacion.FactoryEstrategiaExportacion;
import estrategias.exportacion.FormaDeExportacion;
import exportables.Documento;
import exportador.Exportador;

import java.util.List;
import java.util.Map;

public class FacadeExportador {

    public String exportar(Map<String, List<String>> datos, FormaDeExportacion formaDeExportacion, String nombreDelArchivo){
        Documento documentoExportable = new Documento(datos);
        EstrategiaDeExportacion estrategiaDeExportacion = FactoryEstrategiaExportacion.crearEstrategia(formaDeExportacion, nombreDelArchivo);
        Exportador exportador = new Exportador(estrategiaDeExportacion);
        exportador.setExportable(documentoExportable);
        return exportador.exportar();
    }

}
