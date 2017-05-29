package br.com.jmsstudio.validation;

import br.com.jmsstudio.model.Produto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ProdutoValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Produto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
        ValidationUtils.rejectIfEmpty(errors, "dataLancamento", "field.required");

        Produto produto = (Produto) obj;
        if (produto.getPaginas() <= 0) {
            errors.rejectValue("paginas", "field.required");
        }
    }
}
