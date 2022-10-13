package com.system.pagos.service;

import com.system.pagos.domain.PagosDTO;
import com.system.pagos.entity.PagosEntity;
import com.system.pagos.exception.InternalServerErrorException;
import com.system.pagos.exception.RecordNotFoundException;
import com.system.pagos.kafka.KafkaStringProducer;
import com.system.pagos.repository.PagosRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PagosServiceImpl implements PagosService{

    private final KafkaStringProducer stringProducer;

    private final PagosRepository pagosRepository;

    private ModelMapper modelMapper;

    public PagosServiceImpl(PagosRepository pagosRepository,ModelMapper modelMapper,KafkaStringProducer stringProducer) {
        this.pagosRepository = pagosRepository;
        this.modelMapper = modelMapper;
        this.stringProducer = stringProducer;
    }


    @Override
    public ResponseEntity<List<PagosDTO>> allpagos() {
        List<PagosEntity> allpagos = pagosRepository.findAll();
        if (!allpagos.isEmpty()) {
            return new ResponseEntity<>(allpagos.stream()
                    .map(PagosEntity -> modelMapper.map(PagosEntity, PagosDTO.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            throw new RecordNotFoundException(
                    "OBTENCION DE PAGOS",
                    "NO EXISTE EPAGOS"
            );
        }
    }


    @Override
    public ResponseEntity<PagosDTO> savePago(PagosDTO newPago){
        try{
            PagosEntity pago = this.modelMapper.map(newPago, PagosEntity.class);
            return new ResponseEntity<>(this.modelMapper.map(pagosRepository.save(pago), PagosDTO.class),HttpStatus.CREATED);
        }catch (Exception e){
            throw new InternalServerErrorException(
                    "SAVE_PAGO",
                    "ERROR INISPERADO"
            );
        }

    }

    @Override
    public ResponseEntity<PagosDTO> pago(Integer idPago) {
        PagosEntity pagosEntity = pagosRepository.findByIdPago(idPago);
        if (pagosEntity != null) {
            PagosDTO pago = this.modelMapper.map(pagosEntity, PagosDTO.class);
            return new ResponseEntity<>(pago,HttpStatus.OK);

        } else {
            throw new RecordNotFoundException(
                    "OBTENCION DE PAGO",
                    "NO EXISTE EL ID DEL PAGO"
            );
        }
    }


    @Override
    public ResponseEntity<PagosDTO> uptStaus(Integer idPago,String status){
            PagosEntity pagoUpdate = pagosRepository.findByIdPago(idPago);
            if (pagoUpdate != null) {
                pagoUpdate.setEstatusPago(status);
                log.info("EJECUTADO PRODUCER  TETSING");
                PagosDTO pagoMessage = this.modelMapper.map(pagosRepository.save(pagoUpdate),PagosDTO.class);
                stringProducer.sendMessage(pagoMessage,pagoUpdate.getIdPago().toString());
                return new ResponseEntity<>(this.modelMapper.map(pagosRepository.save(pagoUpdate), PagosDTO.class),HttpStatus.OK);
            } else {
                throw new RecordNotFoundException(
                        "ACTUALIZACION_ESTATUS",
                        "NO EXISTE EL ID DEL PAGO"
                );
            }
    }
}
