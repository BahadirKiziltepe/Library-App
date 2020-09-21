import java.io.FileInputStream;
import java.io.Serializable;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUIsetup extends Application implements Serializable {

	private static final long serialVersionUID = 5236417588606537343L;
	private static MainMenu mm;
	private Image imgLib;

	static Stage mainSTG;
	static Scene mainScene;

	@Override
	public void start(Stage stg) throws Exception {
		mm = new MainMenu();
		imgLib = new Image(new FileInputStream("Bookshelf.png"));
		mainSTG = stg;

		BorderPane bpMain = new BorderPane();
		bpMain.setCenter(mm.getBP());
		mainScene = mm.MenuScene();

		mainSTG.setScene(mainScene);
		mainSTG.setTitle("Library");
		mainSTG.getIcons().add(imgLib);
		mainSTG.setWidth(1320);
		mainSTG.setHeight(512);
		mainSTG.setResizable(false);
		mainSTG.show();
	}

	public static void main(String[] args) {
		try {
			Application.launch(args);
		} catch (IllegalStateException e) {
			
		}
	}

	public static void setStage(Stage mainSTG) {
		GUIsetup.mainSTG = mainSTG;
	}

	public static Stage getStage() {
		return mainSTG;
	}

	public static Button returnMainScreen() {
		Button buttonBack = new Button("Logout");
		buttonBack.setOnAction(e -> {
			GUIsetup.mainSTG.setScene(mm.MenuScene());
		});
		return buttonBack;
	}

}
