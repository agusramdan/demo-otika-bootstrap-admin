package agusramdan.demo.otika.controller;


import agusramdan.demo.otika.model.domain.Users;
import agusramdan.demo.otika.model.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@Controller
@RequestMapping("/idm/users")
public class UsersController  extends CRUDController<Users,String> {

    @Getter
    private UsersRepository repository;

    @Override
    protected Users prepareAdd() {
        return new Users();
    }

    @Override
    protected void pageSetup() {
        page="idm/users";
        super.pageSetup();
    }
}
