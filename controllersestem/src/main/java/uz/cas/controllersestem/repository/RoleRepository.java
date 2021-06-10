package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.controllersestem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
