package ventanas;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Clase encargada de manejar los elementos dentro del JPanel del menu principal. */
public class PanelMenu extends JPanel {
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
        JTextArea textArea = new JTextArea("Escribe algo");
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0; c.gridy = 0; c.gridwidth = 2; c.gridheight = 3; 
        this.add(textArea, c);
        
        //boton abrir
        c.gridx = 2; c.gridy = 0; c.gridwidth = 1; c.gridheight = 1; 
        JButton a = createButton("Abrir", layout, c);
        a.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy abrir");
                new archivos.FileSelector();
			}
            
        });

        this.add(a);

        //boton guardar
        c.gridx = 2; c.gridy = 1; c.gridwidth = 1; c.gridheight = 1; 
        JButton g = createButton("Guardar", layout, c);
        g.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Guardar");
			}
            
        });
        this.add(g);


        //boton evaluar

        c.gridx = 2; c.gridy = 2; c.gridwidth = 1; c.gridheight = 1; 
        JButton e = createButton("Evaluar", layout, c);
        e.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola soy Evaluar");
			}
            
        });
        this.add(e);

         //boton reportes

         c.gridx = 2; c.gridy = 3; c.gridwidth = 1; c.gridheight = 1; 
         JButton r = createButton("Reportes", layout, c);
         r.addActionListener(new ActionListener() {
 
             @Override
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Hola soy Reportes");
             }
             
         });
         this.add(r);


    }


}
