/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.Usuario;
import com.fichas_medicas.impl.UsuarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 *
 * @author user Laura Pulido 11-11-2024 Hora:17:36pm
 * Actualizacion Jose Luis Tapia  12/11/2024   hora: 13:40pm
 */
public class CrudUsuario implements UsuarioDAO {

    private String base = "desarrollo";
    private Conexion conexion;

    public CrudUsuario() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean save(Usuario obj) {
        var sql = "INSERT INTO usuario(username, password, nombre, apellido, correo, id_rol, id_usuario_registro, estado)"
                + "values(?,?,?,?,?,?,?,?)";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(sql)) {
            st.setString(1, obj.getUsuario());     
            st.setString(2, obj.getPassword());        
            st.setString(3, obj.getNombre());
            st.setString(4, obj.getApellido());
            st.setString(5, obj.getCorreo());
            st.setInt(6, obj.getId_rol());
            st.setInt(7, obj.getId_usuario());
            st.setString(8, obj.getEstado());
            int rowsAffected = st.executeUpdate();   
            return rowsAffected > 0;                  
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override
    public boolean update(Usuario obj) {
        var query = "UPDATE usuario SET password = ?, nombre = ?, apellido =?, correo  =?, id_rol = ? WHERE id_usuario = ?";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getPassword());    
            st.setString(2, obj.getNombre());        
            st.setString(3, obj.getApellido());       
            st.setString(4, obj.getCorreo());
            st.setInt(5, obj.getId_rol());          
            int rowsAffected = st.executeUpdate();  
            return rowsAffected > 0;                   
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(String usuario) {
        var query = "UPDATE usuario SET  estado = ? WHERE username = ?";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setString(2, usuario);
            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Usuario getOne(String usuario) {
        Usuario obj = null;
        var query = "SELECT * FROM usuario WHERE username = ? AND estado = 'A'";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, usuario);                     
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                      
                    obj = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getInt("id_rol"),
                            rs.getInt("id_usuario_regtistro"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public Integer getId(String usuario) {
        var query = "SELECT id_usuario FROM usuario WHERE username = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, usuario);                    
            ResultSet rs = st.executeQuery();         
            if (rs.next()) {                          
                return rs.getInt("id_usuario");           
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Usuario> getAll() {
        List<Usuario> datos = new ArrayList<>();
        var query = "select * from usuario where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                var obj = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("id_rol"),
                        rs.getInt("id_usuario_regtistro"),
                        rs.getString("estado")
                );
                datos.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }

    @Override
    public Usuario getLoging(String usuario, String password) {
        Usuario obj = null;
        var query = "SELECT * FROM usuario WHERE username = ? AND password= ? AND estado = 'A'";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, usuario);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                      
                    obj = new Usuario(
                            rs.getInt("id_usuario"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("correo"),
                            rs.getInt("id_rol"),
                            rs.getInt("id_usuario_regtistro"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;

    }

}
