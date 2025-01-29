/**//*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.sql.Date;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/*Jose Luis Romero Rodriguez 12/11/2024 12:15PM...
 */
@Data
@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Persona {

    private String cedula;
    @NonNull
    private String nombre;
    @NonNull
    private String apellidos;
    @NonNull
    private Date fecha_nacimiento;
    @NonNull
    private String lugar_nacimiento;  //cambio
    @NonNull
    private Integer n_hijos;
    @NonNull
    private String direccion;
    @NonNull
    private String telefono;
    @NonNull
    private Integer id_grupo_sanguineo;
    @NonNull
    private Integer id_estado_civil;
    @NonNull
    private Integer id_area;
    @NonNull
    private String id_usuario;
    @NonNull
    private Date fecha_registro;  // cambio
    @NonNull
    private String estado;

}
