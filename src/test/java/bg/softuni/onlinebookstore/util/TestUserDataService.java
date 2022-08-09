package bg.softuni.onlinebookstore.util;

import bg.softuni.onlinebookstore.user.BookstoreUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TestUserDataService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.equals("user@example.com")) {
            return new BookstoreUserDetails(1L,
                    "first_name",
                    "last_name",
                    username,
                    "1234",
                    List.of(new SimpleGrantedAuthority("ROLE_USER")));
        }

        return new BookstoreUserDetails(1L,
                "first_name",
                "last_name",
                username,
                "1234",
                List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
