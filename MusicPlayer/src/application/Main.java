package application;
	
import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Main extends Application {
	static String ss="";
	static Media media;
	static MediaPlayer mediaPlayer;
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void playmusic(String path)
	{
		 media = new Media(new File(path).toURI().toString());
		 mediaPlayer = new MediaPlayer(media);
		 mediaPlayer.play();
		
	}

	public static void main(String[] args) {
      
		launch(args);
		
		}
	
	}
