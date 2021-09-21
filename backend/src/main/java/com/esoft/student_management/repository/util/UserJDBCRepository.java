package com.esoft.student_management.repository.util;

import java.util.List;
import java.util.Map;

public interface UserJDBCRepository {


    List<Map<String,Object>> getUserType(String date);


}
