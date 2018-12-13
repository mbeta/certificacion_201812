/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.controlador;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import mil.agro.dao.entidades.Lote;

/**
 *
 * @author Betania
 */
public class ModeloTablaLotes implements TableModel{

        /**
     * Modelo de Lote. Cada fila es un  lote y las columnas son los datos del lote.
     * Implementa TableModel y dos métodos para añadir y eliminar clases del modelo.*/
    /** Lista con los datos. Cada elemento de la lista es una instancia de Lote. */
    private LinkedList datos = new LinkedList();

    /*aca se definen los titulos de cada columna, utilizamos este arreglo en el
    metodo getColumnName*/
    private String[] titulos = new String[]{"Nro","Superficie","Tipo de Suelo"};

    /*aca definimos los tipos de datos de cada columna, utilizamos este arreglo
    en el metodo getColumnClass*/
    private Class[] types = new Class[]{
        java.lang.Integer.class,
        java.lang.String.class,
        java.lang.String.class
                             };

    @Override
    public int getColumnCount() {
        /* Devuelve el número de columnas del modelo, que coincide con el
        número de datos que tenemos del equipo*/
        return titulos.length;
    }

    @Override
    public int getRowCount() {
        /* Devuelve el número de Lotes en el modelo, es decir, el número
        de filas en la tabla.*/
        return datos.size();
    }

 // Retorna la clase de la columna indicada.
    @Override
    public Class getColumnClass(int columnIndex) {
        // Devuelve la clase que hay en cada columna.
        return types[columnIndex];

    }

//Retorna el nombre de la columna indicada
    @Override
    public String getColumnName(int columnIndex) {
        // Devuelve el nombre de cada columna. Este texto aparecerá en la
        // cabecera de la tabla.

        return titulos[columnIndex];

    }

    /** Devuelve el valor de la celda correspondiente al numero de fila y columna
    indicadas.*/
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Declara un objeto del tipo Lote.Clase
        Lote aux;

        // Se obtiene la clase de la fila indicada
        aux = (Lote) (datos.get(rowIndex));

        // Se obtiene el campo apropiado según el valor de columnIndex
        switch (columnIndex) {
            case 0:
                return aux.getNroLote();
            case 1:
                return aux.getSuperficie().setScale(2).toString()+" ha.";
            case 2:
                return aux.getTipoSuelo();
           default:
                return null;
        }
    }

    public void limpiar() {

        while (datos.size() > 0) {
            datos.remove();
        }
        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent(this, 0, 0,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        // ... y pasándoselo a los suscriptores
        avisaSuscriptores(evento);


    }

    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
  
        // Avisa a los suscriptores del cambio, creando un TableModelEvent ...
//        TableModelEvent evento = new TableModelEvent(this, rowIndex, rowIndex,
//                columnIndex);
//
//        // ... y pasándoselo a los suscriptores.
//        avisaSuscriptores(evento);
    }

    public Lote getLote (int row) {


        return (Lote) datos.get(row);


    }

    /** Borra del modelo la clase en la fila indicada.*/
    public void borraClase(int fila) {
        // Se borra la fila
        datos.remove(fila);

        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent(this, fila, fila,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        // ... y pasándoselo a los suscriptores
        avisaSuscriptores(evento);
    }

    /** Añade una clase al final de la tabla.*/
    public void agregarLote(Lote fila) {
        // Añade la clase al modelo
        datos.add(fila);

        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1,
                this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores(evento);
    }

    public void agregarListaLote(List<Lote> lista) {

        datos.addAll(lista);

        TableModelEvent evento;
        evento = new TableModelEvent(this, this.getRowCount() - 1,
                this.getRowCount() - 1, TableModelEvent.ALL_COLUMNS,
                TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        avisaSuscriptores(evento);
    }




    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permite que la celda sea editable.
        return false;
    }
    /** Lista de suscriptores. El JTable será un suscriptor de este modelo de
     * datos */
    private final LinkedList listeners = new LinkedList();

    /** Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param l the TableModelListener
     */
    @Override
    public void addTableModelListener(TableModelListener l) {
        // Añade el suscriptor a la lista de suscriptores
        listeners.add(l);
    }

    /** Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    @Override
    public void removeTableModelListener(TableModelListener l) {
        // Elimina los suscriptores.
        listeners.remove(l);
    }

    /**
     * Pasa a los suscriptores el evento.
     */
    private void avisaSuscriptores(TableModelEvent evento) {
        int i;

        // Bucle para todos los suscriptores en la lista, se llama al metodo
        // tableChanged() de los mismos, pasándole el evento.
        for (i = 0; i < listeners.size(); i++) {
            ((TableModelListener) listeners.get(i)).tableChanged(evento);
        }
    }
    
    
    public Boolean existeNroLote(int nro){
        for (Iterator it = datos.iterator(); it.hasNext();) {
            Lote dato = (Lote) it.next();
            if(dato.getNroLote()==nro){
                return true;
            }
        }  
        return false;
    }

}
