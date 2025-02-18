/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.test;

import com.fichas_medicas.dao.CrudCorreo;
import com.fichas_medicas.domain.Correo;
import java.util.List;

/**
 *
 * @author user
 */
public class Prueba {
    
    public static void main(String[] args) {
        prueba1();
    }
    
    private static void prueba1(){
        CrudCorreo crud = new CrudCorreo();
        var id_persona="3243242423";
        List<Correo> lista = crud.getPersonMail(id_persona);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getCorreo());
        }
    }
    
}
