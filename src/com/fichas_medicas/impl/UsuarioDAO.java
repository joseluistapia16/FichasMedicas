/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fichas_medicas.impl;

import com.fichas_medicas.domain.Usuario;
import java.util.List;

/**
 *
 * @author user
 */
public interface UsuarioDAO {
    boolean save(Usuario obj);
    boolean update (Usuario obj);
    boolean delete (String usuario);
    Usuario getOne(String usuario);
    Integer getId(String usuario);
    List<Usuario> getAll();
    Usuario getLoging(String usuario, String password);
}
