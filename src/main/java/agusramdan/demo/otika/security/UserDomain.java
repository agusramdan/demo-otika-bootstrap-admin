package agusramdan.demo.otika.security;

import agusramdan.demo.otika.model.domain.BaseDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserDomain {

    @Id
    private String username;
    private String password;
    private boolean enabled;

    @ElementCollection (fetch = FetchType.EAGER)// 1
    @CollectionTable(name = "authorities", joinColumns = @JoinColumn(name = "username") ) // 2
    @Column(name = "authority") // 3
    private Set<String> authorities;
}
