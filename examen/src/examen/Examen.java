package examen;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Examen extends JPanel {
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D misgraficos = (Graphics2D) g;
        int basegrafica = 360;
        misgraficos.drawLine(10, 10, 10, 360);
        misgraficos.drawLine(10, basegrafica, 360, basegrafica);
        int[] barras = new int[] {1, 2, 3, 4, 5, 6, 7};
        List misbarras = new ArrayList();
        
        String url = "jdbc:sqlite:C:/Users/aepila/Desktop/dam-examen-bbdd/Euromillones.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM Pregunta5";
            ResultSet rs = stmt.executeQuery(sql);
            
            
            barras[0] = Integer.parseInt(rs.getString("n1"));
            barras[1] = Integer.parseInt(rs.getString("n2"));
            barras[2] = Integer.parseInt(rs.getString("n3"));
            barras[3] = Integer.parseInt(rs.getString("n4"));
            barras[4] = Integer.parseInt(rs.getString("n5"));
            barras[5] = Integer.parseInt(rs.getString("e1"));
            barras[6] = Integer.parseInt(rs.getString("e2"));
            
            /*int contador = 0;
            while (rs.next()) {
                System.out.println(rs.getString(contador));
                barras[contador] = Integer.parseInt(rs.getString(contador));
                contador++;
            }*/
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        for(int i = 0; i < barras.length; i++) {
            int alturabarra = barras[i];
            misgraficos.fillRect(i*30+20, basegrafica-alturabarra, 20, alturabarra);
        }
    }
    
    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:/Users/aepila/Desktop/dam-examen-bbdd/Euromillones.db";
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            
            String sql = "SELECT * FROM Pregunta5";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("La combinación ganadora es:\n" + 
                    rs.getString("n1") + " " + rs.getString("n2") + " " + 
                    rs.getString("n3") + " " + rs.getString("n4") + " " + 
                    rs.getString("n5") + " " + rs.getString("e1") + " " + 
                    rs.getString("e2"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        JFrame marco = new JFrame("grafica");
        Examen mimarco = new Examen();
        marco.add(mimarco);
        marco.setSize(400, 400);
        marco.setVisible(true);
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
