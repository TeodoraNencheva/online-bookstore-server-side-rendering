package bg.softuni.onlinebookstore.model.mapper;

import bg.softuni.onlinebookstore.model.dto.author.AuthorDetailsDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorNameDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorOverviewDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(source = "picture.url", target = "picture")
    AuthorOverviewDTO authorEntityToAuthorOverviewDTO(AuthorEntity author);

    @Mapping(source = "picture.url", target = "picture")
    AuthorDetailsDTO authorEntityToAuthorDetailsDTO(AuthorEntity author);

    AuthorNameDTO authorEntityToAuthorNameDTO(AuthorEntity author);
}
