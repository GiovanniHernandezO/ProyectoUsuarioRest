package com.ejemplo.vo;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHONE")
public class PhoneVO implements Serializable {
    @Id
    private UUID id;
    @Column(name = "IDUSUARIO")
    private UUID idUsuario;
    @Column(name = "NUMBER")
    private String number;
    @Column(name = "CITYCODE")
    private String citycode;
    @Column(name = "CONTRYCODE")
    private String contrycode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getContrycode() {
        return contrycode;
    }

    public void setContrycode(String contrycode) {
        this.contrycode = contrycode;
    }
}
