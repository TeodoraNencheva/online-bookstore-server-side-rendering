package bg.softuni.onlinebookstore.util;

import bg.softuni.onlinebookstore.user.BookstoreUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class TestUserDataService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new BookstoreUserDetails(1L,
                "first_name",
                "last_name",
                username,
                "1234",
                Collections.emptyList());
    }
}
