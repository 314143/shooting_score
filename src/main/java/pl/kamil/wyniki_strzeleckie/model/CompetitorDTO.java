package pl.kamil.wyniki_strzeleckie.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.kamil.wyniki_strzeleckie.validation.PasswordMatches;
import pl.kamil.wyniki_strzeleckie.validation.ValidEmail;

@Getter
@Setter
@PasswordMatches
public class CompetitorDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String password;
    private String matchingPassword;

    @NotNull
    @ValidEmail
    private String email;

    @NotNull
    private String licenseNumber;

    @NotNull
    private String clubName;

}
