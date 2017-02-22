/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netbeanspost;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
/**
 *
 * @author Darren ZL Zhen
 */
public class XMLparser {

    /******start here, the global variable store the read information from XML****/
    //products fields  PA means Products Array, 
    public String []PA_product_id;
    public String []PA_UPC;
    public String []PA_description;
    public String []PA_price;
    
    //customer fields
    //public String customer_id;
    //public String name;
    
    //transaction fields   T means transaction (variable)
    public String T_txn_id;
    public String T_customer_id;
    public String T_name;
    public String T_UPC;
    public String T_payType;
    public String T_txnDate;
    public String T_txnTime;
    public String T_total;
    
    //items fields    IA means Items array
    public String []IA_item_id;
    public String []IA_quantity;
    public String []IA_txn_id;
    public String []IA_product_id;
    /**********end here************************////
    
    //below are parser variables///////////
    
    //the element to parser the XML file, which is match to table, 
    //elements
    public String products = "products";
    public String product = "product";
    public String transaction = "transaction";
    public String items = "items";
    public String item = "item";
    
    //product child element, the product contain the below element
    public String product_id = "product_id";
    public String UPC = "UPC";
    public String description = "description";
    public String price = "price";
    
    //transaction child elemnt, the transaction contain the below element
    public String txn_id = "txn_id";
    public String customer_id = "customer_id";
    public String name = "name";
    public String t_UPC = "UPC";
    public String payType = "payType";
    public String txnDate = "txnDate";
    public String txnTime = "txnTime";
    public String total = "total";
    
    //item child element, the item contain the below element
    public String item_id = "item_id";
    public String quantity = "quantity";
    public String i_txn_id = "txn_id";
    public String i_product_id = "product_id";
    
    
    
    SAXBuilder saxBuilder;
    Document document;
    Element Etransaction;
    List<Element> Lproducts;
    List<Element> Litems;
    
    public XMLparser(){
        saxBuilder = new SAXBuilder();
    }
    
    //function for parser the XML file
    public void parserXMLFile(String filePath){
        try{
            File inputFile = new File(filePath);
            document = saxBuilder.build(inputFile);
            Element RootElement = document.getRootElement();  
            
            Element products = RootElement.getChild(this.products);
            //Element customers = RootElement.getChild("customer");
            Element txn = RootElement.getChild(transaction);
            Element items = RootElement.getChild(this.items);
            
            Etransaction = txn;
            Lproducts = products.getChildren();
            Litems = items.getChildren();
            
            //intialize global array to store info
            PA_product_id = new String[Lproducts.size()];
            PA_UPC = new String[Lproducts.size()];
            PA_description = new String[Lproducts.size()];
            PA_price = new String[Lproducts.size()];
            
            //intialize global array to store info
            IA_item_id = new String[Litems.size()];
            IA_quantity = new String[Litems.size()];
            IA_txn_id = new String[Litems.size()];
            IA_product_id = new String[Litems.size()];
            
            //storing info
            T_txn_id = txn.getChildText(txn_id);
            T_customer_id = txn.getChildText(customer_id);
            T_name = txn.getChildText(name);
            T_UPC = txn.getChildText(t_UPC);
            T_payType = txn.getChildText(payType);
            T_txnDate = txn.getChildText(txnDate);
            T_txnTime = txn.getChildText(txnTime);
            T_total = txn.getChildText(total);
            
            
            //storing info
            for(int i = 0; i<Lproducts.size(); i++){
                Element Tproduct = Lproducts.get(i);
                PA_product_id[i] = Tproduct.getChild(product_id).getText();
                PA_UPC[i] = Tproduct.getChild(UPC).getText();
                PA_description[i] = Tproduct.getChild(description).getText();
                PA_price[i] = Tproduct.getChild(price).getText();
            }
            
            //storing info
            for(int i = 0; i<Litems.size(); i++){
                Element Titem = Litems.get(i);
                IA_item_id[i] = Titem.getChild(item_id).getText();
                IA_quantity[i] = Titem.getChild(quantity).getText();
                IA_txn_id[i] = Titem.getChild(i_txn_id).getText();
                IA_product_id[i] = Titem.getChild(i_product_id).getText();
            }
            
                    
            
            
        
        }catch(JDOMException e){
            e.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
        
        
    }
    
    public void createXMLFile(String filePath){
        try{
            Element RootElement = new Element("info");
            document = new Document(RootElement);
            
            //add the products element
            Element _1stElement = new Element(products);
            for(int i=0; i<PA_product_id.length; i++){
                Element E1 = new Element(product);
                Element E1E1 = new Element(product_id);
                E1E1.setText(PA_product_id[i]!=null ? PA_product_id[i] : "");
                Element E1E2 = new Element(UPC);
                E1E2.setText(PA_UPC[i]!=null ? PA_UPC[i] : "");
                Element E1E3 = new Element(description);
                E1E3.setText(PA_description[i]!=null ? PA_description[i] : "");
                Element E1E4 = new Element(price);
                E1E4.setText(PA_price[i]!=null ? PA_price[i] : "");
                E1.addContent(E1E1);
                E1.addContent(E1E2);
                E1.addContent(E1E3);
                E1.addContent(E1E4);
                _1stElement.addContent(E1);
            }
            
            //add transaction element
            Element _2ndElement = new Element(transaction);
            Element E2E1 = new Element(txn_id);
            E2E1.setText(T_txn_id!=null ? T_txn_id : "");
            Element E2E2 = new Element(customer_id);
            E2E2.setText(T_customer_id!=null ? T_customer_id : "");
            Element E2E3 = new Element(name);
            E2E3.setText(T_name!=null ? T_name : "");
            Element E2E4 = new Element(t_UPC);
            E2E4.setText(T_UPC!=null ? T_UPC : "");
            Element E2E5 = new Element(payType);
            E2E5.setText(T_payType!=null ? T_payType : "");
            Element E2E6 = new Element(txnDate);
            E2E6.setText(T_txnDate!=null ? T_txnDate : "");
            Element E2E7 = new Element(txnTime);
            E2E7.setText(T_txnTime!=null ? T_txnTime : "");
            Element E2E8 = new Element(total);
            E2E8.setText(T_total!=null ? T_total : "");
            _2ndElement.addContent(E2E1);
            _2ndElement.addContent(E2E2);
            _2ndElement.addContent(E2E3);
            _2ndElement.addContent(E2E4);
            _2ndElement.addContent(E2E5);
            _2ndElement.addContent(E2E6);
            _2ndElement.addContent(E2E7);
            _2ndElement.addContent(E2E8);
            
            //add items element
            Element _3rdElement = new Element(items);
            for(int i=0; i<PA_product_id.length; i++){
                Element E3 = new Element(item);
                Element E3E1 = new Element(item_id);
                E3E1.setText(IA_item_id[i]!=null ? IA_item_id[i] : "");
                Element E3E2 = new Element(quantity);
                E3E2.setText(IA_quantity[i]!=null ? IA_quantity[i] : "");
                Element E3E3 = new Element(i_txn_id);
                E3E3.setText(IA_txn_id[i]!=null ? IA_txn_id[i] : "");
                Element E3E4 = new Element(i_product_id);
                E3E4.setText(IA_product_id[i]!=null ? IA_product_id[i] : "");
                E3.addContent(E3E1);
                E3.addContent(E3E2);
                E3.addContent(E3E3);
                E3.addContent(E3E4);
                _3rdElement.addContent(E3);
            }
            
            document.getRootElement().addContent(_1stElement);
            document.getRootElement().addContent(_2ndElement);
            document.getRootElement().addContent(_3rdElement);
            
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(document, new FileWriter(filePath));
        }catch(IOException ioe){
            ioe.printStackTrace();;
        }
    }
    
    //clear the global variable of products, txn, and items
    public void clearVariable(boolean products, boolean txn, boolean items){
        if(products){
            PA_product_id = new String[0];
            PA_UPC = new String[0];
            PA_description = new String[0];
            PA_price = new String[0];
        }
        
        if(txn){
            T_txn_id = "";
            T_customer_id = "";
            T_name = "";
            T_UPC = "";
            T_payType = "";
            T_txnDate = "";
            T_txnTime = "";
            T_total = "";
        }
        
        if(items){
            IA_item_id = new String[0];
            IA_quantity = new String[0];
            IA_txn_id = new String[0];
            IA_product_id  = new String[0];
        }
    }
    
    public void printTest(){
        System.out.println("-----product info-----");
        for(int i = 0; i<PA_product_id.length; i++){
            System.out.println("product_id : "+ PA_product_id[i]);
            System.out.println("UPC : "+ PA_UPC[i]);
            System.out.println("description : "+ PA_description[i]);
            System.out.println("price : "+ PA_price[i]);
            System.out.println();
        }
        
        System.out.println("-----items info-----");
        for(int i = 0; i<IA_item_id.length; i++){
            System.out.println("item_id : "+ IA_item_id[i]);
            System.out.println("quantity : "+ IA_quantity[i]);
            System.out.println("txn_id : "+ IA_txn_id[i]);
            System.out.println("product_id : "+ IA_product_id[i]);
            System.out.println();
        }
        
        System.out.println("-----transaction info-----");
        System.out.println("txn__id : " + T_txn_id);
        System.out.println("customer_id : " + T_customer_id);
        System.out.println("name : " + T_name);
        System.out.println("UPC : " + T_UPC);
        System.out.println("payTyep : " + T_payType);
        System.out.println("txnDate : " + T_txnDate);
        System.out.println("txnTime : " + T_txnTime);
        System.out.println("total : " + T_total);
        System.out.println();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        XMLparser xp = new XMLparser();
        xp.parserXMLFile("testFile/testFile.xml");
        xp.printTest();
        xp.createXMLFile("testFile/testFileCopy.xml");
        xp.clearVariable(true, true, true);
        xp.createXMLFile("testFile/testEmpty.xml");
        xp.parserXMLFile("testFile/testEmpty.xml");
        xp.printTest();
        
    }
    
}