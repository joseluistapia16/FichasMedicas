/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.domain;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**Eduardo Zapata 15/11/24  14:47
 **
 * @author user
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class FichaMedica {
    private int id_fichaMedica;
    private Date fecha_registro;
    private String id_persona;
    private String ant_patologicos_per;
    private String ant_patologicos_fam;
    private String habito;
    private String estado_actual;
    private String examen_fisico;
    private String examen_complementario;
    private String firma;
    private String foto;
    private String id_usuario;
    private String estado;
    
}
