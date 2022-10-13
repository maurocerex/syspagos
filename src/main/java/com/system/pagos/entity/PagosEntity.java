package com.system.pagos.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "pagos")
public class PagosEntity implements Serializable {

    @Id
    @Column(name = "id_pago", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPago;

    @Basic
    @Column(name = "concepto")
    private String concepto;

    @Basic
    @Column(name = "cantidad")
    private Integer cantidad;

    @Basic
    @Column(name = "cliente_emisor")
    private String clienteEmisor;

    @Basic
    @Column(name = "cliente_receptor")
    private String clienteReeceptor;

    @Basic
    @Column(name = "monto_pago")
    private Double montoPago;

    @Basic
    @Column(name = "estatus_pago")
    private String estatusPago;

    @Basic
    @Column(name = "fecha_pago")
    private Date fechaPago;
}
