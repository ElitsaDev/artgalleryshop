package bg.softuni.artgalleryshop.service;

import bg.softuni.artgalleryshop.model.entity.UserRoleEntity;
import bg.softuni.artgalleryshop.model.enums.UserRoleEnum;
import bg.softuni.artgalleryshop.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public void initializeRoles() {
        if(userRoleRepository.count()!=0){
            return;
        }

        Arrays.stream(UserRoleEnum.values()).map(enumRole ->{
            UserRoleEntity role = new UserRoleEntity();
            role.setUserRole(enumRole);
            return role;
        }).forEach(userRoleRepository::save);
    }
}
