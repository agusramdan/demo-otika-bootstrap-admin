package agusramdan.demo.otika.controller;

import agusramdan.demo.otika.model.dto.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping()
@AllArgsConstructor
public class UnAunthController {

    JdbcUserDetailsManager jdbcUserDetailsManager;
    PasswordEncoder passwordEncoder;
    @GetMapping(value = {"/forgot-password-email.html","/forgot-password-email"})
    public ModelMap forgotPasswordEmail(String pageName) {

        return new ModelMap();
    }

    @GetMapping(value = "/register")
    public String registerForm(Model model) {

        model.addAttribute("register", new RegisterDto());

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(RegisterDto register, Model model) {

        if(!jdbcUserDetailsManager.userExists(register.getEmail())){

            val user = new User(register.getEmail(),passwordEncoder.encode(register.getPassword()),
                    Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
            jdbcUserDetailsManager.createUser(user);
        }
        model.addAttribute("register", register);

        return "register";
    }


    // Login form
    @GetMapping(value = {"/login","/login.html"})
    public String login() {
        return "otika/login";
    }

    // Login form with error
    @GetMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "otika/login";
    }

    @GetMapping(value = {"/forgot-password","/forgot-password.html"})
    public String forgetPassword() {
        return "otika/forgot-password";
    }

    @PostMapping(value = "/forgot-password")
    public String register(@RequestParam String email, Model model) {

        if(jdbcUserDetailsManager.userExists(email)){
            model.addAttribute("success", "Send request to email "+email);
        }else {
            model.addAttribute("errors", "User not found");
        }


        return "otika/forgot-password";
    }
}
