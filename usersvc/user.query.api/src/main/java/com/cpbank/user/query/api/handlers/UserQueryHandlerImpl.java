package com.cpbank.user.query.api.handlers;

import com.cpbank.user.core.models.User;
import com.cpbank.user.query.api.dtos.UserLookupResponseDto;
import com.cpbank.user.query.api.queries.FindAllUserQueries;
import com.cpbank.user.query.api.queries.FindUserByIdQuery;
import com.cpbank.user.query.api.queries.SearchUsersQuery;
import com.cpbank.user.query.api.repositories.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserQueryHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponseDto getUserById(FindUserByIdQuery findUserByIdQuery) {
        User user = userRepository.findById(findUserByIdQuery.getId()).get();
        List<User> users = new ArrayList<>();
        users.add(user);
        UserLookupResponseDto userLookupResponseDto = new UserLookupResponseDto(users);
        return userLookupResponseDto;
    }

    @QueryHandler
    @Override
    public UserLookupResponseDto searchUsers(SearchUsersQuery searchUsersQuery) {
        List<User> users = userRepository.findByFilterRegex(searchUsersQuery.getFilter());
        UserLookupResponseDto userLookupResponseDto = new UserLookupResponseDto(users);
        return userLookupResponseDto;
    }

    @QueryHandler
    @Override
    public UserLookupResponseDto getAllUsers(FindAllUserQueries findAllUserQueries) {
        List<User> users = userRepository.findAll();
        UserLookupResponseDto userLookupResponseDto = new UserLookupResponseDto(users);
        return userLookupResponseDto;
    }
}
