package sportsandleisurevillage.data;

import java.sql.Connection;
import java.util.ArrayList;

public interface Repo {

	/**
	 * 
	 * @param conn
	 */
	ArrayList read(Connection conn);

	/**
	 * 
	 * @param conn
	 * @param list
	 */
	void write(Connection conn, ArrayList list);

	/**
	 * 
	 * @param conn
	 * @param list
	 */
	void update(Connection conn, ArrayList list);

}