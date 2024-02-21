package ra.md4project.repository.AccountRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ra.md4project.model.user.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Role findByRoleName(String name);
}
