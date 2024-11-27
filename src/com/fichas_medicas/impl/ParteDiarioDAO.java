/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;


import com.fichas_medicas.domain.ParteDiario;
import java.util.List;

/**
 *
 * @author Jose Luis Tapia fecha:19/11/2024 hora: 13:46pm
 */
public interface ParteDiarioDAO {

    boolean save(ParteDiario obj);

    boolean update(ParteDiario obj);

    boolean delete(Integer idParte);

    ParteDiario getOne(Integer idParte);

    Integer getId(String name);

    List<ParteDiario> getAll();
}
