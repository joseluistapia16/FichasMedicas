/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.FichaMedica;
import com.fichas_medicas.impl.FichaMedicaDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author user
 */
public class CrudFichaMedica implements FichaMedicaDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;

    public CrudFichaMedica() {
        this.conexion = new Conexion();
    }

    public String save(FichaMedica obj) {
        var msg = "";
        var sql = "INSERT INTO ficha_medica(fecha_registro, id_persona, antecedentes_patologicos_personales, antecedentes_patologicos_familiares,id_usuario, estado)"
                + "values(?,?,?,?,?,?)";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(sql)) {
            st.setDate(1, obj.getFecha_registro());
            st.setString(2, obj.getId_persona());
            st.setString(3, obj.getAnt_patologicos_per());
            st.setString(4, obj.getAnt_patologicos_fam());
            st.setString(5, obj.getId_usuario());
            st.setString(6, obj.getEstado());
            st.executeUpdate();
            msg = "Datos guardados...";
        } catch (SQLException ex) {
            msg = "Erro: " + ex;
            Logger.getLogger(CrudFichaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    public boolean update(FichaMedica obj) {
        boolean msg = false;
        var query = "UPDATE ficha_medica SET antecedentes_patologicos_personales =?, antecedentes_patologicos_familiares  =?"
                + "  WHERE id_ficha_medica = ?";
        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getAnt_patologicos_per());
            st.setString(2, obj.getAnt_patologicos_fam());
            st.setInt(3, obj.getId_fichaMedica());
            st.executeUpdate();
            msg = true;
        } catch (SQLException ex) {
            msg = false;
            Logger.getLogger(CrudFichaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @Override
    public boolean delete(Integer idFichaMedica) {
        var query = "UPDATE ficha_medica SET  estado = ? WHERE id_ficha_medica = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setInt(2, idFichaMedica);
            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudFichaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public FichaMedica getOne(Integer idFichaMedica) {
        FichaMedica obj = null;
        var query = "SELECT * FROM ficha_medica WHERE id_ficha_medica = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, idFichaMedica);                      // Asigna el idArea al parámetro de la consulta
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                       // Si se encuentra un resultado
                    obj = new FichaMedica(
                            rs.getInt("id_ficha_medica"),
                            rs.getDate("fecha_registro"),
                            rs.getString("id_persona"),
                            rs.getString("antecedentes_patologicos_personales"),
                            rs.getString("antecedentes_patologicos_familiares"),
                            rs.getString("id_usuario"),
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
    public Integer getId(Date fecha, String idPersona) {
        var query = "SELECT id_ficha_medica FROM ficha_medica WHERE fecha_registro = ? AND id_persona=? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setDate(1, fecha);                     // Asigna el nombre del área a buscar
            st.setString(2, idPersona);
            ResultSet rs = st.executeQuery();          // Ejecuta la consulta
            if (rs.next()) {                           // Verifica si hay resultados
                return rs.getInt("id_ficha_medica");           // Retorna el id_area encontrado
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<FichaMedica> getAll() {
        List<FichaMedica> datos = new ArrayList<>();
        var query = "select * from ficha_medica where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                var obj = new FichaMedica(
                        rs.getInt("id_ficha_medica"),
                        rs.getDate("fecha_registro"),
                        rs.getString("id_persona"),
                        rs.getString("antecedentes_patologicos_personales"),
                        rs.getString("antecedentes_patologicos_familiares"),
                        rs.getString("id_usuario"),
                        rs.getString("estado")
                );
                datos.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudFichaMedica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    public List<FichaMedica> getAllTabSummary(String id_persona) {
        List<FichaMedica> datos = new ArrayList<>();
        String query = "SELECT * FROM ficha_medica WHERE id_persona = ? AND estado = 'A'";
        try (Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {

            st.setString(1, id_persona);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    FichaMedica obj = new FichaMedica(
                            rs.getInt("id_ficha_medica"),
                            rs.getDate("fecha_registro"),
                            rs.getString("id_persona"),
                            rs.getString("antecedentes_patologicos_personales"),
                            rs.getString("antecedentes_patologicos_familiares"),
                            rs.getString("id_usuario"),
                            rs.getString("estado")
                    );
                    datos.add(obj);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudFichaMedica.class.getName()).log(Level.SEVERE, "Error en la consulta getAllTabSummary", ex);
        }
        return datos;
    }
}
