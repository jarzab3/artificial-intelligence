package com.company;

import java.awt.*;
import java.awt.event.ItemListener;

public class CANTCheckboxMenuItem extends CheckboxMenuItem{


       public CANTCheckboxMenuItem (String label,ItemListener listener, boolean state,Menu menu){
          super(label);
          this.addItemListener(listener);
          this.setState(state);
          menu.add(this);
        }
}