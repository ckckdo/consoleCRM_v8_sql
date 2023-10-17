package portfolio8_sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Delete {

	//削除条件の入力
	public static Boolean deleteMenu() {

		boolean choiceAct = false;
		String[] Act = new String[2]; //{削除条件,対象内容}
		int inputMenu = -1;


		//削除条件の選択
		while (!choiceAct) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("削除する条件を次から1つ選択し番号を入力してください");
			System.out.println("1：会員番号　2：名前　＞");

			try {
				inputMenu = scanner.nextInt();
				if (inputMenu == 9999) {
					//アプリの終了
					Exit.ExitApp();
				}
				if (inputMenu < 0 || inputMenu > 3) {
					System.out.println("入力された番号の条件はありません。");
				} else {
					Act[0] = String.valueOf(inputMenu - 1) ;
					choiceAct = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("無効な入力です。整数を入力してください。");
				scanner.next(); // 不正な入力をクリア
			}
		}

		//削除対象の内容
		switch (inputMenu){
		case 1:     //会員番号
			Act[1] = Message.kaiNumber();
			break;
		case 2:     //名前
			Act[1] = Message.name();
			break;
		default:
		}


		//削除する内容の表示
		List<String[]> data = new ArrayList<>();
		ResultSet rs;

		//SQL文作成
		String sql = ConectSql.createDeleteSelectSql(Act);
		//SQL実行
		rs = ConectSql.executeQuery(sql);

		if (rs != null) {
			try {
				// データがなくなるまで取り出す
				while (rs.next()) {
					String[] rowData = new String[7];
					for (int i = 0; i < 7; i++) {
						rowData[i] = rs.getString(i + 1);
					}

					data.add(rowData);

				}
				//結果表示
				System.out.println("以下のデータの削除します。");
				Message.result(data);

			}catch(SQLException e) {
				System.out.println("データを開けませんでした。");
				return false;
			}

		}else {
			System.out.println("SQLの実行中にエラーが発生しました。");
			return false;
		}


		//削除処理
		if (!deleteData(Act)) {
			System.out.println("削除に失敗しました。");
			return false;
		}else {
			//結果表示
			System.out.println();
			System.out.println();
		}


		//正常終了
		return true;


	}


	//指定データの削除
	public static boolean deleteData(String[] Act) {

		int ret = 0;

		String Sql = "";

		//SQL文作成
		Sql = ConectSql.createDeleteSql(Act);
		//SQL実行
		ret = ConectSql.executeUpdate(Sql);

		if (ret != 0) {
			//結果表示
			System.out.println("正常に削除が完了しました。");
			//正常終了
			return true;
		}else {
			return false;
		}

	}
}
