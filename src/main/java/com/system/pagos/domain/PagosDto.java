package com.system.pagos.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagosDto implements Serializable {

    private Integer idPago;
    private String concepto;
    private Integer cantidad;
    private String clienteEmisor;
    private String clienteReceptor;
    private Double montoPago;
    private String estatusPago;
    private Date feechaPago;

}
