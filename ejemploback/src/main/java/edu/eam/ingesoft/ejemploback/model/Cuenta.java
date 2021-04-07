package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Cuenta implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "amount")
    private double amount;

    @Column(name ="customerid")
    private String cedulaCliente;

    @Column(name = "openedat")
    private Date fechaApertura;

    public Cuenta() {
        fechaApertura = new Date();
    }

    public Cuenta(String id, double amount, String cedulaCliente, Date fechaApertura) {
        this.id = id;
        this.amount = amount;
        this.cedulaCliente = cedulaCliente;
        this.fechaApertura = fechaApertura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
}
