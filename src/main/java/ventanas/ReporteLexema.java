package ventanas;

import javax.swing.JFrame;


public class ReporteLexema {
    public ReporteLexema(String[] lexemas, String[] tokens) { 
        init( lexemas, tokens);

    }


    public void init(String[] lexemas, String[] tokens){
        JFrame frame = new JFrame("REPORTE LEXEMAS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(new PanelRepLex(lexemas, tokens));
        frame.setSize(400, 400);
        frame.setVisible(true);

    }

}
