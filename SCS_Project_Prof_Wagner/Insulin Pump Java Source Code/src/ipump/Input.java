	package ipump;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input implements KeyListener{
    private boolean keyA = false;
    private boolean keyB = false;
    private boolean keyC = false;
    private boolean keyE = false;
    private boolean keyF = false;
    private boolean keyI = false;
    private boolean keyM = false;
    private boolean keyN = false;
    private boolean keyO = false;
    private boolean keyP = false;
    private boolean keyR = false;
    private boolean keyV = false;
    private boolean keyX = false;
    private boolean keyQ = false;
    private boolean keyAOld = false;
    private boolean keyBOld = false;
    private boolean keyEOld = false;
    private boolean keyFOld = false;
    private boolean keyIOld = false;
    private boolean keyMOld = false;
    private boolean keyNOld = false;
    private boolean keyOOld = false;
    private boolean keyPOld = false;
    private boolean keyROld = false;
    private boolean keyVOld = false;
    private boolean keyXOld = false;
    private boolean keyQOld = false;
    int speedKey = 1;
    
   
    public Input() {
    }
    
   
    public void keyPressed(KeyEvent e){
        
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_A: keyA = true; break;
            case KeyEvent.VK_B: keyB = true; break;
            case KeyEvent.VK_C: keyC = true; break;
            case KeyEvent.VK_E: keyE = true; break;
            case KeyEvent.VK_F: keyF = true; break;
            case KeyEvent.VK_I: keyI = true; break;
            case KeyEvent.VK_M: keyM = true; break;
            case KeyEvent.VK_N: keyN = true; break;
            case KeyEvent.VK_O: keyO = true; break;
            case KeyEvent.VK_P: keyP = true; break;
            case KeyEvent.VK_R: keyR = true; break;
            case KeyEvent.VK_V: keyV = true; break;
            case KeyEvent.VK_X: keyX = true; break;
            case KeyEvent.VK_Q: keyQ = true; break;
        }
        
        
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_1: speedKey = 1; break;
            case KeyEvent.VK_2: speedKey = 2; break;
            case KeyEvent.VK_3: speedKey = 4; break;                
            case KeyEvent.VK_4: speedKey = 8; break;
            case KeyEvent.VK_5: speedKey = 16; break;                
            case KeyEvent.VK_6: speedKey = 32; break;
            case KeyEvent.VK_7: speedKey = 64; break;
            case KeyEvent.VK_8: speedKey = 128; break;
            case KeyEvent.VK_9: speedKey = 256; break;                
            case KeyEvent.VK_0: speedKey = 512; break;
        }
    }

    
    public void keyReleased(KeyEvent e){   
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_A: keyA = false; break;
            case KeyEvent.VK_B: keyB = false; break;
            case KeyEvent.VK_C: keyC = false; break;
            case KeyEvent.VK_E: keyE = false; break;
            case KeyEvent.VK_F: keyF = false; break;
            case KeyEvent.VK_I: keyI = false; break;
            case KeyEvent.VK_M: keyM = false; break;
            case KeyEvent.VK_N: keyN = false; break;
            case KeyEvent.VK_O: keyO = false; break;
            case KeyEvent.VK_P: keyP = false; break;
            case KeyEvent.VK_R: keyR = false; break;
            case KeyEvent.VK_V: keyV = false; break;
            case KeyEvent.VK_X: keyX = false; break;
        }
    }
    
    
    public void setOldKeys(){
        keyEOld = keyE;
        keyROld = keyR;
        keyIOld = keyI;
        keyOOld = keyO;
        keyPOld = keyP;
        keyXOld = keyX;
        keyFOld = keyF;
        keyVOld = keyV;
        keyBOld = keyB;
        keyNOld = keyN;
        keyMOld = keyM;
        keyAOld = keyA;
        keyQOld = keyQ;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
        
    
    public int speedKeyPressed()
    {
        return speedKey;
    }
    
    
    public boolean isKeyC() {
        return keyC;
    }
    
    
    public boolean isKeyAPressed() {
        return keyA && !keyAOld;
    }
        
    
    public boolean isKeyBPressed() {
        return keyB && !keyBOld;
    }
    
    
    public boolean isKeyEPressed() {
        return keyE && !keyEOld;
    }

    
    public boolean isKeyRPressed() {
        return keyR && !keyROld;
    }
    
    
    public boolean isKeyFPressed() {
        return keyF && !keyFOld;
    }
       
    
    public boolean isKeyIPressed() {
        return keyI && !keyIOld;
    }
    
    
    public boolean isKeyMPressed() {
        return keyM && !keyMOld;
    }
    
    
    public boolean isKeyNPressed() {
        return keyN && !keyNOld;
    }
    
    
    public boolean isKeyOPressed() {
        return keyO && !keyOOld;
    }
       
    
    public boolean isKeyPPressed() {
        return keyP && !keyPOld;
    }
    
   
    public boolean isKeyVPressed() {
        return keyV && !keyVOld;
    }
   
    public boolean isKeyXPressed() {
        return keyX && !keyXOld;
    } 
    
    public boolean isKeyQPressed() {
        return keyQ && !keyQOld;
    }
}
