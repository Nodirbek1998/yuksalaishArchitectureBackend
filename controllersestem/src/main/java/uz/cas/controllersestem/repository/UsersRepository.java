package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.controllersestem.entity.Users;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);
}
