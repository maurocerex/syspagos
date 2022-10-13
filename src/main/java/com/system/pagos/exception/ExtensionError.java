package com.system.pagos.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@Builder
@ApiModel(value="ExtError")
@EqualsAndHashCode(callSuper = false)

public class ExtensionError {
    @ApiModelProperty(name= "providerCode",example = "ERR-01")
    private String providerCode;

    @ApiModelProperty(name= "providerMessage", example ="Something was wrong or user need to be advice")
    private String providerMessage;

    @ApiModelProperty(name= "transactionId", example ="4412656255")
    private String transactionId;

}
