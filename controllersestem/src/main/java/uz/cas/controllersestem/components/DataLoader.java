package uz.cas.controllersestem.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.cas.controllersestem.entity.Role;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.entity.enums.RoleName;
import uz.cas.controllersestem.repository.RoleRepository;
import uz.cas.controllersestem.repository.UsersRepository;

import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class DataLoader implements CommandLineRunner {

@Autowired
private RoleRepository roleRepository;

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String modeInitial;

    public DataLoader(UsersRepository userRepository, RoleRepository roleRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.usersRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args) throws Exception {

        if (modeInitial.equals("always")) {
            roleRepository.save(new Role(RoleName.admin));
            roleRepository.save(new Role(RoleName.user));
            roleRepository.save(new Role(RoleName.projectControl));
            HashSet<Role> roles = new HashSet<>(roleRepository.findAll());

            usersRepository.save(new Users(
                    "Farrux",
                    "Mamadaliyev",
                    "farrux",
                    passwordEncoder.encode("1234"),
                    "1234",
                    "derictor",
                    roles.stream().filter(role -> role.getRoleName().name()
                            .equals("admin")).collect(Collectors.toSet())));

            usersRepository.save(new Users(
                    "Anvar",
                    "Baxriddinov",
                    "anvar",
                    passwordEncoder.encode("1234"),
                    "1234",
                    "proRector",
                    roles.stream().filter(role -> role.getRoleName().name()
                            .equals("proRector")).collect(Collectors.toSet())));

        }
    }
}
