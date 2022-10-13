package com.system.pagos.exception;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@ApiModel(value="Errors")
@EqualsAndHashCode(callSuper = false)
public class InternalServerException {


}
