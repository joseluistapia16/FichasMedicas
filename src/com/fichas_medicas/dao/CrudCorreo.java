/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;




import com.fichas_medicas.domain.Correo;
import com.fichas_medicas.impl.CorreoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Robert López 15/11/24 14:26
 *
 * @author USUARIO 
 */

public class CrudCorreo implements CorreoDAO{
    
    private String base = "desarrollo";
    private Conexion conexion;

    @Override
    public boolean save(Correo obj) {
        var query = "INSERT INTO correo (correo, id_persona,id_usuario, estado) VALUES (?, ?, ?, ?)";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getCorreo());     // Asigna el Correo
            st.setString(2, obj.getId_persona());         // Asigna el ID de la persona
            st.setString(3, obj.getId_usuario());             //Asigna el ID del usuario
            st.setString(4, obj.getEstado());         // Asigna el estado ('A' o 'I')
            int rowsAffected = st.executeUpdate();     // Ejecuta la inserción
            return rowsAffected > 0;                   // Retorna true si se insertaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public boolean update(Correo obj) {
        var query = "UPDATE correo SET correo = ? WHERE id_correo = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getCorreo());     // Asigna el nombre del rol
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
       
    }

    @Override
    public boolean delete(Integer id_correo) {
        var query = "UPDATE correo SET  estado = ? WHERE id_correo = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setInt(2, id_correo);
            // Asigna el ID del correo para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public Correo getOne(Integer id_correo) {
        Correo correo = null;
        var query = "SELECT * FROM correo WHERE id_correo = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base);
                PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, id_correo);                      // Asigna el id_correo Robert
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                       // Si se encuentra un resultado
                    correo = new Correo(
                            rs.getInt("id_correo"),
                            rs.getString("correo"),
                            rs.getString("id_persona"),
                            rs.getString("id_usuario"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correo;
    
    }

    @Override
    public Integer getId(String name) {
        var query = "SELECT id_correo FROM correo WHERE id_persona = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, name);                     // Asigna el nombre del correo a buscar
            ResultSet rs = st.executeQuery();          // Ejecuta la consulta
            if (rs.next()) {                           // Verifica si hay resultados
                return rs.getInt("id_correo");           // Retorna el id_rol encontrado
            }
        } catch (SQLException ex) {
                Logger.getLogger(CrudCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
        
    }

    @Override
    public List<Correo> getAll() {
        List<Correo> datos = new ArrayList<>();
        var query = "select * from Correo where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                Correo correo = new Correo(rs.getInt("id_correo"), rs.getString("correo"),
                        rs.getString("id_persona"),rs.getString("id_usuario"), rs.getString("estado"));
                datos.add(correo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
        
    }
}
