package bg.softuni.onlinebookstore.service;

import bg.softuni.onlinebookstore.model.dto.book.AddBookToCartDTO;
import bg.softuni.onlinebookstore.model.dto.user.UserRegistrationDTO;
import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.model.entity.UserEntity;
import bg.softuni.onlinebookstore.model.entity.UserRoleEntity;
import bg.softuni.onlinebookstore.model.enums.UserRoleEnum;
import bg.softuni.onlinebookstore.model.mapper.UserMapper;
import bg.softuni.onlinebookstore.repositories.BookRepository;
import bg.softuni.onlinebookstore.repositories.UserRepository;
import bg.softuni.onlinebookstore.repositories.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final UserRoleRepository userRoleRepository;
    private final BookRepository bookRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserRoleRepository userRoleRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userRoleRepository = userRoleRepository;
        this.bookRepository = bookRepository;
    }

    public void registerAndLogin(UserRegistrationDTO userRegistrationDTO) {

        UserEntity newUser = userMapper.userRegistrationDtoToUserEntity(userRegistrationDTO);
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        Optional<UserRoleEntity> userRole = this.userRoleRepository.findByName(UserRoleEnum.USER);
        newUser.addRole(userRole.get());
        this.userRepository.save(newUser);
        login(newUser);
    }


    private void login(UserEntity userEntity) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public boolean addBookToCart(UserDetails userDetails, AddBookToCartDTO bookDTO) {
        Optional<UserEntity> userOpt = userRepository.findByEmail(userDetails.getUsername());
        Optional<BookEntity> bookOpt = bookRepository.findById(bookDTO.getBookId());

        if (userOpt.isEmpty() || bookOpt.isEmpty()) {
            return false;
        }

        UserEntity user = userOpt.get();
        user.addToCart(bookOpt.get(), bookDTO.getQuantity());
        userRepository.save(user);
        return true;
    }


    @Transactional
    public Map<BookEntity, Integer> getUserCart(UserDetails userDetails) {
        return getUser(userDetails).getCart();
    }

    public void removeItemFromCart(Long bookId, UserDetails userDetails) {
        UserEntity user = getUser(userDetails);
        BookEntity book = bookRepository.findById(bookId).get();
        user.removeFromCart(book);
        userRepository.save(user);
    }

    public void removeAllItemsFromCart(UserDetails userDetails) {
        UserEntity user = getUser(userDetails);
        user.emptyCart();
        userRepository.save(user);
    }

    public UserEntity getUser(UserDetails userDetails) {
        return userRepository.findByEmail(userDetails.getUsername()).get();
    }

    public boolean isAdmin(String username) {
        return userRepository.findByEmail(username).get()
                .getRoles()
                .stream().anyMatch(r -> r.getName().equals(UserRoleEnum.ADMIN));
    }
}
