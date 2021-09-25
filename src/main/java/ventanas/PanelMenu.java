package ventanas;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import analizador.Automata;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Clase encargada de manejar los elementos dentro del JPanel del menu principal. */
public class PanelMenu extends JPanel {
    JTextArea textArea;
    public PanelMenu(){
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

        //text area
        textArea = new JTextArea("Escribe algo");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.gridheight = 3; 
        //el weighty y weightx es el valor de si estirar o no las filas y columnas, debemos de resetarla
        //para que no afecte a los demas componentes, al igual que el fill, es es el estitar en si el elemento
        c.weighty=1.0; c.weightx=1.0; c.fill = GridBagConstraints.BOTH;
        this.add(textArea, c);
        c.weighty=0.0; c.weightx=0.0;
        

        


        //boton abrir
        c.gridx = 2; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.fill = GridBagConstraints.HORIZONTAL; c.anchor = GridBagConstraints.NORTHEAST;

        JButton a = createButton("Abrir", layout, c);
        c.weighty=0.0;  c.anchor = GridBagConstraints.CENTER;

        a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy abrir");
                new archivos.FileSelector(textArea);
			}
            
        });

        this.add(a);

        //boton guardar
        c.gridx = 2; c.gridy = 1; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST;


        JButton g = createButton("Guardar", layout, c);
        c.weighty=0.0; c.anchor = GridBagConstraints.CENTER;

        g.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Guardar");
			}
            
        });
        this.add(g);


        //boton evaluar

        c.gridx = 2; c.gridy = 2; c.gridwidth = 1; c.gridheight = 1; 
        c.weighty=1.0; c.anchor = GridBagConstraints.NORTHEAST;

        JButton e = createButton("Evaluar", layout, c);
        c.weighty=0.0;

        e.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Evaluar");
                new Automata();
			}
            
        });
        this.add(e);

         //boton reportes

         c.gridx = 2; c.gridy = 3; c.gridwidth = 1; c.gridheight = 1; 
         c.weighty=0.0; c.anchor = GridBagConstraints.NORTHEAST;
         JButton r = createButton("Reportes", layout, c);
         c.weighty=0.0;
         r.addActionListener(new ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Hola soy Reportes");
             }
             
         });
         this.add(r);
         c.fill = GridBagConstraints.NONE;


    }


}
