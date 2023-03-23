package pl.kamil.wyniki_strzeleckie.validation;

import pl.kamil.wyniki_strzeleckie.model.CompetitorDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        CompetitorDTO user = (CompetitorDTO) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
