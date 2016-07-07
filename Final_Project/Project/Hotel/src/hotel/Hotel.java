/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

import java.awt.Insets;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

/**
 *
 * @author shreee
 */
public class Hotel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here

        try {
            NimbusLookAndFeel nimbus = new NimbusLookAndFeel();
            UIDefaults def = nimbus.getDefaults();
            def.put("Label.contentMargins", new Insets(0, 0, 0, 0));
           def.put("Label.contentMargins", new Insets(0, 0, 0, 0));
            UIManager.setLookAndFeel(nimbus);
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // not worth my time
            }
        }

            /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
}
