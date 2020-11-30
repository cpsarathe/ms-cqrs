package com.cpbank.user.query.api.handlers;

import com.cpbank.user.query.api.dtos.UserLookupResponseDto;
import com.cpbank.user.query.api.queries.FindAllUserQueries;
import com.cpbank.user.query.api.queries.FindUserByIdQuery;
import com.cpbank.user.query.api.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookupResponseDto getUserById(FindUserByIdQuery findUserByIdQuery);
    UserLookupResponseDto searchUsers(SearchUsersQuery searchUsersQuery);
    UserLookupResponseDto getAllUsers(FindAllUserQueries findAllUserQueries);
}
