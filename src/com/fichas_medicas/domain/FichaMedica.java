/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.domain;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

/**
 * Eduardo Zapata 15/11/24 14:47 * @author user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FichaMedica {

    private int id_fichaMedica;
    @NonNull
    private Date fecha_registro;
    @NonNull
    private String id_persona;
    @NonNull
    private String ant_patologicos_per;
    @NonNull
    private String ant_patologicos_fam;
    @NonNull
    private String habito;
    @NonNull
    private String estado_actual;
    @NonNull
    private String examen_fisico;
    @NonNull
    private String examen_complementario;
    @NonNull
    private String firma;
    @NonNull
    private String foto;
    @NonNull
    private String id_usuario;
    @NonNull
    private String estado;

}
