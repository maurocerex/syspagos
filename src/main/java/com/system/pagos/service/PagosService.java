package com.system.pagos.service;

import com.system.pagos.domain.PagosDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PagosService {

    ResponseEntity<List<PagosDto>> getPagos();
    ResponseEntity<PagosDto> savePago(PagosDto pago);
    ResponseEntity<PagosDto> pago(Integer idPago);

    ResponseEntity<PagosDto> updateEstatus(Integer idPago, String status);
}
