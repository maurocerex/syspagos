package com.system.pagos.controller;


import com.system.pagos.domain.PagosDto;
import com.system.pagos.service.PagosService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@CrossOrigin
@Slf4j
@Api(value = "MANAGEMENT-PAYMENTS", tags = {"Administraci\u00F3n de Pagos"})
@RequestMapping("/v1/pagos")
public class PagosController {

    private PagosService servicioPagos;

    @Autowired
    public PagosController(PagosService servicioPagos) {
        this.servicioPagos = servicioPagos;
    }


    @ApiOperation(value = "Pago - Obtiene todos los pagos realizados", response = PagosDto.class)
    @GetMapping("/")
    public ResponseEntity<List<PagosDto>> allpagos() {
        return servicioPagos.getPagos();
    }


    @ApiOperation(value = "Pago - Almacenar pago", response = PagosDto.class)
    @PostMapping
    public ResponseEntity<PagosDto> save(@ApiParam(value = "pago", example = "{}", required = true)
                            @RequestBody PagosDto pago) {
        return servicioPagos.savePago(pago);
    }

    @ApiOperation(value = "Pago - Consulta de pago", response = PagosDto.class)
    @GetMapping("/{idPago}")
    public ResponseEntity<PagosDto> pago(@PathVariable Integer idPago) {
        return servicioPagos.pago(idPago);
    }

    @ApiOperation(value = "Pago - Obbetener estatus de pago", response = String.class)
    @GetMapping("/status/{idPago}")
    public ResponseEntity<String> statusPago(@PathVariable Integer idPago) {
        return new ResponseEntity<>(servicioPagos.pago(idPago).getBody().getEstatusPago(), HttpStatus.OK);
    }

    @ApiOperation(value = "Pago - actualiza estatus de pago por ID", response = PagosDto.class)
    @PutMapping()
    public ResponseEntity<PagosDto> statusPago(@RequestParam(value = "idPago") Integer idPago,
                                               @RequestParam(value = "statusPago") String statusPago) {
        return servicioPagos.updateEstatus(idPago,statusPago);
    }
}
