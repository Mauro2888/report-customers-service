package report.model.dto;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class DatePatternValidation implements ConstraintValidator<DatePatternFormat, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.unwrap(HibernateConstraintValidatorContext.class)
                .addExpressionVariable("validatedValue", localDate.toString())
                .addMessageParameter("message", "Invalid date format. Use yyyy-MM-dd");
        return Pattern.matches("^$|^([0-9]{4}-[0-9]{2}-[0-9]{2})$", localDate.toString());
    }
}
