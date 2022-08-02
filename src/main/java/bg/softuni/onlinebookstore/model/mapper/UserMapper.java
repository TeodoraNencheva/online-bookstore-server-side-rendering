package bg.softuni.onlinebookstore.model.mapper;

import bg.softuni.onlinebookstore.model.dto.user.UserRegistrationDTO;
import bg.softuni.onlinebookstore.model.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserEntity userRegistrationDtoToUserEntity(UserRegistrationDTO registerDTO);
}
