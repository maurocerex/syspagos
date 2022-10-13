package com.system.pagos.repository;

import com.system.pagos.entity.PagosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagosRepository extends  JpaRepository<PagosEntity, Integer> {

    PagosEntity findByIdPago(Integer pago);


}
