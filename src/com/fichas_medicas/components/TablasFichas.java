package com.fichas_medicas.components;

import com.fichas_medicas.dao.CrudArea;
import com.fichas_medicas.dao.CrudPersona;
import com.fichas_medicas.domain.FichaMedica;
import java.awt.Font;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

public class TablasFichas {

    DefaultTableModel modelo;
    CrudPersona crudP = new CrudPersona();
    CrudArea crudA = new CrudArea();

    public DefaultTableModel vaciarTabla(JTable tabla) {
        DefaultTableModel modelo1 = (DefaultTableModel) tabla.getModel();
        while (modelo1.getRowCount() > 0) {
            modelo1.removeRow(0);
        }
        return modelo1;
    }

    public void cargarFichas(List<FichaMedica> lista1, JTable tabla) {
        // Ancho de las columnas
        int a[] = {10, 80, 80, 80, 80, 120};
        String columna[] = {"#", "Fecha", "Cedula", "Nombres", "Apellidos", "Area"};

        // Renderizador para centrar el texto de las celdas
        var tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);  // Centrar contenido de las celdas

        // Modelo de tabla
        modelo = new DefaultTableModel(columna, 0);

        // Rellenar la tabla con los datos de la lista
        String filas[] = new String[6];
        modelo = new DefaultTableModel(null, columna);

        for (int i = 0; i < lista1.size(); i++) {
            filas[0] = "" + (i + 1);
            filas[1] = lista1.get(i).getFecha_registro() + "";
            filas[2] = lista1.get(i).getId_persona();
            var obj = crudP.getOne(lista1.get(i).getId_persona());
            filas[3] = obj.getNombre();
            filas[4] = obj.getApellidos();
            var area = crudA.getOne(obj.getId_area());
            filas[5] = area.getNombre_area();
            modelo.addRow(filas);
        }

        tabla.setModel(modelo);

        // Ajustar el ancho de las columnas
        for (int i = 0; i < columna.length; i++) {
            tabla.getColumnModel().getColumn(i).setPreferredWidth(a[i]);
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }

        // Establecer el alto de las filas
        tabla.setRowHeight(30);  // Aquí puedes ajustar el valor según sea necesario

        // Cambiar la fuente del contenido de las celdas
        Font font = new Font("Arial", Font.PLAIN, 13);  // Fuente y tamaño
        tabla.setFont(font);  // Aplica la fuente a todas las celdas

        // Centrar los nombres de las columnas (cabecera)
        JTableHeader header = tabla.getTableHeader();
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);  // Centra los títulos de las columnas

        // Cambiar la fuente de los encabezados
        header.setFont(new Font("Arial", Font.BOLD, 15));  // Fuente y tamaño para los encabezados

        // Mostrar las líneas de la cuadrícula
        tabla.setShowGrid(true);
    }

    public void filter(String valor, JTable tabla) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(modelo);
        tabla.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter("(?i)" + valor));
    }
}
