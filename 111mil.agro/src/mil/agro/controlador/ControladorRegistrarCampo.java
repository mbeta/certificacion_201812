/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mil.agro.dao.entidades.Lote;
import mil.agro.dao.entidades.TipoSuelo;
import mil.agro.dao.excepciones.CampoException;
import mil.agro.dao.excepciones.TipoSueloException;
import mil.agro.dao.services.CampoService;
import mil.agro.dao.services.LoteService;
import mil.agro.vista.FrmRegistrarCampo;
import mil.agro.vista.Msg;

/**
 *
 * @author Betania
 */
public class ControladorRegistrarCampo implements ActionListener{
FrmRegistrarCampo frm;
    ModeloTablaLotes mdl;

    public ControladorRegistrarCampo(FrmRegistrarCampo frm) {
        this.frm = frm;
        iniciar();
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        
        switch (comando) {
            case "Agregar Lote":
                agregarLote();
                break;
            case "Editar Lote":
                editarLote();
                break;
            case "Quitar Lote":
                quitarLote();
                break;
            case "Cancelar":
                cancelar();
                break;
            case "Registrar Campo":
                registrarCampo();
                break;
                    
        }
    }

    private void iniciar() {
       mdl = new ModeloTablaLotes();
       frm.tablaLotes.setModel(mdl);
       
       frm.btnCancelar.addActionListener(this);
       frm.btnEditar.addActionListener(this);
       frm.btnGuardar.addActionListener(this);
       frm.btnQuitar.addActionListener(this);
       frm.btnAgregarLote.addActionListener(this);
       
       
       frm.btnQuitar.setEnabled(false);
       frm.btnEditar.setSelected(false);
       
       frm.lblAlertaNombreCampo.setVisible(false);
       frm.lblAlertaNroLote.setVisible(false);
       
    try {
        CargarCombos.cargarComboTipoSuelo(frm.cmbTipo);
    } catch (TipoSueloException ex) {
        Msg.error(frm, "Problemas para cargar Tipos de Suelo");
        Logger.getLogger(ControladorRegistrarCampo.class.getName()).log(Level.SEVERE, null, ex);
    }
       
    frm.tablaLotes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {               
                if (frm.tablaLotes.getSelectedRow() != -1) {
                    frm.btnQuitar.setEnabled(true);
                    frm.btnEditar.setSelected(true);
                } else {
                    frm.btnQuitar.setEnabled(false);
                    frm.btnEditar.setSelected(false);
                }
            }
            
        });
       
    //Control de nombre de campo
    frm.txtNombreCampo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(!frm.txtNombreCampo.getText().isEmpty()){
                    try {
                        frm.lblAlertaNombreCampo.setVisible(CampoService.existeNombre(frm.txtNombreCampo.getText()));
                    } catch (CampoException ex) {
                        Logger.getLogger(ControladorRegistrarCampo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
       
    
    //Control de nro de lote
    frm.txtNombreCampo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(!frm.txtNroLote.getText().isEmpty()){
                    frm.lblAlertaNroLote.setVisible(mdl.existeNroLote(Integer.valueOf(frm.txtNroLote.getText())));
                }
            }
        });
    
        FormatosTxt.txtNumeros_Punto(frm.txtSupCampo);
        FormatosTxt.txtNumeros_Punto(frm.txtSupLote);
        FormatosTxt.txtEnteros(frm.txtNroLote);
    
    }

    private void agregarLote() {
        if(validarLote()){
            Lote nuevo = new Lote();
            nuevo.setNroLote(Integer.valueOf(frm.txtNroLote.getText()));
            nuevo.setSuperficie(new BigDecimal(frm.txtSupLote.getText()));
            nuevo.setTipoSuelo((TipoSuelo) frm.cmbTipo.getSelectedItem());
            
            mdl.agregarLote(nuevo);
            
            limpiarLote();
        }
        
        
     
       
    }


    
    
    private void editarLote() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void quitarLote() {
        if((frm.tablaLotes.getSelectedRow()!=-1)&&(Msg.confirm(frm, "Desea quitar Lote?")==JOptionPane.OK_OPTION)){
          mdl.borraClase(frm.tablaLotes.getSelectedRow());
        }
        
    }

    private void cancelar() {
        if(Msg.confirm(frm, "¿Desea Cancelar el Registro?")==JOptionPane.OK_OPTION){
            frm.dispose();
        }
    try {
        this.finalize();
    } catch (Throwable ex) {
        Logger.getLogger(ControladorRegistrarCampo.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    private void registrarCampo() {
        if((Msg.confirm(frm, "¿Confirma el registro del Campo?")==JOptionPane.OK_OPTION)&&(validarDatosCampo())){
            
        }
    }

    private void limpiarLote() {
        frm.txtNroLote.setText("");
        frm.lblAlertaNroLote.setVisible(false);
        frm.txtSupLote.setText("");
        
    }

    private boolean validarDatosCampo() {
    try {
        if(frm.txtNombreCampo.getText().isEmpty()){
           Msg.alerta(frm, "Debe ingresar un NOMBRE para el Campo.");
           return false;
       }
        
        //Controlar siempre antes de registrar
        if(CampoService.existeNombre(frm.txtNombreCampo.getText())){
            Msg.alerta(frm, "el NOMBRE del Campo ya esta en uso.");
            return false;
        }
    } catch (CampoException ex) {
        Logger.getLogger(ControladorRegistrarCampo.class.getName()).log(Level.SEVERE, null, ex);
    }
        
    if(frm.txtSupCampo.getText().isEmpty()){
        Msg.alerta(frm, "Debe ingresar una SUPERFICIE para el Campo");
           return false;
    }
    
    if(mdl.getRowCount()==0){
         Msg.alerta(frm, "Debe Registrar al menos UN LOTE");
           return false;
    }
    
    //Control de Superficies
    
    
    
        return true;
    }

        private boolean validarLote() {
        if(frm.lblAlertaNroLote.isVisible()){
           Msg.alerta(frm, "el NRO de Lote ya esta en uso.");
           return false;
       }
        
        
        if(frm.txtSupLote.getText().isEmpty()){
            Msg.alerta(frm, "Debe ingresar una SUPERFICIE para el Lote");
           return false;
        }
        if(frm.cmbTipo.getSelectedIndex()==-1){
            Msg.alerta(frm, "Debe seleccionar un TIPO DE SUELO para el Lote");
           return false;
        }
        
        return true;
    }
    
    
}
