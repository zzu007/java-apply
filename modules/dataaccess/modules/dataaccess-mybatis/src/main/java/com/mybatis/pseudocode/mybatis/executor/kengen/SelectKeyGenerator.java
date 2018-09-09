package com.mybatis.pseudocode.mybatis.executor.kengen;


import com.mybatis.pseudocode.mybatis.executor.Executor;
import com.mybatis.pseudocode.mybatis.executor.ExecutorException;
import com.mybatis.pseudocode.mybatis.mapping.MappedStatement;
import com.mybatis.pseudocode.mybatis.session.Configuration;
import com.mybatis.pseudocode.mybatis.session.ExecutorType;
import com.mybatis.pseudocode.mybatis.session.RowBounds;
import org.apache.ibatis.reflection.MetaObject;

import java.sql.Statement;
import java.util.List;


public class SelectKeyGenerator implements KeyGenerator
{
    public static final String SELECT_KEY_SUFFIX = "!selectKey";
    private final boolean executeBefore;
    private final MappedStatement keyStatement;

    public SelectKeyGenerator(MappedStatement keyStatement, boolean executeBefore)
    {
        this.executeBefore = executeBefore;
        this.keyStatement = keyStatement;
    }

    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter)
    {
        if (this.executeBefore)
            processGeneratedKeys(executor, ms, parameter);
    }

    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter)
    {
        if (!this.executeBefore)
            processGeneratedKeys(executor, ms, parameter);
    }

    private void processGeneratedKeys(Executor executor, MappedStatement ms, Object parameter)
    {
        try {
            if ((parameter != null) && (this.keyStatement != null) && (this.keyStatement.getKeyProperties() != null)) {
                String[] keyProperties = this.keyStatement.getKeyProperties();
                Configuration configuration = ms.getConfiguration();
                //MetaObject metaParam = configuration.newMetaObject(parameter);
                MetaObject metaParam =null;
                if (keyProperties != null)
                {
                    Executor keyExecutor = configuration.newExecutor(executor.getTransaction(), ExecutorType.SIMPLE);
                    List values = keyExecutor.query(this.keyStatement, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
                    if (values.size() == 0)
                        throw new ExecutorException("SelectKey returned no data.");
                    if (values.size() > 1) {
                        throw new ExecutorException("SelectKey returned more than one value.");
                    }
                    //MetaObject metaResult = configuration.newMetaObject(values.get(0));
                    MetaObject metaResult =null;
                    if (keyProperties.length == 1) {
                        if (metaResult.hasGetter(keyProperties[0])) {
                            setValue(metaParam, keyProperties[0], metaResult.getValue(keyProperties[0]));
                        }
                        else
                        {
                            setValue(metaParam, keyProperties[0], values.get(0));
                        }
                    }
                    else  {
                        handleMultipleProperties(keyProperties, metaParam, metaResult);
                    }
                }
            }
        }
        catch (ExecutorException e)
        {
            throw e;
        } catch (Exception e) {
            throw new ExecutorException("Error selecting key or setting result to parameter object. Cause: " + e, e);
        }
    }

    private void handleMultipleProperties(String[] keyProperties, MetaObject metaParam, MetaObject metaResult)
    {
        String[] keyColumns = this.keyStatement.getKeyColumns();

        if ((keyColumns == null) || (keyColumns.length == 0))
        {
            for (String keyProperty : keyProperties)
                setValue(metaParam, keyProperty, metaResult.getValue(keyProperty));
        }
        else {
            if (keyColumns.length != keyProperties.length) {
                throw new ExecutorException("If SelectKey has key columns, the number must match the number of key properties.");
            }
            for (int i = 0; i < keyProperties.length; i++)
                setValue(metaParam, keyProperties[i], metaResult.getValue(keyColumns[i]));
        }
    }

    private void setValue(MetaObject metaParam, String property, Object value)
    {
        if (metaParam.hasSetter(property))
            metaParam.setValue(property, value);
        else
            throw new ExecutorException("No setter found for the keyProperty '" + property + "' in " + metaParam.getOriginalObject().getClass().getName() + ".");
    }
}
