package ventanas;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import analizador.Automata;
import analizador.Token;
import reportes.Reporte;

/**Clase encargada de manejar los elementos dentro del JPanel del menu principal. */
public class PanelMenu extends JPanel {
    JTextArea textArea;
    JTextArea tLabel;
    Reporte report = new Reporte();
    JFrame frame;

    /**Constructor de la clase PanelMenu. */
    public PanelMenu(JFrame frame){
        this.frame = frame;
        createPanel();

    }


    /*Este metodo sirve para crear los botones del menu principal */
    public JButton createButton(String name, GridBagLayout layout, GridBagConstraints c){
        JButton button = new JButton(name);
        layout.setConstraints(button, c);
        return button;
    }



    /*Metodo encargado de crear el panel dle menu principal*/
    public void createPanel() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout (layout); // Definimos el tipo de layout
        GridBagConstraints c = new GridBagConstraints();


        //JLabel de las lineas del texto
        tLabel = new JTextArea("1");
        tLabel.setEditable(false);
        tLabel.setBackground(Color.LIGHT_GRAY);
        c.gridx = 0; c.gridy = 0; c.gridwidth = 1; c.gridheight = 3; //posicion x,y cuantas casillas ocupa ancho, alto
        c.weighty=1.0; c.weightx=0.0; c.fill = GridBagConstraints.VERTICAL; //el eje y debe estirarse, el eje x no, y solo se estira en vertial
        c.anchor = GridBagConstraints.FIRST_LINE_START; //
        this.add(tLabel, c);
        c.weighty=0.0; c.weightx=0.0;








        //text area
        textArea = new JTextArea("Escribe algo");
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane);

        //Aqui agregamos un evento que encarga de ver las actualizaciones del textarea
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                new NumeroLinea(textArea,tLabel).getNumerosLinea();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                new NumeroLinea(textArea,tLabel).getNumerosLinea();
                
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                new NumeroLinea(textArea,tLabel).getNumerosLinea();
                
            }
        });

        //Aqui va el posicionamiento del textarea
        c.gridx = 1; c.gridy = 0; c.gridwidth = 2; c.gridheight = 3; 
        //el weighty y weightx es el valor de si estirar o no las filas y columnas, debemos de resetarla
        //para que no afecte a los demas componentes, al igual que el fill, es es el estitar en si el elemento
        c.weighty=1.0; c.weightx=1.0; c.fill = GridBagConstraints.BOTH; 
        c.anchor = GridBagConstraints.PAGE_START;
        this.add(textArea, c);
        c.weighty=0.0; c.weightx=0.0; //reset
        

        


        //boton abrir
        c.gridx = 3; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.NORTHEAST;

        JButton a = createButton("Abrir", layout, c);
        c.weighty=0.0;  c.anchor = GridBagConstraints.CENTER; //reset

        a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy abrir");
                new archivos.FileOpen(textArea,tLabel);
			}
            
        });

        this.add(a);






        //boton guardar
        c.gridx = 3; c.gridy = 1; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST;

        JButton g = createButton("Guardar", layout, c);
        c.weighty=0.0; c.anchor = GridBagConstraints.CENTER; //reset

        g.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Guardar");
                new archivos.FileSave(textArea);
			}
            
        });
        this.add(g);






        //boton evaluar

        c.gridx = 3; c.gridy = 2; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST; 

        JButton e = createButton("Evaluar", layout, c);
        c.weighty=0.0; //reset

        e.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Evaluar");
                new Automata(textArea, report);

			}
            
        });
        this.add(e);







         //boton reportes

         c.gridx = 3; c.gridy = 3; c.gridwidth = 1; c.gridheight = 1; 
         c.weighty=0.0; c.anchor = GridBagConstraints.NORTHEAST;
         JButton r = createButton("Reportes", layout, c);
         c.weighty=0.0; //reset
         r.addActionListener(new ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Hola soy Reportes");
                for(Token tmp : Token.values()){
                    if(tmp!=Token.IDENTIFICADOR2){
                        System.out.println(tmp.getNombreEstado() + " " + report.getcontadorEstado(tmp));  

                    }
                }
             }
             
         });
         this.add(r);
         c.fill = GridBagConstraints.NONE;




         //JLabel de la parte inferior
        JLabel labelU = new JLabel("***************************************************************************");
        c.gridx = 1; c.gridy = 4; c.gridwidth = 2; c.gridheight = 1; //posicion x,y cuantas casillas ocupa ancho, alto
        c.weighty=0.0; c.weightx=1.0; c.fill = GridBagConstraints.HORIZONTAL; 
        this.add(labelU, c);
        c.weighty=0.0; c.weightx=0.0;

         


    }




}
