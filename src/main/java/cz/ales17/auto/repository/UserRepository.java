package cz.ales17.auto.repository;

import cz.ales17.auto.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteByUsernameIs(String username);
}
