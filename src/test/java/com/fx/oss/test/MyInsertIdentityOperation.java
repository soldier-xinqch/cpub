/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fx.oss.test;


import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.dataset.*;
import org.dbunit.dataset.filter.IColumnFilter;
import org.dbunit.operation.AbstractOperation;
import org.dbunit.operation.CompositeOperation;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.operation.ExclusiveTransactionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import org.dbunit.ext.mssql.InsertIdentityOperation;

/**
 * This class disable the MS SQL Server automatic identifier generation for
 * the execution of inserts.
 * <p>
 * If you are using the Microsoft driver (i.e.
 * <code>com.microsoft.jdbc.sqlserver.SQLServerDriver</code>), you'll need to
 * use the <code>SelectMethod=cursor</code> parameter in the JDBC connection
 * string. Your databaseUrl would look something like the following:
 * <p>
 * <code>jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=mydb;SelectMethod=cursor</code>
 * <p>
 * Thanks to Jeremy Stein who has submitted multiple patches.
 *
 * @author Manuel Laflamme
 * @author Eric Pugh
 * @version $Revision: 1.7 $
 * @since Apr 9, 2002
 * 
 * 黄维勇修改，，由于在执行数据插入时，破坏了事务一致性，造成测试数据无法回滚。
 */
public class MyInsertIdentityOperation extends AbstractOperation{
    public static final String PROPERTY_IDENTITY_COLUMN_FILTER ="http://www.dbunit.org/properties/mssql/identityColumnFilter";

    public static final DatabaseOperation INSERT =new InsertIdentityOperation(DatabaseOperation.INSERT);

    public static final DatabaseOperation CLEAN_INSERT =
            new CompositeOperation(DatabaseOperation.DELETE_ALL,
                    new InsertIdentityOperation(DatabaseOperation.INSERT));

    public static final DatabaseOperation REFRESH =
            new InsertIdentityOperation(DatabaseOperation.REFRESH);

    private static final IColumnFilter DEFAULT_IDENTITY_FILTER = new IColumnFilter()
    {
        public boolean accept(String tableName, Column column)
        {
            return column.getSqlTypeName().endsWith("identity");
        }
    };


    private final DatabaseOperation _operation;
    
    private boolean inTransaction=false;

    /**
     * Creates a new InsertIdentityOperation object that decorates the
     * specified operation.
     */
    public MyInsertIdentityOperation(DatabaseOperation operation)
    {
        _operation = operation;
    }

    private boolean hasIdentityColumn(ITableMetaData metaData, IDatabaseConnection connection)
            throws DataSetException
    {
        DatabaseConfig config = connection.getConfig();
        IColumnFilter identityFilter = (IColumnFilter)config.getProperty(
                PROPERTY_IDENTITY_COLUMN_FILTER);
        if (identityFilter == null)
        {
            identityFilter = DEFAULT_IDENTITY_FILTER;
        }

        // Verify if there is at least one identity column
        Column[] columns = metaData.getColumns();
        for (int i = 0; i < columns.length; i++)
        {
            if (identityFilter.accept(metaData.getTableName(), columns[i]))
            {
                return true;
            }
        }

        return false;
    }

    ////////////////////////////////////////////////////////////////////////////
    // DatabaseOperation class

    public void execute(IDatabaseConnection connection, IDataSet dataSet)
            throws DatabaseUnitException, SQLException
    {
        Connection jdbcConnection = connection.getConnection();
        Statement statement = jdbcConnection.createStatement();

        try
        {
            IDataSet databaseDataSet = connection.createDataSet();
            

            // INSERT_IDENTITY need to be enabled/disabled inside the
            // same transaction
            if(!this.isInTransaction()){
                if (jdbcConnection.getAutoCommit() == false)
                {
                    throw new ExclusiveTransactionException();
                }
                jdbcConnection.setAutoCommit(false);
            }

            // Execute decorated operation one table at a time
            ITableIterator iterator = dataSet.iterator();
            while(iterator.next())
            {
                ITable table = iterator.getTable();
                String tableName = table.getTableMetaData().getTableName();

                ITableMetaData metaData =
                        databaseDataSet.getTableMetaData(tableName);

                // enable identity insert
                boolean hasIdentityColumn = hasIdentityColumn(metaData, connection);

                if (hasIdentityColumn)
                {
                    StringBuffer sqlBuffer = new StringBuffer(128);
                    sqlBuffer.append("SET IDENTITY_INSERT ");
                    sqlBuffer.append(getQualifiedName(connection.getSchema(),
                            metaData.getTableName(), connection));
                    sqlBuffer.append(" ON");
                    statement.execute(sqlBuffer.toString());
                }

                try
                {
                    _operation.execute(connection, new DefaultDataSet(table));
                }
                finally
                {
                    // disable identity insert
                    if (hasIdentityColumn)
                    {
                        StringBuffer sqlBuffer = new StringBuffer(128);
                        sqlBuffer.append("SET IDENTITY_INSERT ");
                        sqlBuffer.append(getQualifiedName(connection.getSchema(),
                                metaData.getTableName(), connection));
                        sqlBuffer.append(" OFF");
                        statement.execute(sqlBuffer.toString());
                    }
                    //weiyong huang modified on 2011/3/23
                    if(!this.isInTransaction())
                        jdbcConnection.commit();
                }
            }
        }
        finally
        {
            if(!this.isInTransaction())
                jdbcConnection.setAutoCommit(true);
            statement.close();
        }
    }

    public boolean isInTransaction() {
        return inTransaction;
    }

    public void setInTransaction(boolean inTransaction) {
        this.inTransaction = inTransaction;
    }
    
    
}