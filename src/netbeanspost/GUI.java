/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbeanspost;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author tim
 */
public class GUI extends JPanel {
  private JButton addItem, pay;
  private JTextField customerName, payAmount;
  private JLabel name, 
                 product, UPC, quantity, 
                 invoice, 
                 item, qty, unitPrice, price, 
                 total, 
                 paymentType, amount;
  private JTextArea cart;
  private JComboBox<String> upcSelect, paymentSelect;
  private JComboBox<Integer> qtySelect;
  private String orders;
  private int orderTotal;
  private Vector<String>  upcs, paymentTypes;
  private Vector<Integer> quantities;

  public GUI() {
      
    // Placeholder values, will need to call methods to get these.
    orders = "giraffe\t\t\t1\t10.00\t10.00\n" +
             "giraffe\t\t\t1\t10.00\t10.00\n" +
             "giraffe\t\t\t1\t10.00\t10.00\n" +
             "giraffe\t\t\t1\t10.00\t10.00\n" +
             "giraffe\t\t\t1\t10.00\t10.00\n" +
             "giraffe\t\t\t1\t10.00\t10.00\n";
    orderTotal = 0;
    upcs = new Vector<String> (Arrays.asList("UPC001", "UPC002", "UPC003", "UPC004", "UPC005"));
    paymentTypes = new Vector<String> (Arrays.asList("Cash", "Debit", "Mastercard", "Visa", "Amex"));
    quantities = new Vector<Integer> (Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    
    
    // Buttons
    addItem = new JButton("Add item");
    pay = new JButton("Pay");
    
    // Text Fields
    customerName = new JTextField("Enter your name");
    cart = new JTextArea(10, 3);
    payAmount = new JTextField("000.00");
    
    // Dropdowns
    JComboBox<String> upcSelect = new JComboBox<String>(upcs);
    JComboBox<String> paymentSelect = new JComboBox<>(paymentTypes);
    JComboBox<Integer> qtySelect = new JComboBox<>(quantities);
    
    // Labels
    name = new JLabel("Customer Name");
    product = new JLabel("Product");
    invoice = new JLabel("Invoice");
    UPC = new JLabel("UPC");
    quantity = new JLabel("Quantity");
    item = new JLabel("ITEM");
    qty = new JLabel("QTY");
    unitPrice = new JLabel("UNIT PRICE");
    price = new JLabel("PRICE");
    total = new JLabel("Total: $" + orderTotal);
    paymentType = new JLabel("Payment type:");
    amount = new JLabel("Amount");

    
    setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 0;
    add(name, c);

    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 0;
    add(customerName, c);

    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 0;
    add(product, c);
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 1;
    add(UPC, c);
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 4;
    c.gridy = 1;
    add(upcSelect, c);
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 2;
    add(quantity, c);
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 4;
    c.gridy = 2;
    add(qtySelect, c);
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 4;
    c.gridy = 3;
    add(addItem, c);
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 4;
    add(invoice, c);    
 
    c.gridwidth = 2;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 5;
    add(item, c);   
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 5;
    add(qty, c);  
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 5;
    add(unitPrice, c); 
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 4;
    c.gridy = 5;
    add(price, c); 
    
    
    c.gridwidth = 5;
    c.gridx = 0;
    c.gridy = 6;
    c.weightx=1;
    c.fill=GridBagConstraints.HORIZONTAL;
    cart.setText(orders);
    cart.setEditable(false);
    add(cart, c); 
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 4;
    c.gridy = 7;
    add(total, c); 
        
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 8;
    add(paymentType, c); 
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 2;
    c.gridy = 8;
    add(amount, c); 
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 8;
    add(payAmount, c); 
    
    c.gridwidth = 1;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 4;
    c.gridy = 8;
    add(pay, c); 
    
    
    
    setPreferredSize(new Dimension(600, 400));
  }
}
