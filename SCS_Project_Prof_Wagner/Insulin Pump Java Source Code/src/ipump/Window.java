package ipump;

import javax.swing.*;


public class Window extends JFrame {
	private static final long serialVersionUID = 1L;


    public Window(Controller controller, Input contr) {
       
        JFrame f = new JFrame("Insulin Pump");
   
        JFrame.setDefaultLookAndFeelDecorated(false);
       
        f.setSize(960, 540);
    
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
     
        f.setResizable(false);
     
        f.setContentPane(new Output(controller));
  
        f.setVisible(true);
     
        f.addKeyListener(contr);
    }
}