package ventanas;

import javax.swing.JFrame;
import reportes.Reporte;

public class ReporteJF {
    
    /** 
     * Clase encargada de crear el JFrame para los reportes
    */
    public ReporteJF(boolean existeError, Reporte reporte){
        init(existeError, reporte);
    }

    /**
     * 
     * @param existeError Indica si existen errores en el texto evaluado (true==si)
     * @param reporte  Enviamos el objeto reporte, el cual contiene la informacion acerca de los tokens
     */
    public void init(boolean existeError, Reporte reporte){
        String title="";
        if(existeError){
            title = "Reporte de Errores";
        }else{
            title = "Reporte de Tokens";
        }
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new PanelReporte(existeError,reporte));
        frame.setSize(400, 400);
        frame.setVisible(true);

    }

}
