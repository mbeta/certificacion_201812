/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mil.agro.dao.excepciones;

/**
 *
 * @author BETANIA
 */
public class TipoSueloException extends Exception{
        // Atributos
    private Exception excepcionOriginal;
    private String errorDeNegocio;
    private String errorTecnico;

    public TipoSueloException() {
    }

    public TipoSueloException(String message) {
        super(message);
    }

    public TipoSueloException(String message, Exception e) {
               this.excepcionOriginal = e;
        this.errorTecnico = e.getMessage();
        this.errorDeNegocio = errorDeNegocio;
    }
    
    
     public Exception getExcepcionOriginal() {
        return excepcionOriginal;
    }

    public void setExcepcionOriginal(Exception excepcionOriginal) {
        this.excepcionOriginal = excepcionOriginal;
    }

    public String getErrorDeNegocio() {
        return errorDeNegocio;
    }

    public void setErrorDeNegocio(String errorDeNegocio) {
        this.errorDeNegocio = errorDeNegocio;
    }

    public String getErrorTecnico() {
        return errorTecnico;
    }

    public void setErrorTecnico(String errorTecnico) {
        this.errorTecnico = errorTecnico;
    }
    
    
}
