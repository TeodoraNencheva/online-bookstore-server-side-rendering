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
    BookDetailsDTO bookEntityToBookDetailsDTO(BookEntity bookEntity);

    @Mapping(source = "genre.name", target = "genre")
    BookOverviewDTO bookEntityToBookOverviewDTO(BookEntity bookEntity);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "genre.id", target = "genreId")
    AddNewBookDTO bookEntityToAddNewBookDTO(BookEntity bookEntity);
}

