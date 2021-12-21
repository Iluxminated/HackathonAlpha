package PKSantiagolopboc;

import PKsantiagolopboc.*;
import PKSantiagolopboc.ENUMtype;

/**
 *
 * @author santiagolopboc
 */
public class Libro {

    

    /**
     * @return the AñoPublicacion
     */
    public String getAñoPublicacion() {
        return AñoPublicacion;
    }

    /**
     * @param AñoPublicacion the AñoPublicacion to set
     */
    public void setAñoPublicacion(String AñoPublicacion) {
        this.AñoPublicacion = AñoPublicacion;
    }

    /**
     * @return the Genero
     */
    public ENUMtype getGenero() {
        return Genero;
    }

    /**
     * @param Genero the Genero to set
     */
    public void setGenero(ENUMtype Genero) {
        this.Genero = Genero;
    }

    /**
     * @return the AñoPublicacion
     */

    /**
     * @return the codigoISBN
     */
    public int getCodigoISBN() {
        return codigoISBN;
    }

    /**
     * @param codigoISBN the codigoISBN to set
     */
    public void setCodigoISBN(int codigoISBN) {
        this.codigoISBN = codigoISBN;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Autor
     */
    public String getAutor() {
        return Autor;
    }

    /**
     * @param Autor the Autor to set
     */
    public void setAutor(String Autor) {
        this.Autor = Autor;
    }

    /**
     * @return the Genero
     */


    public Libro(){}
    
    public Libro(int codigo, String nombre, String Author, ENUMtype Genero, String año){
        this.codigoISBN=codigo;
        this.Nombre=nombre;
        this.Autor=Author;
        this.Genero=Genero;
        this.AñoPublicacion=año;
    }
    
    
    Libro(int codigo, String nombre, String author, String Genero, int año) {
        this.codigoISBN=codigo;
        this.Nombre=nombre;
        this.Autor=author;
        this.text=Genero;
        this.AñoPublicacion=año+"";
    }
    
    public String getInfo(){
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------"+"\n");
        sb.append("codigo: "+getCodigoISBN()+"\n");
        sb.append("Nombre: "+getNombre()+"\n");
        sb.append("Author: "+getAutor()+"\n");
        sb.append("Genero: "+getGenero()+"\n");
        sb.append("Año: "+getAñoPublicacion()+"\n");
        sb.append("\n"+"--------------------");
        Info = sb+"";
        return Info;
    }
    

    private int codigoISBN;
    private String Nombre;
    private String Autor;
    private ENUMtype Genero;
    private String AñoPublicacion;
    private String Info;
    private String text;
    
}
