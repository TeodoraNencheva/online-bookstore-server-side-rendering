package bg.softuni.onlinebookstore.model.mapper;

import bg.softuni.onlinebookstore.model.dto.book.AddNewBookDTO;
import bg.softuni.onlinebookstore.model.dto.book.BookDetailsDTO;
import bg.softuni.onlinebookstore.model.entity.AuthorEntity;
import bg.softuni.onlinebookstore.model.entity.BookEntity;
import bg.softuni.onlinebookstore.model.dto.book.BookOverviewDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.awt.print.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
    @Mapping(source = "genre.name", target = "genre")
    @Mapping(source = "picture.url", target = "picture")
    BookDetailsDTO bookEntityToBookDetailsDTO(BookEntity bookEntity);

    @Mapping(source = "genre.name", target = "genre")
    @Mapping(source = "picture.url", target = "picture")
    BookOverviewDTO bookEntityToBookOverviewDTO(BookEntity bookEntity);
}

