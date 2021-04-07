package edu.eam.ingesoft.ejemploback.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaccion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "number")
    private Integer numero;

    @Column(name = "accountid")
    private String numeroCuenta;

    @Column(name = "type")
    private String tipo;

    @Column(name = "amount")
    private double monto;

    @Column(name = "date")
    private Date fecha;

    public Transaccion() { fecha = new Date(); }

    public Transaccion(String numeroCuenta, String tipo, double monto) {
        this.numero = numero;
        this.numeroCuenta = numeroCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = new Date();
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNumeroCuenta() { return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta;
    }

    public String getTipo() { return tipo;
    }

    public void setTipo(String tipo) { this.tipo = tipo;
    }

    public double getMonto() { return monto;
    }

    public void setMonto(double monto) { this.monto = monto;
    }

    public Date getFecha() { return fecha;
    }

    public void setFecha(Date fecha) { this.fecha = fecha;
    }
}