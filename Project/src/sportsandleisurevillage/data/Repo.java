package sportsandleisurevillage.data;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public interface Repo {
	String url = "jdbc:mysql://charlesbarry.coventry.domains:3306/charlesb_260CT";
	String username = "charlesb_charlie";
	String password = "Password1234";

	ResultSet read();
	void delete();
	void update();
	void closeconn();
}