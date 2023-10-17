package portfolio8_sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Search {


	//検索条件・検索キーの入力
	public static String[] searchMenu() {

		boolean choiceJoken = false;
		boolean choiceSort = false;
		int jokenNumber = -1;
		int sort = -1;
		String[] jokenSort = new String[3]; //{検索条件,ソート順,検索項目}

		//検索条件の選択
		while (!choiceJoken) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("検索する条件を次から1つ選択し番号を入力してください");
			System.out.println("1：会員番号　2：名前　3：都道府県　4：性別　5:生年月日　 6：全件　＞");

			try {
				jokenNumber = scanner.nextInt();
				if (jokenNumber == 9999) {
					//アプリの終了
					Exit.ExitApp();
				}
				if (jokenNumber < 0 || jokenNumber > 6) {
					System.out.println("入力された番号の条件はありません。");
				} else {
					jokenSort[2] = String.valueOf(jokenNumber - 1) ;
					choiceJoken = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("無効な入力です。整数を入力してください。");
				scanner.next(); // 不正な入力をクリア
			}
		}

		//ソート順の選択
		while (!choiceSort) {
			Scanner scanner = new Scanner(System.in);
			System.out.println("表示する順番を次から1つ選択し番号を入力してください");
			System.out.println("1：会員番号順　2：登録日順　＞");

			try {
				sort = scanner.nextInt();
				if (jokenNumber == 9999) {
					//アプリの終了
					Exit.ExitApp();
				}
				if (sort != 1 && sort != 2) {
					System.out.println("入力された番号の順番はありません。");
				} else {
					jokenSort[1] = String.valueOf(sort);
					choiceSort = true;
				}
			} catch (java.util.InputMismatchException e) {
				System.out.println("無効な入力です。整数を入力してください。");
				scanner.next(); // 不正な入力をクリア
			}
		}

		//検索キー入力
		switch (jokenNumber){
		case 1:     //会員番号
			jokenSort[0] = Message.kaiNumber();
			break;
		case 2:     //名前
			jokenSort[0] = Message.name();
			break;
		case 3:     //都道府県
			jokenSort[0] = Message.prefecture();
			break;
		case 4:     //性別
			jokenSort[0] = Message.gender();
			break;
		case 5:     //生年月日
			jokenSort[0] = Message.birth();
			break;
		case 6:     //全件
			jokenSort[0] = "5";
			break;
		default:
			;
		}

		return jokenSort;

	}


	//検索結果取得
	public static boolean searchData (String[] jokenSort) {

		List<String[]> data = new ArrayList<>();
		ResultSet rs;

		//SQL文作成
		String sql = ConectSql.createSearchSelectSql(jokenSort);
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
				Message.result(data);

			}catch(SQLException e) {
				System.out.println("データを開けませんでした。");
				return false;
			}

			//正常終了
			return true;

		}else {
			System.out.println("SQLの実行中にエラーが発生しました。");
			return false;
		}

	}

}
