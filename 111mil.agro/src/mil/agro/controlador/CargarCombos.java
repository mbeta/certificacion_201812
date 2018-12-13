/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.controlador;

import java.util.LinkedList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.text.JTextComponent;
import mil.agro.dao.entidades.TipoSuelo;
import mil.agro.dao.excepciones.TipoSueloException;
import mil.agro.dao.services.TipoSueloService;

/**
 *
 * @author Betania
 */
public class CargarCombos {
     public static void cargarComboTipoSuelo(JComboBox combo) throws TipoSueloException {
       
        List lista;
        lista = TipoSueloService.getTipos();
        //vaciamos el  comboBox
        DefaultComboBoxModel theModel = (DefaultComboBoxModel) combo.getModel();
        theModel.removeAllElements();
        TipoSuelo aux;
        //cargamos el comboBox con los elementos de la lista       
        for (int i = 0; i < lista.size(); i++) {
            aux = ((TipoSuelo) lista.get(i));
            combo.addItem(aux);
        }
        //extraemos el editor para asignar la clase que realiza la funcion de autocompletado
        JTextComponent editor = (JTextComponent) combo.getEditor().getEditorComponent();
//        editor.setDocument(new S09ShowPopup(combo));
        try {
            //Seleccionamos el primer cliente
            combo.setSelectedIndex(-1);
            //escribimos en el comboBox
//            editor.setText(((TipoBien) combo.getSelectedItem()).toString());
        } catch (Exception ex) {
        }
    }
}
