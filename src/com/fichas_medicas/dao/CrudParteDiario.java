/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fichas_medicas.domain.ParteDiario;
import com.fichas_medicas.impl.ParteDiarioDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class CrudParteDiario implements ParteDiarioDAO {

    private String base = "Desarrollo";
    private Conexion conexion;

    public CrudParteDiario() {

        this.conexion = new Conexion();
    }

    @Override//uno
    public boolean update(ParteDiario obj) {
        var query = "UPDATE persona SET fecha_registro = ?, hora_entrada = ? , diagnostico = ? ,tratamiento = ?, observacion=?, permisos=?, hora_salida=?, frecuencia_cardiaca=?, saturacion=?, peso=? ,estatura=?, imc=?  "
                + "where usuario=?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setDate(1, obj.getFecha_registro());     // Asigna el nombre del área
            st.setTime(2, obj.getHora_entrada());         // Asigna el ID del usuario         // Asigna el estado ('A' o 'I')
            st.setString(3, obj.getDiagnostico());
            st.setString(4, obj.getTratamiento());
            st.setString(5, obj.getObservacion());
            st.setString(6, obj.getPermisos());
            st.setTime(7, obj.getHora_salida());
            st.setString(8, obj.getFrecuencia_cardiaca());
            st.setString(9, obj.getSaturacion());
            st.setDouble(10, obj.getPeso());
            st.setDouble(11, obj.getEstatura());
            st.setDouble(12, obj.getImc());
            st.setString(13, obj.getUsuario());

            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    @Override//Dos
    public boolean save(ParteDiario obj) {
        var sql = "INSERT INTO persona(fecha_registro, hora_entrada, diagnostico, tratamiento, oobservacion, Persmisos, hora_salida, "
                + "frecuencia_cardiaca, saturacion, peso, estatura, imc, usuario,) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                java.sql.Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(sql)) {
            st.setDate(1, obj.getFecha_registro());     // Asigna el nombre del área
            st.setTime(2, obj.getHora_entrada());         // Asigna el ID del usuario         // Asigna el estado ('A' o 'I')
            st.setString(3, obj.getDiagnostico());
            st.setString(4, obj.getTratamiento());
            st.setString(5, obj.getObservacion());
            st.setString(6, obj.getPermisos());
            st.setTime(7, obj.getHora_salida());
            st.setString(8, obj.getFrecuencia_cardiaca());
            st.setString(9, obj.getSaturacion());
            st.setDouble(10, obj.getPeso());
            st.setDouble(11, obj.getEstatura());
            st.setDouble(12, obj.getImc());
            st.setString(13, obj.getUsuario());

            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    @Override
//    public boolean delete(String usuario) {
//
//    }
    @Override
    public ParteDiario getOne(Integer idParte) {
        ParteDiario obj = null;
        var query = "SELECT * FROM persona WHERE usuario"
                + " = ? AND estado = 'A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setInt(1, idParte);                      // Asigna el idArea al parámetro de la consulta
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {                       // Si se encuentra un resultado
                    obj = new ParteDiario(
                            rs.getInt("id_partediario"),
                            rs.getDate("fecha_registro"),
                            rs.getTime("hora_entrada"),
                            rs.getString("id_persona"),
                            rs.getString("diagnostico"),
                            rs.getString("tratamiento"),
                            rs.getString("observacion"),
                            rs.getString("permisos"),
                            rs.getTime("hora_salida"),
                            rs.getString("usuario"), // Assuming String type for usuario
                            rs.getString("frecuencia_cardiaca"),
                            rs.getString("saturacion"),
                            rs.getDouble("peso"),
                            rs.getDouble("estatura"),
                            rs.getDouble("imc"),
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
    public Integer getId(String id_persona) {
        var query = "SELECT id_parte_diario FROM parte_diario WHERE id_persona = ?";
        try (Connection conn = this.conexion.conectar(base); PreparedStatement st = conn.prepareStatement(query)) {
            st.setString(1, id_persona);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0; // Retorna null si no se encuentra el usuario

    }

    @Override
    public List<ParteDiario> getAll() {
        List<ParteDiario> datos = new ArrayList<>();
        var query = "select * from parte_diario where estado='A'";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                ParteDiario obj = new ParteDiario(
                        rs.getInt("id_partediario"),
                        rs.getDate("fecha_registro"),
                        rs.getTime("hora_entrada"),
                        rs.getString("id_persona"),
                        rs.getString("diagnostico"),
                        rs.getString("tratamiento"),
                        rs.getString("observacion"),
                        rs.getString("permisos"),
                        rs.getTime("hora_salida"),
                        rs.getString("usuario"), // Assuming String type for usuario
                        rs.getString("frecuencia_cardiaca"),
                        rs.getString("saturacion"),
                        rs.getDouble("peso"),
                        rs.getDouble("estatura"),
                        rs.getDouble("imc"),
                        rs.getString("estado")
                );
                datos.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CrudParteDiario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datos;
    }

    @Override
    public boolean delete(Integer idParte) {
        var query = "UPDATE parte_diario SET  estado = ? WHERE id_parte_diario = ?";
        try (
                Connection conect = this.conexion.conectar(base); PreparedStatement st = conect.prepareStatement(query)) {
            st.setString(1, "I");          // Asigna el estado ('A' o 'I')
            st.setInt(2, idParte);
            // Asigna el ID del área para actualizar
            int rowsAffected = st.executeUpdate();     // Ejecuta la actualización
            return rowsAffected > 0;                   // Retorna true si se actualizaron filas
        } catch (SQLException ex) {
            Logger.getLogger(CrudArea.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

}
