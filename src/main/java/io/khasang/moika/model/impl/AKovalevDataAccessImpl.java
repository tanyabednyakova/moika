package io.khasang.moika.model.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import io.khasang.moika.model.AKovalevDataAccess;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;

@Component
public class AKovalevDataAccessImpl {

}