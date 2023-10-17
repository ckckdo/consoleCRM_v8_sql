package portfolio8_sql;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {


		Scanner scanner = new Scanner(System.in);
		boolean StartMenu = true;

		//起動メッセージ
		System.out.println("会員管理システムを起動しました。");
		//アプリ終了の方法表示
				Exit.ExitMsg();


				//メニューの表示
				while (StartMenu) {
					System.out.println("実行するメニューの番号を入力してください ");
//					System.out.println("1：登録　2：検索　3：削除　4：修正　0:終了　＞");
					System.out.println("1：登録　2：検索　3：削除　0:終了　＞");
					try {
						int ChoiceMenu ;
						ChoiceMenu = scanner.nextInt();

						if (ChoiceMenu < 0 || ChoiceMenu > 4) {
							System.out.println("入力された番号のメニューはありません。");
						} else {
							switch (ChoiceMenu) {
							case 1:  //登録
								System.out.println("登録します。");
								if (!Register.registerData()){
									Message.restartMsg();
								}
								break;
							case 2:  //検索
								System.out.println("検索します。");
								String[] data ;
								data = Search.searchMenu() ;
								if(!Search.searchData(data)){
									Message.restartMsg();
								}
								break;
							case 3:  //削除
								System.out.println("削除します。");
								if(!Delete.deleteMenu()) {
									Message.restartMsg();
								}
								break;
							case 4:  //修正
								System.out.println("登録されている情報を修正します。");
//								if(!Edit.EditMenu(FilePath)) {
//									Message.restartMsg();
//								}
								break;
							case 0:  //終了
								//アプリの終了
								Exit.ExitApp();
								break;
							default:  //その他
								System.out.println("入力された番号のメニューはありません。");
							}
						}

					} catch (java.util.InputMismatchException e) {
						System.out.println("無効な入力です。整数を入力してください。＞");
					}
				}

	}


}
