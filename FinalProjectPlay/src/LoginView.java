import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LoginView {
	private Scene loginViewScene;
	private AdminView adminView;
	private UserView userView;
	private HBox allPic;
	private HBox addLines;
	private User user;
	private TextField tfUserName;
	private PasswordField tfPassWord;
	private Text tUserName, tPassWord;
	private Button btnLogin, btnRegister, btnReturn;
	private Image img1, img2, img3;
	private ImageView imgV1, imgV2, imgV3;
	private UserShelf us = new UserShelf(new User[] {});

	public Scene LoginViewScene() {
		UserShelf.read(us);
		adminView = new AdminView();
		userView = new UserView();
		user = new User();

		tfUserName = new TextField("");
		tfPassWord = new PasswordField();
		tUserName = new Text("Username: ");
		tPassWord = new Text("Password: ");
		tUserName.setFont(new Font(15));
		tUserName.setFill(Color.PURPLE);
		tUserName.setStroke(Color.CYAN);
		tPassWord.setFont(new Font(15));
		tPassWord.setFill(Color.PURPLE);
		tPassWord.setStroke(Color.CYAN);
		btnLogin = new Button("Login");
		btnRegister = new Button("Register");
		btnReturn = GUIsetup.returnMainScreen();
		btnReturn.setText("Cancel");

		img1 = null;
		img2 = null;
		img3 = null;
		try {
			img1 = new Image(new FileInputStream("hmn.png"));
			img2 = new Image(new FileInputStream("lock.png"));
			img3 = new Image(new FileInputStream("lgn.png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		imgV1 = new ImageView(img1);
		imgV2 = new ImageView(img2);
		imgV3 = new ImageView(img3);
		imgV1.setFitHeight(30);
		imgV1.setFitWidth(30);
		imgV2.setFitHeight(30);
		imgV2.setFitWidth(30);
		imgV3.setFitHeight(280);
		imgV3.setFitWidth(280);

		btnLogin.setOnAction(e -> {
			UserShelf.read(us);
			for (int i = 0; i < us.getAllUsers().size(); i++) {
				user = us.getAllUsers().get(i);
				if (tfUserName.getText().equals(user.getUserName())
						&& tfPassWord.getText().equals(user.getPassWord())) {
					userView.setCurrentUser(user);
					GUIsetup.mainSTG.setScene(userView.UserViewScene());
					return;
				}
			}
			if (tfUserName.getText().equals("admin") && tfPassWord.getText().equals("passAdmin")) {
				GUIsetup.mainSTG.setScene(adminView.AdminViewScene());
			}
		});

		btnRegister.setOnAction(e -> {
			TextField rUserName = new TextField("");
			TextField rPassWord = new TextField("");
			TextField rFName = new TextField("");
			TextField rLName = new TextField("");
			TextField rAge = new TextField("");
			TextField rAdress = new TextField("");
			Text tUserName = new Text("Usernmae: ");
			Text tPassWord = new Text("Password: ");
			Text tFName = new Text("First Name: ");
			Text tLName = new Text("Last Name: ");
			Text tAge = new Text("Age: ");
			Text tAdress = new Text("Address: ");
			tUserName.setFont(new Font(15));
			tUserName.setFill(Color.PURPLE);
			tUserName.setStroke(Color.CYAN);
			tPassWord.setFont(new Font(15));
			tPassWord.setFill(Color.PURPLE);
			tPassWord.setStroke(Color.CYAN);
			tFName.setFont(new Font(15));
			tFName.setFill(Color.PURPLE);
			tFName.setStroke(Color.CYAN);
			tLName.setFont(new Font(15));
			tLName.setFill(Color.PURPLE);
			tLName.setStroke(Color.CYAN);
			tAge.setFont(new Font(15));
			tAge.setFill(Color.PURPLE);
			tAge.setStroke(Color.CYAN);
			tAdress.setFont(new Font(15));
			tAdress.setFill(Color.PURPLE);
			tAdress.setStroke(Color.CYAN);

			Button btnReg = new Button("Register");
			btnReg.setOnAction(e1 -> {
				User regUser = new User(rUserName.getText(), rPassWord.getText(), rFName.getText(), rLName.getText(),
						rAge.getText(), rAdress.getText());
				regUser.setID(User.uniqueID());
				try (ObjectOutputStream bookLocation = new ObjectOutputStream(
						new FileOutputStream("userInfo\\" + regUser.getID() + ".dat"));) {
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				us.addUser(regUser);
				UserShelf.write(us);
				UserShelf.read(us);
				loginViewScene.setRoot(allPic);
			});

			Button btnCancel = new Button("Cancel");
			btnCancel.setOnAction(e1 -> {
				loginViewScene.setRoot(allPic);
			});

			HBox btns = new HBox();
			btns.getChildren().addAll(btnReg, btnCancel);
			btns.setAlignment(Pos.BASELINE_RIGHT);
			HBox.setMargin(btnReg, new Insets(10, 5, 10, 10));
			HBox.setMargin(btnCancel, new Insets(10, 10, 10, 5));

			Text tTitle = new Text("REGISTER");
			tTitle.setFont(new Font(50));
			tTitle.setFill(Color.PURPLE);
			tTitle.setStroke(Color.CYAN);
			VBox firstLine = new VBox();
			firstLine.getChildren().addAll(tUserName, tPassWord, tFName, tLName, tAge, tAdress);
			VBox.setMargin(tUserName, new Insets(97, 0, 15, 0));
			VBox.setMargin(tPassWord, new Insets(0, 0, 15, 0));
			VBox.setMargin(tFName, new Insets(0, 0, 15, 0));
			VBox.setMargin(tLName, new Insets(0, 0, 15, 0));
			VBox.setMargin(tAge, new Insets(0, 0, 15, 0));
			VBox.setMargin(tAdress, new Insets(0, 0, 15, 0));

			VBox secondLine = new VBox();
			secondLine.getChildren().addAll(tTitle, rUserName, rPassWord, rFName, rLName, rAge, rAdress, btns);
			VBox.setMargin(tTitle, new Insets(5, 0, 0, 0));
			VBox.setMargin(rUserName, new Insets(25, 0, 10, 5));
			VBox.setMargin(rPassWord, new Insets(0, 0, 10, 5));
			VBox.setMargin(rFName, new Insets(0, 0, 10, 5));
			VBox.setMargin(rLName, new Insets(0, 0, 10, 5));
			VBox.setMargin(rAge, new Insets(0, 0, 10, 5));
			VBox.setMargin(rAdress, new Insets(0, 0, 10, 5));

			addLines = new HBox();
			addLines.setAlignment(Pos.CENTER);
			addLines.getChildren().addAll(firstLine, secondLine);
			FileInputStream input = null;
			try {
				input = new FileInputStream("bg.png");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
			Image backgroundIMG = new Image(input);
			BackgroundImage backgroundimage = new BackgroundImage(backgroundIMG, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background background = new Background(backgroundimage);
			addLines.setBackground(background);
			loginViewScene.setRoot(addLines);

		});

		VBox firstLine = new VBox();
		firstLine.getChildren().addAll(tUserName, tPassWord);
		VBox.setMargin(tPassWord, new Insets(35, 0, 0, 0));

		HBox buttons = new HBox();
		HBox.setMargin(btnLogin, new Insets(5));
		HBox.setMargin(btnRegister, new Insets(5));
		HBox.setMargin(btnReturn, new Insets(5));
		buttons.getChildren().addAll(btnLogin, btnRegister, btnReturn);

		VBox pics = new VBox();
		pics.getChildren().addAll(imgV1, imgV2);
		VBox.setMargin(imgV1, new Insets(10, 0, 0, 0));

		VBox secondLine = new VBox(tfUserName, tfPassWord, buttons);
		VBox.setMargin(tUserName, new Insets(10, 5, 5, 5));
		VBox.setMargin(tfUserName, new Insets(10, 5, 5, 5));
		VBox.setMargin(tPassWord, new Insets(5));
		VBox.setMargin(tfPassWord, new Insets(5));

		HBox content = new HBox();
		content.getChildren().addAll(firstLine, pics, secondLine);

		VBox wPic = new VBox();
		wPic.getChildren().addAll(imgV3, content);
		VBox.setMargin(imgV3, new Insets(10, 0, 0, 0));

		allPic = new HBox();
		allPic.getChildren().addAll(wPic);
//		allPic.setPadding(new Insets(25));
		allPic.setAlignment(Pos.CENTER);
		FileInputStream input = null;
		try {
			input = new FileInputStream("bg.png");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Image backgroundIMG = new Image(input);
		BackgroundImage backgroundimage = new BackgroundImage(backgroundIMG, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background background = new Background(backgroundimage);
		allPic.setBackground(background);
		loginViewScene = new Scene(allPic);
		return loginViewScene;
	}

}
