package br.com.dextra.javabootcamp.MentorMatch.controllers;

import br.com.dextra.javabootcamp.MentorMatch.models.MentorResponse;
import br.com.dextra.javabootcamp.MentorMatch.services.MentorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api
@RestController
@RequestMapping("api/v1/mentor")
public class MentorController {

    @Autowired
    MentorService mentorService;

    @ApiOperation(value = "Listagem de mentoras")
    @ApiResponses({
            @ApiResponse( code= HttpServletResponse.SC_OK, message = "Listagem de mentoras", response = List.class),
            @ApiResponse( code= HttpServletResponse.SC_NO_CONTENT, message = "Não foram encontradas mentoras", response = List.class)
    })

    @GetMapping
    public ResponseEntity<List<MentorResponse>> list(){
        List<MentorResponse> returnedList = mentorService.list();
        if(returnedList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(returnedList);
    }

}
