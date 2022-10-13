package com.system.pagos.controller;


import com.system.pagos.domain.PagosDTO;
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


    @ApiOperation(value = "PAGOS - Get Alll Payment", response = PagosDTO.class)
    @GetMapping("/allPagos")
    public ResponseEntity<List<PagosDTO>> allpagos() {
        return servicioPagos.allpagos();
    }


    @ApiOperation(value = "PAGOS- Save Payment", response = PagosDTO.class)
    @PostMapping
    public ResponseEntity<PagosDTO> save( @ApiParam(value = "pago", example = "{}", required = true)
                            @RequestBody PagosDTO pago) {
        return servicioPagos.savePago(pago);
    }

    @ApiOperation(value = "PAGOS - Get Payment For Id", response = PagosDTO.class)
    @GetMapping("/cvePago/{idPago}")
    public ResponseEntity<PagosDTO> pago(@PathVariable Integer idPago) {
        return servicioPagos.pago(idPago);
    }

    @ApiOperation(value = "PAGOS - Get Status Payment For Id", response = String.class)
    @GetMapping("/status/cvePago/{idPago}")
    public ResponseEntity<String> statusPago(@PathVariable Integer idPago) {
        return new ResponseEntity<>(servicioPagos.pago(idPago).getBody().getEstatusPago(), HttpStatus.OK);
    }

    @ApiOperation(value = "PAGOS - Update Staus Payment For Id", response = PagosDTO.class)
    @PutMapping()
    public ResponseEntity<PagosDTO> statusPago(@RequestParam(value = "idPago") Integer idPago,
                             @RequestParam(value = "statusPago") String statusPago) {
        return servicioPagos.uptStaus(idPago,statusPago);
    }
}
