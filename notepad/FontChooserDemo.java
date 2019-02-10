//
// Name: David Escobedo
// Project: 2
// Due: 3/7/18
// Course: CS-245-01-w18
// Description: FontChooserDemo class to test out JFontChooser class
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Font;

public class FontChooserDemo{
   
    private JFrame parent;
    private Font font = new Font("Courier", Font.PLAIN, 12);
    private Color color = new Color(0,0,0);
    
    public FontChooserDemo() {
        
        parent = new JFrame("FontChooserDemo");
        parent.setLayout(new FlowLayout());
        parent.setSize(1000,1000);
        parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        parent.setVisible(true);
        parent.setLocationRelativeTo(null);
        
        JFontChooser jfontc = new JFontChooser(); 
        jfontc.setDefault(font);
        jfontc.setDefault(color);
        if (jfontc.showDialog(parent)) {
            jfontc.getFont();
            //jfontc.getStyle();
            //jfontc.getSize();
            jfontc.getColor();
            
        }
        
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FontChooserDemo();
            }
        });
    }
}
