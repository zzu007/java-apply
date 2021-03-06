package com.mybatis.pseudocode.mybatis.type;


import com.mybatis.pseudocode.mybatis.mapping.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectTypeHandler extends BaseTypeHandler<Object>
{
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException
    {
        ps.setObject(i, parameter);
    }

    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException
    {
        return rs.getObject(columnName);
    }

    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException
    {
        return rs.getObject(columnIndex);
    }

    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException
    {
        return cs.getObject(columnIndex);
    }
}
