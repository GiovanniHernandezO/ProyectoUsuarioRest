package com.ejemplo.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "USUARIO")
public class UsuarioVO implements Serializable {

    @Id
    private UUID id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PhoneVO> phones;
    @Column(name = "CREATED")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date created;
    @Column(name = "MODIFIED")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date modified;
    @Column(name = "LAST_LOGIN")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date last_login;
    @Column(name = "TOKEN")
    private String token;
    @Column(name = "ISACTIVE")
    private Boolean isactive;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PhoneVO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneVO> phones) {
        this.phones = phones;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}
