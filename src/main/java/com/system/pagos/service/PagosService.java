package com.system.pagos.service;

import com.system.pagos.domain.PagosDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PagosService {

    ResponseEntity<List<PagosDTO>> allpagos();
    ResponseEntity<PagosDTO> savePago(PagosDTO pago);
    ResponseEntity<PagosDTO> pago(Integer idPago);

    ResponseEntity<PagosDTO> uptStaus(Integer idPago,String status);
}
