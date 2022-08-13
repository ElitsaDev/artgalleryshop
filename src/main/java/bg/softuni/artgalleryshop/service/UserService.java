package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.exceptions.UserNotFoundException;
import bg.softuni.artgalleryshop.model.dto.UserRegisterDTO;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.model.view.UserViewModel;
import bg.softuni.artgalleryshop.repository.PasswordTokenRepository;
import bg.softuni.artgalleryshop.repository.UserRepository;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.model.mapper.UserMapper;
import bg.softuni.artgalleryshop.repository.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserMapper userMapper;
  private final UserDetailsService userDetailsService;
  private final EmailService emailService;
  private final PasswordTokenRepository passwordTokenRepository;

  private final UserRoleRepository userRoleRepository;

  public UserService(UserRepository userRepository,
                     PasswordEncoder passwordEncoder,
                     UserMapper userMapper,
                     UserDetailsService userDetailsService,
                     EmailService emailService, PasswordTokenRepository passwordTokenRepository, UserRoleRepository userRoleRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.userMapper = userMapper;
    this.userDetailsService = userDetailsService;
    this.emailService = emailService;

    this.passwordTokenRepository = passwordTokenRepository;
    this.userRoleRepository = userRoleRepository;
  }

  public void registerAndLogin(UserRegisterDTO userRegisterDTO,
                               Locale preferredLocale) {
    if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
      throw new RuntimeException("passwords.match");
    }

    Optional<UserEntity> byEmail = this.userRepository.findByEmail(userRegisterDTO.getEmail());

    if (byEmail.isPresent()) {
      throw new RuntimeException("email.used");
    }

    UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
    newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

    Optional<UserEntity> firstUser = this.userRepository.findById(1l);

    if(firstUser.isPresent() && userRepository.count() == 1){
      firstUser.get().setUserRoles(List.of(
              userRoleRepository.findByUserRole(UserRoleEnum.ADMIN).orElseThrow(),
              userRoleRepository.findByUserRole(UserRoleEnum.MODERATOR).orElseThrow(),
              userRoleRepository.findByUserRole(UserRoleEnum.USER).orElseThrow()
      ));
      newUser.setUserRoles(List.of(userRoleRepository.findByUserRole(UserRoleEnum.USER).orElseThrow()));

    } else{
      newUser.setUserRoles(List.of(userRoleRepository.findByUserRole(UserRoleEnum.USER).orElseThrow()));
    }

    this.userRepository.save(newUser);
    login(newUser);
    emailService.sendRegistrationEmail(newUser.getEmail(),
        newUser.getFirstName() + " " + newUser.getLastName(),
        preferredLocale);
  }

  private void login(UserEntity userEntity) {

    UserDetails userDetails =
        userDetailsService.loadUserByUsername(userEntity.getUsername());
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

  public UserEntity getUserByUsername(String username) {
    return this.userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
  }

    public List<UserViewModel> findAll() {
      List<UserEntity> users = this.userRepository.findAll();
      List<UserViewModel> userViewModels = new ArrayList<>();

      for (UserEntity user : users) {
        UserViewModel userViewModel = this.userMapper.map(user, UserViewModel.class);
        userViewModels.add(userViewModel);
      }

      return userViewModels;
    }

  public void deleteUser(Long id) {
    this.userRepository.deleteById(id);
  }

  public List<UserViewModel> findByNameCriteria(String firstName) {
    List<UserEntity> users = this.userRepository.findByFirstName(firstName);
    if (users.isEmpty()) {
      return new ArrayList<>();
    } else {
      return users.stream().map(u -> this.userMapper.map(u, UserViewModel.class)).collect(Collectors.toList());
    }
  }

  public void changeCurrentUserRole(Long id) {
      //todo
  }

    public UserEntity findUserByEmail(String userEmail) {
    return this.userRepository.findByEmail(userEmail).orElseThrow(UserNotFoundException::new);
    }

    public void adminAddUserRoles() {
        if(userRepository.count() == 1){

        UserEntity user =  userRepository.findById(1l).get().setUserRoles(List.of(
                  userRoleRepository.findByUserRole(UserRoleEnum.ADMIN).orElseThrow(),
                  userRoleRepository.findByUserRole(UserRoleEnum.MODERATOR).orElseThrow(),
                  userRoleRepository.findByUserRole(UserRoleEnum.USER).orElseThrow()
          ));

        userRepository.saveAndFlush(user);

        }
    }
}
