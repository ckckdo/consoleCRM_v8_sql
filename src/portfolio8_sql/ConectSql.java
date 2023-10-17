package portfolio8_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class ConectSql {

	private static final String URL = "jdbc:mysql://localhost/mysql?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "root";


	public static ResultSet executeQuery(String sql) {


		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			// データベース接続
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// SQL実行準備
			stmt = con.prepareStatement(sql);
			// 実行結果取得
			rs = stmt.executeQuery();
			return rs;

		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}


	}


	public static int executeUpdate(String sql) {


		Connection con = null;
		PreparedStatement stmt = null;
		int ret = 0;
		try {
			// データベース接続
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			// SQL実行準備
			stmt = con.prepareStatement(sql);
			// 実行結果取得
			ret = stmt.executeUpdate();
			return ret;

		}catch (SQLException e) {
			e.printStackTrace();
			return ret;
		}

	}




	public static String createSearchSelectSql(String[] joken) {

		StringBuilder SqlTxt = new StringBuilder();

		SqlTxt.append("SELECT * ");
		SqlTxt.append(System.lineSeparator()); //改行
		SqlTxt.append("FROM consolecrm_v8.m_user ");
		SqlTxt.append(System.lineSeparator()); //改行
		SqlTxt.append("WHERE ");

		switch (Integer.parseInt(joken[2])) {
		case 0: //会員番号
			SqlTxt.append("ID = ");
			break;

		case 1: //名前
			SqlTxt.append("NAME = ");
			break;

		case 2: //都道府県
			SqlTxt.append("PREFECTURE = ");
			break;

		case 3: //性別
			SqlTxt.append("GENDER = ");
			break;

		case 4: //生年月日
			SqlTxt.append("BIRTH = ");
			break;

		default: //全件
			SqlTxt.append("5 = ");
			break;
		}
		SqlTxt.append(joken[0] + " ");  //検索キー
		SqlTxt.append(System.lineSeparator()); //改行
		SqlTxt.append("ORDER BY ");

		if (Integer.parseInt(joken[1]) == 1) {
			SqlTxt.append("ID;");		//会員番号順
		} else {
			SqlTxt.append("AD_DATE;");	//登録日順
		}

		return SqlTxt.toString();
	}


	public static String createRegisterSql(List<String> data) {

		StringBuilder SqlTxt = new StringBuilder();
		StringBuilder strData = new StringBuilder();

		SqlTxt.append("INSERT INTO consolecrm_v8.m_user ");
		SqlTxt.append(System.lineSeparator()); //改行
		SqlTxt.append("VALUE  ( ");
		for (int i= 0; i < 6; i++) {
			strData.append(data.get(i) + ",");
		}
		strData.append(data.get(6));
		SqlTxt.append(strData);
		SqlTxt.append(" ) ");

		return SqlTxt.toString();
	}



	public static String createDeleteSql(String[] Act) {

		StringBuilder SqlTxt = new StringBuilder();

		SqlTxt.append("DELETE FROM consolecrm_v8.m_user ");
		SqlTxt.append("WHERE ");

		if (Integer.parseInt(Act[0]) == 0) {
			SqlTxt.append("ID = ");
		} else {
			SqlTxt.append("NAME = ");
		}
		SqlTxt.append(Act[1]);

		return SqlTxt.toString();
	}


	public static String createDeleteSelectSql(String[] Act) {

		StringBuilder SqlTxt = new StringBuilder();

		SqlTxt.append("SELECT * ");
		SqlTxt.append(System.lineSeparator()); //改行
		SqlTxt.append("FROM consolecrm_v8.m_user ");
		SqlTxt.append(System.lineSeparator()); //改行
		SqlTxt.append("WHERE ");

		switch (Integer.parseInt(Act[0])) {
		case 0: //会員番号
			SqlTxt.append("ID = ");
			break;

		case 1: //名前
			SqlTxt.append("NAME = ");
			break;
		}

		SqlTxt.append(Act[1]);

		return SqlTxt.toString();
	}

}
