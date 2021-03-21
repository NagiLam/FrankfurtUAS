package com.insulin.controller;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  

@Controller  
public class PumpSimulation {  
@RequestMapping("/simulation")  
    public String display()  
    {  
        return "simulation";  
    }     
}  