package com.codegnan.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class Dao {

	protected Connection con = null;

	public Dao() {
		super();
	}

	public void commit() throws SQLException {
		con.commit();
		con.close();
	}

	public void rollback() throws SQLException {
		con.rollback();
		con.close();
	}

}