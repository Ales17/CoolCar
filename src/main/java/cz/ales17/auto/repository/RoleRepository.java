package cz.ales17.auto.repository;

import cz.ales17.auto.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Byte> {
    Role findRoleByName(String name);
}
