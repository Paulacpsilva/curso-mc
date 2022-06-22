package com.paulaproenca.cursomc.services.validation;

import com.paulaproenca.cursomc.domain.Cliente;
import com.paulaproenca.cursomc.domain.enums.TipoCliente;
import com.paulaproenca.cursomc.dto.ClienteNewDto;
import com.paulaproenca.cursomc.repositories.ClienteRepository;
import com.paulaproenca.cursomc.resources.exception.FieldMessage;
import com.paulaproenca.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteNewDto clienteNewDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if(clienteNewDto.getTipoCliente().equals(TipoCliente.PESSOA_FISICA.getCodigo())
        &&
        !BR.isValidCPF(clienteNewDto.getCpfCnpj())) {
            list.add(new FieldMessage("CPFOuCNPJ", "CPF invalido!"));
        }

        if(clienteNewDto.getTipoCliente().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
                &&
                !BR.isValidCNPJ(clienteNewDto.getCpfCnpj())) {
            list.add(new FieldMessage("CPFOuCNPJ", "CNPJ invalido!"));
        }

        Cliente aux = clienteRepository.findByEmail(clienteNewDto.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

}
