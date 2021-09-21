package com.esoft.student_management.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class GeneralResultsetExtracor implements ResultSetExtractor<List<Map<String, Object>>> {

    private static final Logger logger = LoggerFactory.getLogger(GeneralResultsetExtracor.class);

    //expected object list
    private String[] colSet;

    //extracted result
    private List<Map<String, Object>> extractedResult;

    public GeneralResultsetExtracor(String[] colSet) {
        this.colSet = colSet;
        this.extractedResult = new ArrayList<>();
    }


    @Override
    public List<Map<String, Object>> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        while (resultSet.next()) {
            HashMap<String, Object> row = new HashMap<>();
            Arrays.stream(this.colSet).forEach(column -> {
                boolean integerFiled = false;
                if (column.indexOf(":") > 1) {
                    String[] atvl = column.split(":");
                    if (atvl.length > 1) {
                        column = atvl[0];
                        integerFiled = true;
                    }
                }
                try {
                    row.put(column, integerFiled ? resultSet.getInt(column) : resultSet.getObject(column));
                } catch (SQLException throwables) {
                    GeneralResultsetExtracor.logger.error(throwables.getMessage());
                }
            });
            this.extractedResult.add(row);
        }
        return this.extractedResult;
    }
}
