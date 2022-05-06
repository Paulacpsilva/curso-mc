package com.paulaproenca.cursomc.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class StandardError {

    private Integer status;
    private String msg;
    private Long timeStamp;

}
