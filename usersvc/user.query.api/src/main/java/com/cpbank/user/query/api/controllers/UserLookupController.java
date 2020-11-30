package com.cpbank.user.query.api.controllers;

import com.cpbank.user.query.api.dtos.UserLookupResponseDto;
import com.cpbank.user.query.api.queries.FindAllUserQueries;
import com.cpbank.user.query.api.queries.FindUserByIdQuery;
import lombok.extern.apachecommons.CommonsLog;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CommonsLog
@RestController
@RequestMapping("/api/v1/user/lookup")
public class UserLookupController {
    private QueryGateway queryGateway;

    @Autowired
    public UserLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("")
    public ResponseEntity<UserLookupResponseDto> getAllUsers() {
        try {
            UserLookupResponseDto userLookupResponseDto = queryGateway.query(new FindAllUserQueries(), ResponseTypes.instanceOf(UserLookupResponseDto.class)).join();
            if (userLookupResponseDto == null) {
                return new ResponseEntity<>(new UserLookupResponseDto(new ArrayList<>()), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userLookupResponseDto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex);
            return new ResponseEntity<>(new UserLookupResponseDto(new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserLookupResponseDto> getUserById(@PathVariable(value = "id") String id) {
        try {
            FindUserByIdQuery findUserByIdQuery = new FindUserByIdQuery();
            findUserByIdQuery.setId(id);
            UserLookupResponseDto userLookupResponseDto = queryGateway.query(findUserByIdQuery, ResponseTypes.instanceOf(UserLookupResponseDto.class)).join();
            if (userLookupResponseDto == null) {
                return new ResponseEntity<>(new UserLookupResponseDto(new ArrayList<>()), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(userLookupResponseDto, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex);
            return new ResponseEntity<>(new UserLookupResponseDto(new ArrayList<>()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
