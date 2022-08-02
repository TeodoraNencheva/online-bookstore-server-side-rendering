package bg.softuni.onlinebookstore.model.mapper;

import bg.softuni.onlinebookstore.model.dto.author.AddNewAuthorDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorDetailsDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorNameDTO;
import bg.softuni.onlinebookstore.model.dto.author.AuthorOverviewDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorOverviewDTO authorEntityToAuthorOverviewDTO(AuthorEntity author);

    AuthorDetailsDTO authorEntityToAuthorDetailsDTO(AuthorEntity author);

    AuthorNameDTO authorEntityToAuthorNameDTO(AuthorEntity author);

    AddNewAuthorDTO authorEntityToAddNewAuthorDTO(AuthorEntity author);
}
