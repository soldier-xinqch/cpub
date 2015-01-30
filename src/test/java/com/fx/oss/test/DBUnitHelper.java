package com.fx.oss.test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;



/**
 * 封装DBUnit的数据访问功能，为单元测试提供Fixture能力。
 * 
 * @author Weiyong Huang
 */
@Component
public class DBUnitHelper {
    
    private String dataFile;
    private boolean loadDataFileFromClassPath=true;
    private IDataSet dataSet;
    private boolean dataFileExisted=false;
    private Boolean mssqlDB=false;
    
    @Autowired
    private DataSource dataSource;
    
    public DBUnitHelper() throws DatabaseUnitException{
        super();
    }
    
    public DBUnitHelper(String dataFile) throws DatabaseUnitException{
        this();
        this.dataFile=dataFile;
    }
    
    private IDatabaseConnection getConn() throws DatabaseUnitException{
        return new DatabaseConnection(DataSourceUtils.getConnection(this.dataSource));
    }

    public String getDataFile() {
        return dataFile;
    }

    public void setDataFile(String dataFile) {
        this.dataFile = dataFile;
    }

    public boolean isLoadDataFileFromClassPath() {
        return loadDataFileFromClassPath;
    }

    public void setLoadDataFileFromClassPath(boolean loadDataFileFromClassPath) {
        this.loadDataFileFromClassPath = loadDataFileFromClassPath;
    }
    
    public IDataSet reLoadDataSet() throws IOException, DataSetException{
        if(this.isLoadDataFileFromClassPath()){
            this.dataSet=new FlatXmlDataSet(new ClassPathResource(this.dataFile).getFile());
        }else{
            this.dataSet=new FlatXmlDataSet(new File(this.dataFile));
        }
        return this.dataSet;
    }
    
    public IDataSet getDataSet() throws IOException, DataSetException{
        if(this.dataSet!=null){
            return this.dataSet;
        }else{
            return this.reLoadDataSet();
        }
    }
    
    public void executeDBOperation(DatabaseOperation operation) throws DatabaseUnitException, SQLException, IOException{
        operation.execute(this.getConn(), this.getDataSet());
    }
    
    public void executeDBOperation(DatabaseOperation operation, IDataSet dataSet) throws DatabaseUnitException, SQLException{
        operation.execute(this.getConn(),dataSet);
    }
    
    public boolean isDataFileExisted(){
        //如果已经获知默认datafile已经存在，不需要再进行检查
        if(this.dataFileExisted){
            return true;
        }
        this.dataFileExisted=isDataFileExisted(this.dataFile);
        return this.dataFileExisted;
    }
    
    public boolean isDataFileExisted(String dataFile){
        return this.isDataFileExisted(dataFile, this.loadDataFileFromClassPath);
    }
    
    public boolean isDataFileExisted(String dataFile,boolean loadFromCalssPath){
        if(loadFromCalssPath){
            try {
                new ClassPathResource(dataFile).getFile();
                return true;
            } catch (IOException ex) {
            	ex.printStackTrace();
                return false;
            }
        }else{
            return new File(dataFile).exists();
        }
    }
    
    public boolean isMSSQL(){
//        if(mssqlDB==null){
//            //ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config/application-test-config.xml");
//            //com.mchange.v2.c3p0.ComboPooledDataSource dsTarget=(com.mchange.v2.c3p0.ComboPooledDataSource)ctx.getBean("dataSourceTarget");
//            //String dsUrl=dsTarget.getJdbcUrl();
//            //if(dsUrl.toLowerCase().indexOf("sqlserver")>=0){
//            //   this.mssqlDB=new Boolean(true); 
//            //}else{
//            //   this.mssqlDB=new Boolean(false); 
//            //}
//        	PropertiesFileReader pReader=new PropertiesFileReader("config/test.properties");
//        	String isSqlServer=pReader.getProperties().get("is.sqlserver");
//        	if(isSqlServer!=null){
//        		this.mssqlDB=Boolean.parseBoolean(isSqlServer);
//        	}else{
//        		this.mssqlDB=new Boolean(false);
//        	}
//            return this.mssqlDB.booleanValue();
//        }
        return mssqlDB;
    }
}
