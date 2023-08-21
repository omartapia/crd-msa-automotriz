package com.pichincha.crd.automotriz.util.validators;

import com.pichincha.crd.automotriz.service.dto.VehicleDto;

import static com.pichincha.crd.automotriz.util.constants.FIELD_ERROR;
import static com.pichincha.crd.automotriz.util.constants.FIELD_REQUIRED;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class VehicleValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return VehicleDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "plate", FIELD_REQUIRED, FIELD_ERROR);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "model", FIELD_REQUIRED, FIELD_ERROR);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "chassisNumber", FIELD_REQUIRED, FIELD_ERROR);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brand", FIELD_REQUIRED, FIELD_ERROR);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "engineDisplacement", FIELD_REQUIRED, FIELD_ERROR);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", FIELD_REQUIRED, FIELD_ERROR);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valuation", FIELD_REQUIRED, FIELD_ERROR);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "state", FIELD_REQUIRED, FIELD_ERROR);
    }

}
