package uz.cas.controllersestem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.cas.controllersestem.entity.Role;
import uz.cas.controllersestem.entity.Users;
import uz.cas.controllersestem.entity.enums.RoleName;
import uz.cas.controllersestem.exception.UsernameException;
import uz.cas.controllersestem.payload.ReqLogin;
import uz.cas.controllersestem.payload.ReqUser;
import uz.cas.controllersestem.repository.RoleRepository;
import uz.cas.controllersestem.repository.UsersRepository;
import uz.cas.controllersestem.security.JwtFilter;
import uz.cas.controllersestem.security.JwtProvider;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username dis not found"));
    }


    public ResponseEntity<?> login(ReqLogin reqLogin) {
        boolean byUsername = usersRepository.existsByUsername(reqLogin.getUsername());
        if (byUsername) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            reqLogin.getUsername(),
                            reqLogin.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtProvider.generateToken(authentication);

            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        throw new UsernameException("Username not found!");
    }

    public ResponseEntity<?> addUser(ReqUser reqUser) {
        boolean existsByUsername = usersRepository.existsByUsername(reqUser.getUsername());
        HashSet<Role> roles = new HashSet<>(roleRepository.findAll());
        if (!existsByUsername) {
            Users users = new Users();
            users.setFirstName(reqUser.getFirstName());
            users.setLastName(reqUser.getLastName());
            users.setUsername(reqUser.getUsername());
            users.setPassword(passwordEncoder.encode(reqUser.getPassword()));
            users.setShowPassword(reqUser.getPassword());
            users.setJob(reqUser.getJob());
            users.setRoles(roles.stream().filter(role -> role.getRoleName().name()
                    .equals("user")).collect(Collectors.toSet()));
            usersRepository.save(users);
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(400).body("Bunday username oldin ishlatilgan");
    }

    public ResponseEntity<?> editUser(Integer id, ReqUser reqUser) {
        Optional<Users> byId = usersRepository.findById(id);
        if (byId.isPresent()) {
            Users users = byId.get();
            users.setShowPassword(reqUser.getPassword());
            users.setPassword(passwordEncoder.encode(reqUser.getPassword()));
            users.setUsername(reqUser.getUsername());
            users.setLastName(reqUser.getLastName());
            users.setJob(reqUser.getJob());
            users.setFirstName(reqUser.getFirstName());
            usersRepository.save(users);
            return ResponseEntity.status(200).body("Malumot o'zgartirildi");
        }
        return ResponseEntity.status(500).body("Bunday idli username topilmadi");
    }

    public ResponseEntity<?> deleteUser(Integer id) {
        usersRepository.deleteById(id);
        return ResponseEntity.status(200).body("User o'chirildi");
    }

    public ResponseEntity<?> getAll() {
        Users users = loadUserByUsername(jwtProvider.getUsername());
        if (users.getRoles().stream().findFirst().get().getRoleName() == RoleName.admin) {
            List<Users> all = usersRepository.findAll();
            return ResponseEntity.ok(all);
        }
        return ResponseEntity.ok("Kechirasiz malumotingiz noto'g'ri");
    }

    public ResponseEntity<?> getUser() {
        Users users = loadUserByUsername(jwtProvider.getUsername());
        List<Users> all = usersRepository.findAll();

        List<Object> editUser = new ArrayList<>();
        for (Users users1 : all) {
            if (users1.getRoles().contains(RoleName.gip) || users1.getRoles().contains(RoleName.gip) || users1.getRoles().contains(RoleName.gip))
                editUser.add(users1);
        }
        return ResponseEntity.ok(editUser);
    }

    public ResponseEntity<?> getUser(Integer id) {
        System.out.println(id);
        Optional<Users> byId = usersRepository.findById(id);
        return ResponseEntity.ok(byId.get());
    }
}
