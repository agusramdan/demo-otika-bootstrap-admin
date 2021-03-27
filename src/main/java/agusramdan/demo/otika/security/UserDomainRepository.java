package agusramdan.demo.otika.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDomainRepository extends CrudRepository<UserDomain,String> {
}
