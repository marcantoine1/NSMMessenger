/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ca.qc.bdeb.p56.NSMMessenger.Vue;

import ca.qc.bdeb.P56.NSMMessenger.Vue.FxGUI;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.*;

/**
 *
 * @author Ordinatron
 */
public class FXMLControllerProfileTest extends Application{
    @Rule public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    @BeforeClass    
    public static void setUpClass() {        
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
     @BeforeClass
    public static void initJFX() {
        Thread t = new Thread("JavaFX Init Thread") {
            @Override
            public void run() {
                Application.launch(FxGUI.class, new String[0]);
            }
        };
        t.setDaemon(true);
        t.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
    }
}
