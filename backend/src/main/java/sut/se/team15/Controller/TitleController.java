package sut.se.team15.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import sut.se.team15.Entity.*;
import sut.se.team15.Repository.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TitleController {

    @Autowired
    private final TitleRepository titleRepository;

    public TitleController(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @GetMapping("/Title")
    public Collection<Title> titles() {
        return titleRepository.findAll().stream().collect(Collectors.toList());
    }

}