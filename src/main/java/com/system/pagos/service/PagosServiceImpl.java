package com.system.pagos.service;

import com.system.pagos.domain.PagosDto;
import com.system.pagos.entity.PagosEntity;
import com.system.pagos.exception.InternalServerErrorException;
import com.system.pagos.exception.RecordNotFoundException;
import com.system.pagos.kafka.KafkaProducer;
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

    private final KafkaProducer kafkaProducer;

    private final PagosRepository pagosRepository;

    private ModelMapper modelMapper;

    public PagosServiceImpl(PagosRepository pagosRepository, ModelMapper modelMapper, KafkaProducer kafkaProducer) {
        this.pagosRepository = pagosRepository;
        this.modelMapper = modelMapper;
        this.kafkaProducer = kafkaProducer;
    }


    @Override
    public ResponseEntity<List<PagosDto>> getPagos() {
        List<PagosEntity> allpagos = pagosRepository.findAll();
        if (!allpagos.isEmpty()) {
            return new ResponseEntity<>(allpagos.stream()
                    .map(PagosEntity -> modelMapper.map(PagosEntity, PagosDto.class))
                    .collect(Collectors.toList()), HttpStatus.OK);
        } else {
            throw new RecordNotFoundException(
                    "OBTENCION DE PAGOS",
                    "NO EXISTE EPAGOS"
            );
        }
    }


    @Override
    public ResponseEntity<PagosDto> savePago(PagosDto newPago){
        try{
            PagosEntity pago = this.modelMapper.map(newPago, PagosEntity.class);
            log.info("eenvio mensaje prod");
            PagosDto pagoMessage = this.modelMapper.map(pagosRepository.save(pago), PagosDto.class);
            kafkaProducer.sendMessage(pagoMessage,pago.getIdPago().toString());
            return new ResponseEntity<>(this.modelMapper.map(pagosRepository.save(pago), PagosDto.class),HttpStatus.CREATED);
        }catch (Exception e){
            throw new InternalServerErrorException(
                    "SAVE_PAGO",
                    "ERROR INISPERADO"
            );
        }

    }

    @Override
    public ResponseEntity<PagosDto> pago(Integer idPago) {
        PagosEntity pagosEntity = pagosRepository.findByIdPago(idPago);
        if (pagosEntity != null) {
            PagosDto pago = this.modelMapper.map(pagosEntity, PagosDto.class);
            return new ResponseEntity<>(pago,HttpStatus.OK);
        } else {
            throw new RecordNotFoundException(
                    "OBTENCION DE PAGO",
                    "NO EXISTE EL ID DEL PAGO"
            );
        }
    }


    @Override
    public ResponseEntity<PagosDto> updateEstatus(Integer idPago, String status){
            PagosEntity pagoUpdate = pagosRepository.findByIdPago(idPago);
            if (pagoUpdate != null) {
                pagoUpdate.setEstatusPago(status);
                log.info("eenvio mensaje prod");
                PagosDto pagoMessage = this.modelMapper.map(pagosRepository.save(pagoUpdate), PagosDto.class);
                kafkaProducer.sendMessage(pagoMessage,pagoUpdate.getIdPago().toString());
                return new ResponseEntity<>(this.modelMapper.map(pagosRepository.save(pagoUpdate), PagosDto.class),HttpStatus.OK);
            } else {
                throw new RecordNotFoundException(
                        "ACTUALIZACION_ESTATUS",
                        "NO EXISTE EL ID DEL PAGO"
                );
            }
    }
}
