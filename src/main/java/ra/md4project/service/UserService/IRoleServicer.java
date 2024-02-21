package ra.md4project.service.UserService;

import ra.md4project.model.user.Role;

public interface IRoleServicer {
    void save(Role role);
    Role findRoleByName(String name);
}
