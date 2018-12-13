/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.controlador;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Betania
 */
public class FormatosTxt {

    public static void txtCUIL(JTextField txt) {
        txt.setToolTipText("Ingrese solo los números del CUIT/CUIL");
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!Character.isDigit(caracter)) {
//                    frm.getToolkit().beep();               
                    e.consume();
                }
                if (txt.getText().length() >= 11) {
                    e.setKeyChar('\0');
                    e.consume();
                }
            }
        });
    }

    public static void txtID(JTextField txt) {
        txt.setToolTipText("Ingrese solo los números");
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!Character.isDigit(caracter)) {
//                    frm.getToolkit().beep();               
                    e.consume();
                }
                if (txt.getText().length() >= 11) {
                    e.setKeyChar('\0');
                    e.consume();
                }
            }
        });

    }

    public static void txtNumeros_Letras(JTextField txt) {
        txt.setToolTipText("Ingresar solo letras y números");
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!(Character.isDigit(caracter) || Character.isAlphabetic(caracter))) {
                    e.consume();
                }
                if (Character.isLetter(caracter)) {
                    e.setKeyChar(Character.toUpperCase(caracter));
                }
            }
        });
    }

    public static void txtNumeros_Guion(JTextField txt) {

        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();

                if (!(Character.isDigit(caracter) || (caracter == '-') || (caracter == '+'))) {
                    e.consume();
                }
            }
        });
    }

    public static void txtNumeros_Punto(JTextField txt) {
        txt.setToolTipText("Utilizar '.' para decimal");
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!(Character.isDigit(caracter) || (caracter == '.'))) {
                    e.consume();
                }
                if ((caracter == '.')) {
                    int ocurrencia = txt.getText().indexOf(".");
                    if(ocurrencia>=0){
                        e.consume();
                    }
                }
            }
        });
    }

    public static void txtEnteros(JTextField txt) {
        txt.setToolTipText("Ingrese solo los números");
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (!Character.isDigit(caracter)) {
                    e.consume();
                }

            }
        });
    }

    public static void txtMayusculas(JTextField txt) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (Character.isLetter(caracter)) {
                    e.setKeyChar(Character.toUpperCase(caracter));
                }

            }
        });
    }
    public static void txtMayusculas(JTextArea txt) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (Character.isLetter(caracter)) {
                    e.setKeyChar(Character.toUpperCase(caracter));
                }

            }
        });
    }
    
    
     public static void txtMayusculas_limite(JTextField txt, int limite) {
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (Character.isLetter(caracter)) {
                    e.setKeyChar(Character.toUpperCase(caracter));
                }
                 if (txt.getText().length() >= limite) {
                    e.setKeyChar('\0');
                    e.consume();
                }

            }
        });
    }
}
