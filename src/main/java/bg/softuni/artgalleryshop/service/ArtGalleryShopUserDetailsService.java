package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.user.ArtGalleryShopUserDetails;
import bg.softuni.artgalleryshop.repository.UserRepository;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.model.entity.UserRoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ArtGalleryShopUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public ArtGalleryShopUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.
        findByUsername(username).
        map(this::map).
        orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " not found!"));
  }

  private UserDetails map(UserEntity userEntity) {

    return new ArtGalleryShopUserDetails(
        userEntity.getId(),
        userEntity.getUsername(),
        userEntity.getPassword(),
        userEntity.getFirstName(),
        userEntity.getLastName(),
        userEntity.getEmail(),

        userEntity.
            getUserRoles().
            stream().
            map(this::map).
            toList()
    );
  }

  private GrantedAuthority map(UserRoleEntity userRole) {
    return new SimpleGrantedAuthority("ROLE_" +
        userRole.
            getUserRole().name());
  }
}
