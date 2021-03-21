package ipump;

import java.awt.*;
import javax.swing.*;
import javax.annotation.Resources;


public class Output extends JComponent {
    private static final long serialVersionUID = 2L;

    public Controller controller;
    
    Image imgBackround;
    Image imgBackgroundOn;
    Image imgBattery;
    Image imgReservoir;    
    Image imgReservoirDose;
    Image imgReservoirDoseSmall;    
    Image imgAlarmRisingSugar;
    Image imgAlarmMissingReservoir;
    Image imgAlarmUsedReservoir;    
    Image imgErrorBackground;
    Image imgErrorSensor;
    Image imgErrorDelivery;
    Image imgErrorPump;
    Image imgErrorNeedle;
    Image imgGlucagon;
    Image imgInfinity;
    Font fontMini;
    Font fontSmall;
    Font font;
    Font fontLarge;
    
    Color darkRed = new Color(150, 50, 50);
    Color grayGreen = new Color(160, 170, 160);
    
    final JFrame parent = new JFrame();
    JButton button = new JButton();


    
   
    public Output(Controller controller)
    {
        this.controller = controller;
        loadImages();
        loadFont();
    }
    

    private void loadImages()
    {
        imgBackround = loadImage("bg.png");
        imgBackgroundOn = loadImage("bgon.png");
        imgBattery = loadImage("bat.png");
        imgReservoir = loadImage("cart.png");
        imgReservoirDose = loadImage("cartdose.png");
        imgReservoirDoseSmall = loadImage("cartdoses.png");
        imgAlarmRisingSugar = loadImage("alarmsugar.png");
        imgAlarmMissingReservoir = loadImage("alarmmissing.png");
        imgAlarmUsedReservoir = loadImage("alarmwrong.png");
        imgErrorBackground = loadImage("error.png");
        imgErrorSensor = loadImage("esensor.png");
        imgErrorDelivery = loadImage("edelive.png");
        imgErrorPump = loadImage("epump.png");
        imgErrorNeedle = loadImage("eneedle.png");
        imgGlucagon = loadImage("pills.png");
        imgInfinity = loadImage("infinity.png");
    }
    

    private Image loadImage(String location)
    {
        return Toolkit.getDefaultToolkit().getImage(Resources.class.getResource("/resources/img/" + location));
    }
    
    private void loadFont()
    {        
     
        try{
            font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/font/digital.ttf")).deriveFont(Font.PLAIN, 22);
            fontMini = font.deriveFont(Font.PLAIN, 8f);
            fontSmall = font.deriveFont(Font.PLAIN, 12f);
            fontLarge =  font.deriveFont(Font.PLAIN, 52f);
        }
       
        catch (Exception ex)
        {
            fontMini = new Font("serif", Font.PLAIN, 8);
            fontSmall = new Font("serif", Font.PLAIN, 12);
            font = new Font("serif", Font.PLAIN, 22);
            fontLarge = new Font("serif", Font.PLAIN, 52);
        }
    }
    

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
     
        setBackground(grayGreen);

        Graphics2D g2d = (Graphics2D) g;
     
        g2d.scale(4, 4);

        g2d.drawImage(imgBackround, 0, 0, this);
        
        if (controller.isOn())
        {
        	if((controller.getBatteryPower()<15)) {
        		drawBatteryError(g,g2d);
        	}else if(controller.getCritLow()){
        		drawLowError(g,g2d);
        	}else if(controller.getCritHigh()){
        		drawHighError(g,g2d);
        	}else {
                
                g2d.drawImage(imgBackgroundOn, 0, 0, this);
         
                drawOn(g, g2d);
        	}

        }

        else if (controller.getBattery().isCharging())
        {
            drawBattery(g2d);
        }
        
  
        fps();
      

        repaint();
    }
    
    private void drawBatteryError(Graphics g, Graphics2D g2d) {
    	   g.setFont(font);
    	   g2d.setColor(Color.black);
    	   g2d.drawString("Battery low.", 50, 37);
    	   g2d.drawString("Please Recharge.", 50, 57);
    }
    private void drawLowError(Graphics g, Graphics2D g2d) {
 	   g.setFont(font);
 	   g2d.setColor(Color.black);
 	   g2d.drawString("Low blood sugar.", 50, 37);
 	   g2d.drawString("Caretaker notified.", 50, 57);
 }
    private void drawHighError(Graphics g, Graphics2D g2d) {
 	   g.setFont(font);
 	   g2d.setColor(Color.black);
 	   g2d.drawString("High blood sugar.", 50, 37);
 	   g2d.drawString("Caretaker notified.", 50, 57);
 }
    private void drawOn(Graphics g, Graphics2D g2d)
    {
        drawBattery(g2d);
        drawReservior(g2d);
        
        drawDoseIcon(g2d);
        drawGlucagon(g,g2d);
        drawCriticalErrors(g2d);
        drawOtherErrors(g2d);

        drawText(g, g2d);
        
    }
    
    

    private void drawBattery(Graphics2D g2d)
    {
        if (controller.getBatteryPower() >= 80)
        {
            g2d.drawImage(imgBattery, 204, 4, 236, 20, 0, 0, 32, 16, this);
        }
        else if (controller.getBatteryPower() >= 60)
        {
            g2d.drawImage(imgBattery, 204, 4, 236, 20, 0, 16, 32, 32, this);
        }   
        else if (controller.getBatteryPower() >= 40)
        {
            g2d.drawImage(imgBattery, 204, 4, 236, 20, 0, 32, 32, 48, this);
        }
        else if (controller.getBatteryPower() >= 20)
        {
            g2d.drawImage(imgBattery, 204, 4, 236, 20, 0, 48, 32, 64, this);
        }    
        else
        {
            g2d.drawImage(imgBattery, 204, 4, 236, 20, 0, 64, 32, 80, this);
        }
    }
  

    private void drawReservior(Graphics2D g2d) {
        if (controller.getRemaningReservoir() >= 80)
        {
            g2d.drawImage(imgReservoir, 7, 18, 38, 26, 0, 0, 31, 8, this);
        }
        else if (controller.getRemaningReservoir() >= 60)
        {
            g2d.drawImage(imgReservoir, 7, 18, 38, 26, 0, 8, 31, 16, this);
        }   
        else if (controller.getRemaningReservoir() >= 40)
        {
            g2d.drawImage(imgReservoir, 7, 18, 38, 26, 0, 16, 31, 24, this);
        }
        else if (controller.getRemaningReservoir() >= 20)
        {
            g2d.drawImage(imgReservoir, 7, 18, 38, 26, 0, 24, 31, 32, this);
        }    
        else
        {
            g2d.drawImage(imgReservoir, 7, 18, 38, 26, 0, 32, 31, 40, this);
        }
    }
    

    private void drawDoseIcon(Graphics2D g2d) {
       
        if (controller.getSugarLevelDose(0))
        {
            g2d.drawImage(imgReservoirDose, 89, 38, this);
        }
        
        if (controller.getSugarLevelDose(1))
        {
            g2d.drawImage(imgReservoirDoseSmall, 184, 35, this);
        }
        
        if (controller.getSugarLevelDose(2))
        {
            g2d.drawImage(imgReservoirDoseSmall, 184, 56, this);
        }
    }
    
    private void drawGlucagon(Graphics g,Graphics2D g2d) {
    	float alpha = 0.1f;
    	  if (controller.givenGlucagon)
          {
    	    	  alpha = 1f; //draw half transparent

    	    	  //parent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
 
          }
 			AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			g2d.setComposite(ac);
            //g2d.drawImage(imgGlucagon, 61,90,15,25, this);
            //g2d.drawImage(imgInfinity, 60,107,18,23, this);
			   g.setFont(fontMini);
	    	   g2d.setColor(Color.black);
	    	   g2d.drawString("Glucagon", 115, 95);
            alpha = 1f;
        	ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
			g2d.setComposite(ac);
        
    }
    

    private void drawCriticalErrors(Graphics2D g2d) {
        if (controller.alarm.errorPump || controller.alarm.errorDelivery ||
            controller.alarm.errorSensor || controller.alarm.errorNeedle)
        {
            g2d.drawImage(imgErrorBackground, 194, 85, this);
            if (controller.alarm.errorPump)
            {
                g2d.drawImage(imgErrorPump, 197, 94, this);
            }
            
            if (controller.alarm.errorDelivery)
            {
                g2d.drawImage(imgErrorDelivery, 197, 103, this);
            }
            
            if (controller.alarm.errorSensor)
            {
                g2d.drawImage(imgErrorSensor, 197, 112, this);
            }
            
            if (controller.alarm.errorNeedle)
            {
                g2d.drawImage(imgErrorNeedle, 197, 121, this);
            }
        }
    }


    private void drawOtherErrors(Graphics2D g2d) {
        if (controller.alarm.sugarRising)
        {
            g2d.drawImage(imgAlarmRisingSugar, 83, 100, this);    
        }
                
        if (controller.alarm.missingReservoir)
        {
            g2d.drawImage(imgAlarmMissingReservoir, 120, 100, this);    
        }
        
        if (controller.alarm.incorrectReservoir)
        {
            g2d.drawImage(imgAlarmUsedReservoir, 157, 100, this);    
        }
    }


    private void drawText(Graphics g, Graphics2D g2d) {
      
        g.setFont(font);
        g2d.setColor(Color.BLACK);
     
        if (Clock.getTotalTime()%60 < 10)
        {
            g2d.drawString(Clock.getTotalTime()/60 + " : 0" + Clock.getTotalTime()%60, 98, 17);
        }
        else 
        {
            g2d.drawString(Clock.getTotalTime()/60 + " : " + Clock.getTotalTime()%60, 98, 17);
        }
        
        
      
        g.setFont(fontSmall);
        if (Clock.getTotalTime()/60 >= 12)
        {
            if (Clock.getTotalTime()/60 >= 20)
            {
                g2d.drawString("pm", 156, 17);
            } else {
                g2d.drawString("pm", 148, 17);       
            }
        } else {
            g2d.drawString("am", 150, 17);
        }
        
   
        g.setFont(fontLarge);
        g2d.setColor(Color.BLACK);
        if((controller.getSugarLevel(0) > 0) &&
                (controller.getSugarLevel(0) < 75 || controller.getSugarLevel(0) > 135))
        {
            g2d.setColor(darkRed);
        }
        if (Main.mg)
        {
            g2d.drawString("" + controller.getSugarLevel(0), 100, 70);
        } else {
            g2d.drawString("" + (float)Math.round(controller.getSugarLevel(0) * 0.0555 * 10) / 10, 100, 70);
        }
        
     
        g.setFont(fontMini);
        if(Main.mg)
        {
            g2d.drawString("mg", 165, 62);
            g2d.drawString("__", 165, 64);
            g2d.drawString("dl", 165, 70);
        } else{
            g2d.drawString("mmol", 163, 62);
            g2d.drawString("__", 165, 64);
            g2d.drawString(" l", 165, 70);
        }
        
        g.setFont(font);
        g2d.setColor(Color.BLACK);

        if((controller.getSugarLevel(1) > 0) &&
                (controller.getSugarLevel(1) < 75 || controller.getSugarLevel(1) > 135))
        {
            g2d.setColor(darkRed);
        }
        if (Main.mg)
        {
            g2d.drawString("" + controller.getSugarLevel(1), 190, 50);
        } else {
       
            g2d.drawString("" + (float)Math.round(controller.getSugarLevel(1) * 0.0555 * 10) / 10, 190, 50);
        }
        g.setFont(font);
        
        
    
        g2d.setColor(Color.BLACK);
     
        if((controller.getSugarLevel(2) > 0) &&
                (controller.getSugarLevel(2) < 75 || controller.getSugarLevel(2) > 135))
        {
            g2d.setColor(darkRed);
        }
        if (Main.mg)
        {
            g2d.drawString("" + controller.getSugarLevel(2), 190, 70);
        } else {

            g2d.drawString("" + (float)Math.round(controller.getSugarLevel(2) * 0.0555 * 10) / 10, 190, 70);
        }

        g2d.setColor(Color.BLACK);
        g.setFont(font);
    
        if (controller.getRemaningReservoir() == 0)
        {
            g2d.setColor(darkRed);    
        }
        g2d.drawString("" + controller.getRemaningReservoir(), 10, 16);

        
       
        g2d.setColor(Color.BLACK);
        if (controller.getTotalDailyDose() >= controller.getMaxDailyDose())
        {
            g2d.setColor(darkRed);
        }
        g2d.drawString("" + controller.getTotalDailyDose() + "/" + controller.getMaxDailyDose(), 12, 110);
        
        
       
        g2d.setColor(Color.BLACK);
   
        if (controller.getTimePreviousDose()%60 < 10)
        {
            g2d.drawString(" - " +
                controller.getTimePreviousDose()/60 + ":0" + controller.getTimePreviousDose()%60,
                15, 62);
        }
        else 
        {
            g2d.drawString(" - " +
                controller.getTimePreviousDose()/60 + ":" + controller.getTimePreviousDose()%60,
                15, 62);
        }
        
   
        g2d.drawString("" + controller.getLastDose(), 5, 62);
        
        
        
        g.setFont(fontSmall);
        g2d.drawString("Sugar level" , 98 , 84);
        
        g2d.drawString("Previous dose" , 4, 76);
        
        g.setFont(fontMini);
        g2d.drawString("Past levels" , 182, 80);
        
        g2d.drawString("Daily dose" , 16, 122);
    }
    	
    private void fps(){
        try{
            Thread.sleep(16);
        }catch(Exception exp){
        }
    }
}
