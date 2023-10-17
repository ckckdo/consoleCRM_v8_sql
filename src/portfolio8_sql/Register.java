package portfolio8_sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Register {

	//DBに登録されている最大番号を取得
	public static int getMaxId() {
		ResultSet rs;
		int MaxId = -1;
		//SQL文作成
		String SqlTxt = "SELECT MAX(ID) AS ID FROM consolecrm_v8.m_user";
		//SQL実行
		rs = ConectSql.executeQuery(SqlTxt);

		try {
			//結果取得
			while(rs.next()) {
				MaxId = rs.getInt("ID");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return MaxId;

	}


	//DBにデータを登録
	public  static boolean registerData() {
		List<String> dataList = new ArrayList<>();
		int ret;

		// 最大番号を取得し、採番
		int maxNum = getMaxId();
		int number = -1;
		String strNumber = "";
		if (maxNum > 0 ) {
			number = maxNum + 1;
			strNumber = String.format("%03d",number);
		}

		//名前の入力
		String name = Message.name();

		//都道府県の入力
		String prefecture = Message.prefecture();

		//生年月日の入力
		String birth = Message.birth();

		//性別の入力
		String gender = Message.gender();

		//登録日・更新日
		LocalDate now = LocalDate.now();
		String nowDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));


		//内容を保存
		dataList.add(strNumber);
		dataList.add(name);
		dataList.add(prefecture);
		dataList.add(birth);
		dataList.add(gender);
		dataList.add(nowDate);
		dataList.add(nowDate);


		//SQL文作成
		String Sql = ConectSql.createRegisterSql(dataList);

		//SQL実行
		ret = ConectSql.executeUpdate(Sql);



		if (ret != 0) {
			//結果表示
			System.out.println("正常に登録が完了しました。");
			//正常終了
			return true;
		}else {
			return false;
		}

	}
}
