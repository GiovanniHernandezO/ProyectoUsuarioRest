package com.ejemplo.dto;

public class MensajeErrorDTO {

    public MensajeErrorDTO(String mensaje) {
        this.mensaje = mensaje;
    }
    
    private String mensaje;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
