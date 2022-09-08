package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.entity.UserEntity;
import bg.softuni.onlinebookstore.model.entity.UserRoleEntity;
import bg.softuni.onlinebookstore.repositories.UserRepository;
import bg.softuni.onlinebookstore.user.BookstoreUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

public class BookstoreUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public BookstoreUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = userRepository.findByEmail(username);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User with email " + username + " not found!");
        }

        return this.map(userOpt.get());
    }

    private UserDetails map(UserEntity userEntity) {

        return new BookstoreUserDetails(
                userEntity.getId(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.isAccountVerified(),
                userEntity.
                        getRoles().
                        stream().
                        map(this::map).collect(Collectors.toList())
        );
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.getName().name());
    }


}
