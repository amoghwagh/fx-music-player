package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SearchController {

    @FXML
    private TextField text;

    @FXML
    private Button searchbut;

    @FXML
    private Label label;

    @FXML
   private ListView<String> list;

    @FXML
    private Button ok;
    
    @FXML
    void okay(MouseEvent event) throws IOException {
    	
    	Main.ss=list.getSelectionModel().getSelectedItem();
		Parent root=FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		Stage primaryStage=new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
		Stage stage = (Stage)ok.getScene().getWindow();
	   stage.close();
	  
    }

    @FXML
    void search(MouseEvent event) throws FileNotFoundException {
    	ObservableList<String> words = FXCollections.observableArrayList();
    	File f = new File("C:\\Users\\amogh\\eclipse-workspace\\MusicPlayer\\src\\application\\index");
    	Scanner sc1 = new Scanner(f);
    	String stext = text.getText();
    	while(sc1.hasNextLine())
    	{
    		 String s = sc1.nextLine();
    		 String s2=s.toLowerCase();
     	    Pattern p = Pattern.compile(stext.toLowerCase());
     	   Matcher m = p.matcher(s2);
     	    if (m.find())
     	    { System.out.println(s); words.add(s);}
     	    else
     	      continue;
    	}
    	list.setItems(words);
    	sc1.close();

    }

}
