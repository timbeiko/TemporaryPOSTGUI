/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbeanspost;
import javax.swing.*;

/**
 *
 * @author tim
 */
public class NetbeansPost {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame post = new JFrame("POST Terminal");
        post.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GUI g = new GUI();
        post.add(g);
        post.pack();
        post.setVisible(true);
    }
    
}
