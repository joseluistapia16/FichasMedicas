/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;


import com.fichas_medicas.domain.FichaMedica;
import java.util.List;

/**
 * Eduardo Zapata y PabloG 15/11/24 15:11
 *
 * @author user
 */
public interface FichaMedicaDAO {

    boolean save(FichaMedica obj);

    boolean update(FichaMedica obj);

    boolean delete(Integer id_FichaMedica);

    FichaMedica getOne(Integer id_persona);

    Integer getId(String name);

    List<FichaMedica> getAll();
}
