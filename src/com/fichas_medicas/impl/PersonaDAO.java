/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;

import com.fichas_medicas.domain.Persona;
import java.util.List;

/*Jose Luis Romero Rodriguez 12/11/2024 12:15PM.
*/

public interface PersonaDAO {

    boolean save(Persona obj);

    boolean update(Persona obj);

    boolean delete(String cedula);

    Persona getOne(String cedula);

    List<Persona> getAll();
}
