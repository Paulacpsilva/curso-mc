package com.paulaproenca.cursomc.services.validation;

import com.paulaproenca.cursomc.domain.Cliente;
import com.paulaproenca.cursomc.dto.ClienteDto;
import com.paulaproenca.cursomc.repositories.ClienteRepository;
import com.paulaproenca.cursomc.resources.exception.FieldMessage;
import com.paulaproenca.cursomc.services.validation.utils.ClienteUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDto> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(ClienteDto clienteDto, ConstraintValidatorContext context) {
        Map<String , String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer urlId = Integer.parseInt(map.get("id"));
        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(clienteDto.getEmail());
        if (aux != null && !aux.getId().equals(urlId)) {
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
