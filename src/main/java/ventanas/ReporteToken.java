package ventanas;

import javax.swing.JFrame;


public class ReporteToken {
    public ReporteToken(String[][] reporteCont) { 
        init(reporteCont);

    }

   
    public void init(String[][] reporteCont){
        JFrame frame2 = new JFrame("REPORTE TOKENS");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.add(new PanelRepTok(reporteCont));
        frame2.setSize(400, 400);
        frame2.setVisible(true);

    }

}
