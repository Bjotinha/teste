
package Modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


public class DataFormato {
    private String data, hora;
    
    
    public void formatData(){
        DateTimeFormatter dtIso = DateTimeFormatter.ISO_DATE;
        LocalDateTime agora = LocalDateTime.now();
        String format = dtIso.format(agora);
        setData(format);
    }
    
    public void formatHora(){
        DateTimeFormatter dtIso = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        LocalDateTime agora = LocalDateTime.now();
        String format = dtIso.format(agora);
        setHora(format);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    
    
}
