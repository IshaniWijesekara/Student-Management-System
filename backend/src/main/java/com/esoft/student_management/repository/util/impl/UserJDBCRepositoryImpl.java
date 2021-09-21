package com.esoft.student_management.repository.util.impl;

import com.esoft.student_management.repository.util.UserJDBCRepository;
import com.esoft.student_management.util.GeneralResultsetExtracor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.esoft.student_management.util.QueryRepository.GetUserType;

@Repository
public class UserJDBCRepositoryImpl implements UserJDBCRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getUserType(String username) {
        Map<String,Object> query_param = new HashMap<>();
        query_param.put("username",username);
        List<Map<String,Object>> list = jdbcTemplate.query(GetUserType,query_param,new GeneralResultsetExtracor(
                new String[]{"userType"}
        ));
        return list;
    }
}
