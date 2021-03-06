/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbeanspost;

import com.sun.org.apache.xerces.internal.parsers.XMLParser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Tim Beiko
 */
public class GUI extends JPanel {
  // Store and Catalog
  private Store store;  
  private XMLparser xml;
  
  // Panel attributes 
  private JButton addItem, pay;
  private JTextField customerName, payAmount;
  private JLabel name, 
                 product, UPC, quantity, 
                 invoice, 
                 item, qty, unitPrice, price, 
                 total, 
                 paymentType, amount;
  private JTextArea cart;
  
  // Data for GUI
  private JComboBox<String> upcSelect, paymentSelect;
  private JComboBox<Integer> qtySelect;
  private String orders;
  private int orderTotal;
  private Vector<String>  upcs, paymentTypes;
  private Vector<Integer> quantities;
  
  

  public GUI(Store s) {
    store = s;
    // xml = new XMLParser();
    orders = "";
    orderTotal = 0;
    xml = new XMLparser();
    upcs = new Vector<String> (Arrays.asList("UPC001", "UPC002", "UPC003", "UPC004", "UPC005"));
    paymentTypes = new Vector<String> (Arrays.asList("Cash", "Debit", "Mastercard", "Visa", "Amex"));
    quantities = new Vector<Integer> (Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    
    
    // Buttons
    addItem = new JButton("Add item");
    addItem.addActionListener(new AddItemListener());
    pay = new JButton("Pay");
    pay.addActionListener(new PayListener());
    
    // Text Fields
    customerName = new JTextField("Enter your name");
    cart = new JTextArea(10, 3);
    payAmount = new JTextField("000.00");
    
    // Dropdowns
    upcSelect = new JComboBox<String>(upcs);
    upcSelect.addItemListener(new ItemSelectListener());
    paymentSelect = new JComboBox<>(paymentTypes);
    qtySelect = new JComboBox<Integer>(quantities); // Need to fetch quantities from first item
    
    // Labels
    name = new JLabel("Customer:");
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
    
    setPreferredSize(new Dimension(660, 400));
    
    JFrame post = new JFrame("POST Terminal");
    post.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    post.add(this);
    post.pack();
    post.setVisible(true);
  }

    private class AddItemListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
             // Perhaps this would be implemented in the backend
             // Since we need to fetch the price and all from the record
             int q = (Integer) qtySelect.getSelectedItem();
             int p = q * 10; 
             orders += "Item name" + "\t\t\t" +
                        q + "\t" + 
                       "10.00" + "\t" + p + "\n";
             orderTotal += p;
             total.setText("Total: $" + orderTotal);
             cart.setText(orders);
         }    
    }

    private class PayListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Again, this will likely call a function from the backend
            orders = "";
            cart.setText(orders);
            total.setText("Total: $0");
            payAmount.setText("000.00");
        }
    }
    
    private class ItemSelectListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            System.out.println("test");
            qtySelect.removeAllItems();
            Vector<Integer> q = new Vector<Integer>();
            for (int i=0; i<4; i++)
                qtySelect.addItem(i);
        }
    }
}
