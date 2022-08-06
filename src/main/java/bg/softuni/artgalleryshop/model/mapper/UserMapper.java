package bg.softuni.artgalleryshop.model.mapper;

import bg.softuni.artgalleryshop.model.dto.UserRegisterDTO;
import bg.softuni.artgalleryshop.model.entity.UserEntity;
import bg.softuni.artgalleryshop.model.view.UserViewModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "active.id", constant = "true")
  UserEntity userDtoToUserEntity(UserRegisterDTO registerDTO);

  UserViewModel map(UserEntity user, Class<UserViewModel> userViewModelClass);
}


