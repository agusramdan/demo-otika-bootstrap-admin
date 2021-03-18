package agusramdan.demo.otika.model.dto;

import lombok.Data;

@Data
public class RegisterDto  {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String passwordConfirm;


}
