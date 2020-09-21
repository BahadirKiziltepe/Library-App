import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainMenu extends GUIsetup implements Serializable {

	private static final long serialVersionUID = 5508489641208415111L;
	private LoginView loginView;
	private GuestView guestView;

	private BorderPane bpMainMenu;
	private Group gMainMenu;
	private HBox centerBTN;
	private Button btnLogin, btnGuest, btnExit;
	private Image img1, img2, btnImg, backgroundIMG;
	private ImageView imgV1, imgV2, btnV1, btnV2, btnV3;
	private Text text;

	public MainMenu() {
	}

	public Scene MenuScene() {
		loginView = new LoginView();
		guestView = new GuestView();

		try {
			img1 = new Image(new FileInputStream("Bookshelf.png"));
		} catch (FileNotFoundException e6) {
			e6.printStackTrace();
		}
		imgV1 = new ImageView(img1);
		imgV1.setFitHeight(225);
		imgV1.setFitWidth(225);
		imgV1.setX(650);
		imgV1.setY(115);
		try {
			img2 = new Image(new FileInputStream("Librarian.png"));
		} catch (FileNotFoundException e5) {
			e5.printStackTrace();
		}
		imgV2 = new ImageView(img2);
		imgV2.setFitHeight(300);
		imgV2.setFitWidth(233);
		imgV2.setY(100);
		gMainMenu = new Group();
		centerBTN = new HBox(15);
		centerBTN.setPadding(new Insets(15, 0, 0, 35));
		text = new Text(100, 500, "Welcome To My Library!");
		text.setFont(new Font(75));
		text.setFill(Color.PURPLE);
		text.setStroke(Color.CYAN);
		try {
			btnImg = new Image(new FileInputStream("btn.png"));
		} catch (FileNotFoundException e4) {
			e4.printStackTrace();
		}
		btnV1 = new ImageView(btnImg);
		btnV1.setFitHeight(30);
		btnV1.setFitWidth(30);
		btnV2 = new ImageView(btnImg);
		btnV2.setFitHeight(30);
		btnV2.setFitWidth(30);
		btnV3 = new ImageView(btnImg);
		btnV3.setFitHeight(30);
		btnV3.setFitWidth(30);
		btnLogin = new Button("Login");
		btnGuest = new Button("Guest");
		btnExit = new Button("Exit");
		btnLogin.setGraphic(btnV1);
		btnGuest.setGraphic(btnV2);
		btnExit.setGraphic(btnV3);
		centerBTN.getChildren().addAll(btnLogin, btnGuest, btnExit);
		btnLogin.setOnAction(e -> {
			GUIsetup.mainSTG.setScene(loginView.LoginViewScene());
		});
		btnGuest.setOnAction(e -> GUIsetup.mainSTG.setScene(guestView.GuestViewScene()));
		btnExit.setOnAction(e -> System.exit(1));
		btnLogin.setTextFill(Color.GREEN);
		btnGuest.setTextFill(Color.BLUE);
		btnExit.setTextFill(Color.RED);
		
		bpMainMenu = new BorderPane();
		gMainMenu.getChildren().addAll(imgV1, imgV2, bpMainMenu);
		bpMainMenu.setCenter(centerBTN);
		centerBTN.setAlignment(Pos.CENTER);
		bpMainMenu.setTop(text);
		HBox container = new HBox(gMainMenu);
		FileInputStream input = null;
		try {
			input = new FileInputStream("bg.png");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		backgroundIMG = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(backgroundIMG, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
		container.setBackground(background);
		container.setAlignment(Pos.CENTER);
		Scene sceneAdmin = new Scene(container);
		return sceneAdmin;
	}

	public void setBP(BorderPane bp) {
		this.bpMainMenu = bp;
	}

	public BorderPane getBP() {
		return bpMainMenu;
	}

}
