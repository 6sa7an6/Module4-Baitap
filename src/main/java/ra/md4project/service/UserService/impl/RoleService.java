package ra.md4project.service.UserService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.md4project.model.user.Role;
import ra.md4project.repository.AccountRepository.RoleRepository;
import ra.md4project.service.UserService.IRoleServicer;
@Service
public class RoleService implements IRoleServicer {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByRoleName(name);
    }
}
