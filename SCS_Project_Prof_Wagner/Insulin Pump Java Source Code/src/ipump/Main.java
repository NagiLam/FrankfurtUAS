package ipump;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {
    public static boolean mg = false;
    
    public static void main(String[] args) throws IOException {
        Human man = new Human();
        Controller controller = new Controller(man);
        Input input = new Input();
        boolean wasTurned = false;
        Window window = new Window(controller, input);
        BufferedWriter writer = new BufferedWriter(new FileWriter("report.txt")); 
        int sum = 0;
        while(true) {      
        	if(controller.isOn()) {
        		wasTurned = true;
        	}
        	if(!(controller.isOn())&&(wasTurned)) {
        		wasTurned = false;
        		writer.close();
        	}
            Clock.setSpeed(input.speedKeyPressed());
            if (Clock.getSpeed() > 8)
            {
                Wav.stop();
            }
            controls(input, man, controller);
            input.setOldKeys();
            if (Clock.update())
            {	
            	if((int)Clock.getTotalTime()%60==0) {
            		if((int)Clock.getTotalTime()==1440) {
            			sum+=1440;
            		}
                	writer.write((int) Clock.getTotalTime()+sum+",");
                	writer.write(controller.getSugarLevel(0)+"");
                	writer.newLine();	
            	}
                man.update();
                controller.update();    
            }
        }
    }
    
    private static void controls(Input input, Human man, Controller controller)
    {
        if(input.isKeyAPressed())
        {
            controller.power();
            
            Wav.stop();
            if (controller.isOn())
                Wav.play("beep.wav");   
        }
        if (input.isKeyC())
        {
            controller.getBattery().charging();
        } else {
            controller.getBattery().chargingOff();
        }
        if (input.isKeyEPressed())
        {
            man.eat();
            
            Wav.play("eat.wav");
        }
        if (input.isKeyRPressed())
        {
            man.eatBig();
            
            Wav.play("eat.wav");
        }
        if (input.isKeyIPressed())
        {
            controller.reservoirRemoved();
        }

        if (input.isKeyOPressed())
        {
            controller.replaceCartridge(true);
        }

        if (input.isKeyPPressed())
        {
            controller.replaceCartridge(false);
        }

        if (controller.isOn())
        {

            if(input.isKeyXPressed())
            {
                mg = !mg;
            }

            if(input.isKeyFPressed())
            {
                controller.manualInject();
            }

            if(input.isKeyVPressed())
            {
                controller.alarm.errorDelivery = !controller.alarm.errorDelivery;
                
                Wav.stop();
                if (controller.alarm.errorDelivery)
                    Wav.play("alarm.wav");
            }

            if(input.isKeyBPressed())
            {
                controller.alarm.errorSensor = !controller.alarm.errorSensor;
                
                Wav.stop();
                if (controller.alarm.errorSensor)
                    Wav.play("alarm.wav");   
            }

            if(input.isKeyNPressed())
            {
                controller.alarm.errorNeedle = !controller.alarm.errorNeedle;
                
                Wav.stop();
                if (controller.alarm.errorNeedle)
                    Wav.play("alarm.wav");   
            }


            if(input.isKeyMPressed())
            {
                controller.alarm.errorPump = !controller.alarm.errorPump;
                
                Wav.stop();
                if (controller.alarm.errorPump)
                    Wav.play("alarm.wav");   
            }
            
            if(input.isKeyQPressed())
            {
                controller.canGlucagon = !(controller.canGlucagon);
            }
        }
    }
}