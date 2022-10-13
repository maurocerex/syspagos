package com.system.pagos.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagosDTO implements Serializable {

    private Integer idPago;
    private String concepto;
    private Integer cantidad;
    private String usuarioMovimiento;
    private String clientePago;
    private Double montoPago;
    private String estatusPago;

}
