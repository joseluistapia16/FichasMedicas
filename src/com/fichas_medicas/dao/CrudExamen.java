/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import com.fichas_medicas.domain.Examen;
import com.fichas_medicas.impl.ExamenDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class CrudExamen implements ExamenDAO {

    private String base = "fichas_medicas_desarrollo";
    private Conexion conexion;

    public CrudExamen() {
        this.conexion = new Conexion();
    }

    @Override
    public boolean save(Examen obj) {
        var query = "INSERT INTO examen (id_persona,fecha_registro,frecuencia_cardiaca,sistolica,diastolica,"
                + "saturacion,peso_kg, estatura_cm,temperatura,imc, estado_actual,habitos, estado) VALUES (?, ?, ?, ?,?, ?,?, ?, ?,?, ?, ?, ?)";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, obj.getIdPersona());     // Asigna el Correo
            st.setDate(2, obj.getFechaRegistro());         // Asigna el ID de la persona
            st.setInt(3, obj.getFrecuenciaCardiaca());
            st.setInt(4, obj.getSistolica());
            st.setInt(5, obj.getDiastolica());
            st.setInt(6, obj.getSaturacion());
            st.setDouble(7, obj.getPesoKg());
            st.setDouble(8, obj.getEstaturaCm());
            st.setDouble(9, obj.getTemperatura());
            st.setDouble(10, obj.getImc());
            st.setString(11, obj.getEstadoActual());
            st.setString(12, obj.getHabitos());
            st.setString(13, obj.getEstado());         // Asigna el estado ('A' o 'I')
            int rowsAffected = st.executeUpdate();     // Ejecuta la inserción
            return rowsAffected > 0;                   // Retorna true si se insertaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean update(Examen obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Integer id_correo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Examen getOne(Integer id_correo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Integer getId(String name) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Examen> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
