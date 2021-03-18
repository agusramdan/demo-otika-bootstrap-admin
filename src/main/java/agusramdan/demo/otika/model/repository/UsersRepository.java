package agusramdan.demo.otika.model.repository;

import agusramdan.demo.otika.model.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository  extends JpaRepository<Users,String> {
}
