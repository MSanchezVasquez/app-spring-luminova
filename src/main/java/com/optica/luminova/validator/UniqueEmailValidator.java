package com.optica.luminova.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.optica.luminova.repository.UsuarioRepository;
@RequiredArgsConstructor
@Component
public class UniqueEmailValidator implements ConstraintValidator <UniqueEmail, String> {
@Autowired
    private UsuarioRepository usuarioRepository;
@Override
    public boolean isValid(String email, ConstraintValidatorContext context){
    return email!=null && !usuarioRepository.existsByEmail(email);
}

}
