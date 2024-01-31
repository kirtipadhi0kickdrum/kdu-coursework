package com.example.springjdbc.serviceimpl;

import com.example.springjdbc.entity.Shift;
import com.example.springjdbc.entity.ShiftType;
import com.example.springjdbc.entity.ShiftUser;
import com.example.springjdbc.entity.User;
import com.example.springjdbc.helper.SaveAllEntitiesRequest;
import com.example.springjdbc.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TenantServiceImpl implements TenantService {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TenantServiceImpl(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveShiftType(UUID tenantId, ShiftType shiftType) {
        String sql = "INSERT INTO shift_types (id, uq_name, description, active, created_at, updated_at, time_zone, tenant_id) " +
                "VALUES (:id, :uqName, :description, :active, :createdAt, :updatedAt, :timeZone, :tenantId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", shiftType.getId())
                .addValue("uqName", shiftType.getName())
                .addValue("description", shiftType.getDescription())
                .addValue("active", shiftType.isActive())
                .addValue("createdAt", shiftType.getCreatedAt())
                .addValue("updatedAt", shiftType.getUpdatedAt())
                .addValue("timeZone", shiftType.getTimeZone())
                .addValue("tenantId", tenantId);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void saveShift(UUID tenantId, Shift shift) {
        String sql = "INSERT INTO shifts (id, shift_type_id, name, date_start, date_end, time_start, time_end, created_at, updated_at, time_zone, tenant_id) " +
                "VALUES (:id, :shiftTypeId, :name, :dateStart, :dateEnd, :timeStart, :timeEnd, :createdAt, :updatedAt, :timeZone, :tenantId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", shift.getId())
                .addValue("shiftTypeId", shift.getShiftTypeId())
                .addValue("name", shift.getName())
                .addValue("dateStart", shift.getDateStart())
                .addValue("dateEnd", shift.getDateEnd())
                .addValue("timeStart", shift.getTimeStart())
                .addValue("timeEnd", shift.getTimeEnd())
                .addValue("createdAt", shift.getCreatedAt())
                .addValue("updatedAt", shift.getUpdatedAt())
                .addValue("timeZone", shift.getTimeZone())
                .addValue("tenantId", tenantId);  // Corrected order

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void saveUser(UUID tenantId, User user) {
        String sql = "INSERT INTO users (id, username, loggedin, time_zone, tenant_id) " +
                "VALUES (:id, :username, :loggedin, :timeZone, :tenantId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("username", user.getUsername())
                .addValue("loggedin", user.getLoggedIn())
                .addValue("timeZone", user.getTimeZone())
                .addValue("tenantId", tenantId);  // Corrected order

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void saveShiftUser(UUID tenantId, ShiftUser shiftUser) {
        String sql = "INSERT INTO shift_users (id, shift_id, user_id, tenant_id) " +
                "VALUES (:id, :shiftId, :userId, :tenantId)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", shiftUser.getId())
                .addValue("shiftId", shiftUser.getShiftId())
                .addValue("userId", shiftUser.getUserId())
                .addValue("tenantId", tenantId);  // Corrected order

        jdbcTemplate.update(sql, params);
    }


    @Transactional
    public void saveAllEntities(UUID tenantId, SaveAllEntitiesRequest request) {
        ShiftType shiftType = request.getShiftType();
        Shift shift = request.getShift();
        User user = request.getUser();
        ShiftUser shiftUser = request.getShiftUser();

        saveShiftType(tenantId, shiftType);
        saveShift(tenantId, shift);
        saveUser(tenantId, user);
        saveShiftUser(tenantId, shiftUser);
    }

    @Override
    public List<ShiftType> getShiftTypesByTenant(UUID tenantId) {
        String sql = "SELECT * FROM shift_types WHERE tenant_id = :tenantId";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("tenantId", tenantId);
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(ShiftType.class));
    }

    @Override
    public List<Shift> getShiftsByTenant(UUID tenantId) {
        String sql = "SELECT * FROM shifts WHERE tenant_id = :tenantId";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("tenantId", tenantId);
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(Shift.class));

    }

    @Override
    public List<User> getUsersByTenant(UUID tenantId) {
        String sql = "SELECT * FROM users WHERE tenant_id = :tenantId";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("tenantId", tenantId);
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<ShiftUser> getShiftUsersByTenant(UUID tenantId) {
        String sql = "SELECT * FROM shift_users WHERE tenant_id = :tenantId";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("tenantId", tenantId);
        return jdbcTemplate.query(sql, params, new BeanPropertyRowMapper<>(ShiftUser.class));
    }

    @Override
    public void updateUserInformation(UUID userId, String username) {
        String sql = "UPDATE users SET username = :username WHERE id = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("userId", userId)
                .addValue("username", username);

        jdbcTemplate.update(sql, params);
    }



}
