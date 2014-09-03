/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1150275
 */
public class TestAuthentificateur {
    
    public TestAuthentificateur() {
    }
    private static DocumentBuilderFactory xmlFactory;
    private static DocumentBuilder builder;
    @BeforeClass
    public static void setUpClass() {
        try{
            builder = xmlFactory.newDocumentBuilder();
        }
        catch(final ParserConfigurationException e){
            e.printStackTrace();
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    private void testUserToXml(){
        
    } 
    private void testPasswordToXml(){
        
    }
    private void testUserFromXml(){
        
    }
    private void testPasswordFromXml(){
        
    }
}
