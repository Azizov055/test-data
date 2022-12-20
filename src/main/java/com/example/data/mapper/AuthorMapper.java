package com.example.data.mapper;

import com.example.data.dto.AuthorDto;
import com.example.data.entity.Author;
import com.example.data.request.AuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AuthorMapper {

    @Mapping(source = "id", target = "authorId")
    AuthorDto mapEntityToDto(Author author);

    List<AuthorDto> mapEntityListToDtoList(List<Author> authors);

    @Mapping(source = "id", target = "authorId")
    void mapEntityToExistingDto(Author author, @MappingTarget AuthorDto authorDto);

    @Mapping(target = "id", ignore = true)
    void mapRequestToExistingEntity(AuthorRequest authorRequest, @MappingTarget Author author);

}
