/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PKSantiagolopboc;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClassDB {

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * @return the datosConexion
     */
    public String getDatosConexion() {
        return datosConexion;
    }

    /**
     * @return the baseDatos
     */
    public String getBaseDatos() {
        return baseDatos;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }
//variables Atributos:

    private final String datosConexion = "jdbc:mysql://localhost:3306/";
    private final String baseDatos = "Santiagolopboc14_12_2021";// el nombre de base de datos
    private final String usuario = "root";
    private final String password = "";
    private final String tableName = "tablaLibro";
    private Connection con;
    private ArrayList<Libro> AL = new ArrayList<Libro>();


//constructor
    public ClassDB() {
        ConnectToDataBase();
        CreateDB();
        //deleteBookTable();
        CreateBookTable();
    }

//metodos:
    public void ConnectToDataBase() {
        try {
            setCon(DriverManager.getConnection(getDatosConexion(), getUsuario(), getPassword()));
            System.out.println("Connect to data base: OK");
        } catch (SQLException ex) {
            System.out.println("Connect to data base: NOT OK");
        }

    }

    public void CreateDB() {
        String query = "CREATE DATABASE IF NOT EXISTS " + getBaseDatos();
        try {
            Statement stmt = getCon().createStatement();
            stmt.executeUpdate(query);
            System.out.println("DATABASE: OK");
        } catch (Exception e) {
            System.out.println("Create data base: NOT OK");

        }

    }
    
    
    /*
     public void deleteBookTable(){
        
        String query = "DROP TABLE " + getBaseDatos() + "." + getTableName();
        
        
        
        
        
        try {
            
            Statement stmt = getCon().createStatement();
            stmt.executeUpdate(query);
            System.out.println("Drop table: OK");
        } catch (SQLException ex) {
            //Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Drop table: NOT OK");
        }
    }*/
    
    
    
    
    public void CreateBookTable(){
        
        String query = "CREATE TABLE IF NOT EXISTS " + baseDatos + "." + tableName + "(codigo INT(15) NOT NULL , nombre VARCHAR(50) NOT NULL, author VARCHAR(50) NOT NULL, genero VARCHAR(50) NOT NULL , anyo INT(4) NOT NULL) ENGINE = InnoDB CHARACTER SET utf8 COLLATE utf8_spanish_ci;";
    
        
        
        try {
            
            Statement stmt = getCon().createStatement();
            stmt.executeUpdate(query);
            System.out.println("Create table: OK");
        } catch (SQLException ex) {
            //Logger.getLogger(ClassDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Create table: NOT OK");
        }
    }
    
    
    public ArrayList cargarDatosLibros2() throws SQLException {
        String query = "SELECT * FROM " + baseDatos+"."+ tableName+ "";
        Statement stmt = null;
        ResultSet rs = null;
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        Libro s;
     
        while (rs.next()) {
            s = new Libro(rs.getInt("codigo"), rs.getString("nombre"),rs.getString("author"), rs.getString("genero"), rs.getInt("anyo"));
            AL.add(s);
        }
        return AL;
    }
    
    
    public void insertarLibro (Libro libro)throws SQLException{
            
        String query = "INSERT INTO " + baseDatos+"."+ tableName+ " (codigo, nombre, author, genero, anyo) VALUES ('" + libro.getCodigoISBN()+ "', '" + libro.getNombre() + "', '" + libro.getAutor()+ "', '" + libro.getGenero() + "', '" + libro.getAñoPublicacion()+ "');";
        
        
        
        
        
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Insertado correctamente");
        } catch (SQLException e) {
            System.out.println("No se ha Insertado ");
        } finally {
            stmt.close();
        }
        

        
        
    }
    
    public void borrarLibro(Libro libro) throws SQLException {
        String query = "delete from " + baseDatos+"."+ tableName+ " where codigo= "+libro.getCodigoISBN()+";";

        Statement stmt = null;
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(query);
            System.out.println("filas  Borradas:" + n);
        } catch (SQLException e) {
            System.out.println("fila No borrada");
        } finally {
            stmt.close();
        }
    }
    
    
    
        public void modificarLibro(Libro libro) throws SQLException {
        String query = "update " + baseDatos+"."+ tableName+ " set nombre='" + libro.getNombre() + "', genero='" + libro.getGenero() + "', anyo=" + libro.getAñoPublicacion()+ " where codigo=" + libro.getCodigoISBN() + ";";
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            int n = stmt.executeUpdate(query);
            System.out.println("filas  Modificadas:" + n);
        } catch (SQLException e) {
            System.out.println("bd No Modificada");
        } finally {
            stmt.close();
        }
    }

    
    
    
}
