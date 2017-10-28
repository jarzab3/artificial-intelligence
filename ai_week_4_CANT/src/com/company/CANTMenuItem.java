package com.company;

import java.awt.*;
import java.awt.event.ActionListener;


public class CANTMenuItem extends MenuItem {

       public CANTMenuItem(String label,ActionListener listener,Menu menu) {
            super(label);
            this.addActionListener(listener);
            menu.add(this);
       }

}