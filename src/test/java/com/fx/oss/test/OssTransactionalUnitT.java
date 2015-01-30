package com.fx.oss.test;

import org.dbunit.operation.DatabaseOperation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 支持事务的SHE单元测试基类。
 * 
 * @author Weiyong Huang
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/application-test-config*.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class OssTransactionalUnitT extends AbstractTransactionalJUnit4SpringContextTests{
    @Autowired
    protected DBUnitHelper dbHelper;
    //mssql不能对identity的字段插入值，当测试数据库采用mssql时，需要将该值设置为true
    //见http://www.dbunit.org/xref/org/dbunit/ext/mssql/InsertIdentityOperation.html
    //见http://stackoverflow.com/questions/4261707/phpunit-database-testing-with-dbunit-extension
    //jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=mydb;SelectMethod=cursor
    //需要更改url链接
    
    protected void loadFixture(){
    	
        try{
            String fixtureFile=this.dbHelper.getDataFile();
            if(fixtureFile==null){
                fixtureFile=this.getDefaultXMLFixtureFile();//只执行一次
                this.dbHelper.setDataFile(fixtureFile);
            }
            if(this.dbHelper.isDataFileExisted()){
                if(this.dbHelper.isMSSQL()){
                    MyInsertIdentityOperation operation=new MyInsertIdentityOperation(DatabaseOperation.CLEAN_INSERT);
                    operation.setInTransaction(true);
                    this.dbHelper.executeDBOperation(operation);
                }else{
                    this.dbHelper.executeDBOperation(DatabaseOperation.CLEAN_INSERT);
                }
            }
        }catch(Exception x){
            x.printStackTrace();
        }
    }
    
    private String getCallerClassName(){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        boolean foundCurrentClass=false;
        String currentClassName=OssTransactionalUnitT.class.getName();
        String callerClassName=null;
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            String stackClassName=stackTraceElement.getClassName();
            if(!foundCurrentClass && currentClassName.equals(stackClassName)){
                foundCurrentClass=true;
                continue;
            }
            if(foundCurrentClass && !currentClassName.equals(stackClassName)){
                callerClassName=stackClassName;
                break;
            }
        }
        return callerClassName;
    }
    
    private String getDefaultXMLFixtureFile(){
        String className=this.getCallerClassName();
        String fixtureFile=className.replace(".", "/")+".xml";
        System.out.println("@@@fixtureFile=" + fixtureFile);
        return fixtureFile;
    }
}
