package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Cliente implements Serializable {
    @Id
    @Column(name = "id")
    private String cedula;

    @Column(name = "name")
    private String nombre;

    @Column(name = "lastname")
    private String apellido;

    @Column(name = "email")
    private String correo;

    public Cliente() {
    }

    public Cliente(String id, String name, String lastName, String email) {
        this.cedula = id;
        this.nombre = name;
        this.apellido = lastName;
        this.correo = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
