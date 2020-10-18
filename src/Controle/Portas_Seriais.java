
package Controle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.comm.*;

public  class Portas_Seriais {
    
    private String[] name_Port;
    private String[] tipo_Port;
    Enumeration list_Port;
    
    public void retornaPorta() {        
        list_Port = CommPortIdentifier.getPortIdentifiers();
        name_Port = new String[5];
        tipo_Port = new String[5];
        int i = 0;
        
        while(list_Port.hasMoreElements()){
            CommPortIdentifier num = (CommPortIdentifier)list_Port.nextElement();
            name_Port[i]= num.getName();
            tipo_Port[i] = select_port(num.getPortType());
            i++;
        }
        
       
         
       
    }
    
    public String select_port(int port){
        switch(port){
            case CommPortIdentifier.PORT_SERIAL:
                return "Serial";
            case CommPortIdentifier.PORT_PARALLEL:
                return "Paralela";
            default:
                return "Não identificada";
        }
    }

    public String[] getName_Port() {
        return name_Port;
    }

    public void setName_Port(String[] name_Port) {
        this.name_Port = name_Port;
    }

    public String[] getTipo_Port() {
        return tipo_Port;
    }

    public void setTipo_Port(String[] tipo_Porta) {
        this.tipo_Port = tipo_Porta;
    }
    
            
}
