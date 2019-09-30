/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.atsistemas_test;

import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EQUIPO
 */
public class Interface extends javax.swing.JFrame {

public static final Color GREY = new Color(120,120,120);

public String[] pieces = new String [10];
public String product_name = "";
public String product = "";
public String products_bill = "";
public String bill = "";

public boolean imported_flag = false;
public boolean bill_flag = false;

public int quantity = 0;

public double price = 0.0;
public double total_taxes = 0;
public double total_price = 0;
public double tax_aux = 0;
public double original_price = 0;

private Connection connect = null;
private Statement statement = null;
private ResultSet resultSet = null;
    /**
     * Creates new form Interface
     */
    public Interface() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Add Product");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddButtonClicked(evt);
            }
        });

        jButton2.setText("Finish");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FinishButtonClicked(evt);
            }
        });

        jTextField1.setForeground(new java.awt.Color(120, 120, 120));
        jTextField1.setText("Insert Product Here");
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                EnterTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                EnterTextFieldFocusLost(evt);
            }
        });

        jButton3.setText("Exit");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitButtonClicked(evt);
            }
        });

        jButton4.setText("Clean");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CleanButtonClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EnterTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EnterTextFieldFocusGained
        //Removes foreground hint text
        if (jTextField1.getText().equals("Insert Product Here")){
            jTextField1.setText("");
            jTextField1.setForeground(Color.black);
        }
    }//GEN-LAST:event_EnterTextFieldFocusGained

    private void AddButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddButtonClicked
        if ((! jTextField1.getText().equals("Insert Product Here")) && 
            (!bill_flag)){
            pieces = null;
            pieces = jTextField1.getText().split(" ");
             if (! quantityIsNumeric(pieces[0])){
                jTextArea1.setText("Invalid input: Not starting with number");
             }
             else if (! priceIsNumeric(pieces[pieces.length -1]))
             {
                jTextArea1.setText("Invalid input: Not ending with number");
             }
             else{
                price = price * quantity;
                original_price = price;
                checkPieces();
                setPrices();
                products_bill += quantity + product_name + " " + price + "\n";
                bill = products_bill + "Sales Taxes: " + roundDouble(total_taxes) + "\n" + "Total: " + total_price;
                resetValues();
             }
        }
    }//GEN-LAST:event_AddButtonClicked
    
    private double roundDouble(double d){
        return (double) Math.round(d*100)/100;
    }
    
    private void checkPieces(){
        for (int i = 1; i < pieces.length -1; i++){
            if (pieces[i] == null){break;}
            else 
            {
                System.out.printf(pieces[i]+ "\n");
                if (pieces[i].equals("imported"))
                {
                    if (imported_flag == false){
                        imported_flag = true;
                    }
                }
                else
                {
                    product_name += " " + pieces[i];
                }
            }
        }
    }
    
    private void resetValues(){
        jTextField1.setText("");
        price = 0;
        product_name = "";
        jTextField1.setText("Insert Product Here");
        jTextField1.setForeground(GREY);
        jTextArea1.setText(bill);
    }
    
    private void setPrices(){
        try {
            if (!searchProduct(product_name)){
                price = roundDouble(price * 1.1);
            }
        } catch (Exception ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (imported_flag) {
            imported_flag = false;
            tax_aux = roundDouble(price*0.05);
            System.out.printf(tax_aux + "\n");
            while ((100*tax_aux % 5) != 0.0)
            {
                System.out.printf(tax_aux % 0.05 + "\n");
                tax_aux = roundDouble(tax_aux + 0.01);
                System.out.printf(tax_aux + "\n");
            }
            price += tax_aux;
            System.out.printf(price + "\n");
            product_name = " imported" + product_name;
            System.out.printf("Importado");
        }
        total_taxes += roundDouble(price-original_price);
        total_price += roundDouble(price);
    }
    
    private void EnterTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_EnterTextFieldFocusLost
        if (jTextField1.getText().equals("")){
            jTextField1.setText("Insert Product Here");
            jTextField1.setForeground(GREY);
        }
    }//GEN-LAST:event_EnterTextFieldFocusLost

    private void ExitButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitButtonClicked
        System.exit(0);
    }//GEN-LAST:event_ExitButtonClicked

    private void CleanButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CleanButtonClicked
        bill = "";
        bill_flag = false;
        products_bill = "";
        total_taxes = 0;
        total_price = 0;
        resetValues();
    }//GEN-LAST:event_CleanButtonClicked

    private void FinishButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FinishButtonClicked
        if (!bill.equals("")){
            jTextArea1.setText(bill); 
            bill_flag = true;            
        }
    }//GEN-LAST:event_FinishButtonClicked

    private boolean searchProduct(String product) throws Exception{
        try {

            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            connect = DriverManager
                    .getConnection("jdbc:derby://localhost:1527/Products");
            PreparedStatement statement = connect
                    .prepareStatement("SELECT * from PRODUCTLIST");

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("name").equals(product)){
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.printf("Doesn't connect\n" + e + "\n");
            return false;
        } finally {
            close();
        }
    }
    
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    
    private boolean priceIsNumeric (String input){
        try{
            price = Double.parseDouble(input);
            System.out.printf("Price: " + price + "\n");
        }
        catch (NumberFormatException | NullPointerException nfe){
            return false;
        }
        return true;
    }
    
    private boolean quantityIsNumeric (String input){
        try{
            quantity = Integer.parseInt(input);
        }
        catch (NumberFormatException | NullPointerException nfe){
            return false;
        }
        return true;
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
