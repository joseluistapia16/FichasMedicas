/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fichas_medicas.test;

import com.fichas_medicas.components.FechaComponente;
import com.fichas_medicas.dao.CrudCorreo;
import com.fichas_medicas.domain.Correo;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author user
 */
public class Prueba {

    public static void main(String[] args) {
        prueba3("Validado");
       // System.out.println(FechaComponente.FechaSql());
    }

    private static void prueba1() {
        CrudCorreo crud = new CrudCorreo();
        var id_persona = "3243242423";
        List<Correo> lista = crud.getPersonMail(id_persona);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getCorreo());
        }
    }

    private static void prueba2() {
        java.util.Date fechaActual = new java.util.Date();
        SimpleDateFormat formatoSQL = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSQL = formatoSQL.format(fechaActual);
        Date fecha = Date.valueOf(fechaSQL);
    }
    
    
    private static void prueba3(String msg){
        JDialog dialog = new JDialog((Frame) null, "Aviso", false);
        
        // Configuramos el contenido del diálogo (similar a un mensaje de JOptionPane)
        JLabel messageLabel = new JLabel(msg, SwingConstants.CENTER);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        messageLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dialog.getContentPane().add(messageLabel);
        
        // Ajustamos el tamaño y centramos el diálogo en pantalla
        dialog.setSize(350, 150);
        dialog.setLocationRelativeTo(null);
        
        // Mostramos el diálogo
        dialog.setVisible(true);
        
        // Creamos un Timer que se ejecuta después de 4000 milisegundos (4 segundos)
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose(); // Cierra el diálogo
            }
        });
        timer.setRepeats(false); // Se ejecuta una sola vez
        timer.start();
    }

}
