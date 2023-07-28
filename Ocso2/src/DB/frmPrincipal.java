package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class frmPrincipal extends javax.swing.JFrame {

   public static Connection conexion;
   
   void conectarMySQL(){
     try{
            //para mysql 5 el driver se encuentra en:  com.mysql.jdbc.Driver
            //para mysql 8 el driver se encuentra en:  com.mysql.cj.jdbc.Driver
         DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
         conexion = DriverManager.getConnection("jdbc:mysql://localhost/test1", //servidor
                                                "root",   //usuario
                                                "123456");  //password
//         JOptionPane.showMessageDialog(this, "Conexión exitosa");
     }catch (Exception e){
        e.printStackTrace();
     }
   }
   
   public frmPrincipal() {
        initComponents();
        conectarMySQL();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 350);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        lblPresentacion = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        txtPresentacion = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        lblCodigo.setText("Codigo");
        getContentPane().add(lblCodigo);
        lblCodigo.setBounds(30, 40, 80, 21);

        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(30, 90, 90, 21);

        lblPrecio.setText("Precio");
        getContentPane().add(lblPrecio);
        lblPrecio.setBounds(30, 140, 80, 21);

        lblPresentacion.setText("Presentacion");
        getContentPane().add(lblPresentacion);
        lblPresentacion.setBounds(30, 190, 100, 21);
        getContentPane().add(txtCodigo);
        txtCodigo.setBounds(180, 30, 160, 29);
        getContentPane().add(txtNombre);
        txtNombre.setBounds(180, 80, 160, 29);
        getContentPane().add(txtPrecio);
        txtPrecio.setBounds(180, 130, 160, 29);
        getContentPane().add(txtPresentacion);
        txtPresentacion.setBounds(180, 180, 160, 29);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(10, 250, 91, 29);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar);
        btnModificar.setBounds(110, 250, 102, 29);

        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBorrar);
        btnBorrar.setBounds(220, 250, 80, 29);

        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrar);
        btnMostrar.setBounds(310, 250, 90, 29);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        try {
        
        String sql = "insert into producto values("+ txtCodigo.getText()+",'"+ txtNombre.getText() + "'"
                + ","+ txtPrecio.getText() +", '"+ txtPresentacion.getText() +"')" ;
        
        JOptionPane.showMessageDialog(this, sql);
        Statement s = conexion.createStatement();
        
        s.executeUpdate(sql);
        
       }catch (Exception e){
           
          e.printStackTrace();
       }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed

        try{
  
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from producto");

            while (rs.next())
            {
                JOptionPane.showMessageDialog(null,"Código : " + rs.getInt ("codigo") +
                       "    Nombre: " + rs.getString("nombre") + "  Precio: " + rs.getInt("precio") +
                        "   Presentación: " + rs.getString("presentacion"));
            }
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

            Statement s;
            try{
// Se crea un Statement, para realizar la alta
            s = conexion.createStatement();
// Se realiza la consulta. Los resultados se guardan en el
// ResultSet rs
            s.executeUpdate ("update producto set nombre = '"+ txtNombre.getText()  +"', precio = "+
            txtPrecio.getText()+  ", presentacion = '" + txtPresentacion.getText() +"' where codigo = " + txtCodigo.getText());
            }catch (Exception e){       
                e.printStackTrace();
            }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        
        Statement s;
        
        try{
            
            s = conexion.createStatement();
            s.executeUpdate ("delete from producto where codigo =" +
            txtCodigo.getText());
            JOptionPane.showMessageDialog(null, "Registro eliminado con éxito");
            
            }catch (Exception e){
                
                e.printStackTrace();
            }
        
            txtCodigo.setText("");
            txtNombre.setText("");
            txtPrecio.setText("");
            txtPresentacion.setText("");
    }//GEN-LAST:event_btnBorrarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JLabel lblPresentacion;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtPresentacion;
    // End of variables declaration//GEN-END:variables
}
