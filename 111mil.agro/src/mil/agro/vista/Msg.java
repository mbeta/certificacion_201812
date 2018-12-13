/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.vista;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author BETANIA
 */
public class Msg {
   
    
    public static void alerta(String msg){
        JOptionPane.showMessageDialog(null, msg, "Alerta", JOptionPane.INFORMATION_MESSAGE);
    } 
    
    public static void alerta(Component objeto,String msg){
        JOptionPane.showMessageDialog(objeto, msg, "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void ok(Component objeto,String msg){
        JOptionPane.showMessageDialog(objeto, msg, "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }
    
     public static String input(Component objeto, String msg, String titulo) {
        return JOptionPane.showInputDialog(objeto, msg, titulo,JOptionPane.PLAIN_MESSAGE);
    }
     
     public static int confirm(Component objeto,String msg){     
        return JOptionPane.showConfirmDialog(objeto,msg, "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE);
     }
     
     public static void error(Component objeto, String msg){
         JOptionPane.showMessageDialog(objeto, msg, "Error", JOptionPane.ERROR_MESSAGE);
     }
             
            
}
