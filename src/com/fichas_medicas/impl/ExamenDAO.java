/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;

import com.fichas_medicas.domain.Examen;
import java.util.List;

/**
 *
 * @author user
 */
public interface ExamenDAO {

    boolean save(Examen obj);

    boolean update(Examen obj);

    boolean delete(Integer id_correo);

    Examen getOne(Integer id_correo);

    Integer getId(String name);

    List<Examen> getAll();

}
