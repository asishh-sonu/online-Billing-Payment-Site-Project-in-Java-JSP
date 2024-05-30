package com.javatpoint;
import javax.servlet.*;
import java.sql.*;

public class MyListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent arg0) {
		
		Connection con=null;
	try{
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
		
		PreparedStatement ps2= con.prepareStatement("CREATE SEQUENCE JAVATPOINT MINVALUE 1 MAXVALUE 999999999 INCREMENT BY 1 START WITH 1 NOCACHE NOORDER NOCYCLE");
		ps2.executeUpdate();
		PreparedStatement ps=con.prepareStatement("CREATE TABLE STUDENT2 (ID NUMBER,NAME VARCHAR2(4000), COURSE VARCHAR2(4000), MOBILE VARCHAR2(4000), FEESUB VARCHAR2(4000), FEE VARCHAR2(4000), PAID VARCHAR2(4000), BALANCE VARCHAR2(4000), ADDRESS VARCHAR2(4000), FATHERNAME VARCHAR2(4000), MOTHERNAME VARCHAR2(4000), DATEOFBIRTH VARCHAR2(4000), QUALIFICATION VARCHAR2(4000), DATEOFJOINING VARCHAR2(4000), DESCRIPTION VARCHAR2(4000), TRAINER VARCHAR2(4000),CONSTRAINT STUDENT2_PK PRIMARY KEY (ID) ENABLE)");
		ps.executeUpdate();
		PreparedStatement ps4=con.prepareStatement("CREATE TABLE PAYREGISTER(ID NUMBER,USERNAME VARCHAR2(4000), USERPASS VARCHAR2(4000), BRANCH VARCHAR2(4000),DATEOFJOINING VARCHAR2(4000), DATEOFBIRTH VARCHAR2(4000), SALARY VARCHAR2(4000),CONSTRAINT PAYREGISTER_PK PRIMARY KEY (ID) ENABLE)");
		ps4.executeUpdate();		
		
		Statement stmt=con.createStatement();
		stmt.executeUpdate("CREATE TRIGGER  BI_STUDENT2 before insert on STUDENT2 for each row begin select JAVATPOINT.nextval into :NEW.ID from dual; end");
		stmt.executeUpdate("CREATE TRIGGER  BI_PAYREGISTER before insert on PAYREGISTER for each row begin select JAVATPOINT.nextval into :NEW.ID from dual;end");
		}	
	
		
	    catch(Exception e){
	    	e.printStackTrace();
	    	
	    }
	    }
	    
	    public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("project undeployed");
		
	}
}
