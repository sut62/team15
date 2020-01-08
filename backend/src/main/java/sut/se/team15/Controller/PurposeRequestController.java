package sut.se.team15.Controller;

import sut.se.team15.Entity.PurposeRequest;
import sut.se.team15.Repository.PurposeRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PurposeRequestController {

    @Autowired
	private final PurposeRequestRepository purposeRequestRepository;

    public PurposeRequestController(PurposeRequestRepository purposeRequestRepository) {
        this.purposeRequestRepository = purposeRequestRepository;
    }

    @GetMapping("/PurposeRequest")
    public Collection<PurposeRequest> PurposeRequests() {
        return purposeRequestRepository.findAll().stream().collect(Collectors.toList());
    }

}