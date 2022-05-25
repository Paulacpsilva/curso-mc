package com.paulaproenca.cursomc.resources.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FieldMessage {

    private String fieldName;

    private String message;
}
