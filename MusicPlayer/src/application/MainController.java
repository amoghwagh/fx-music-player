package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
	public static  boolean playing=false;
	static File f;
	static File f1;
	static PrintWriter pw;
	static Scanner sc;
	   
	@FXML
    private Button pp;
	@FXML
	private AnchorPane mainpane;
	@FXML
	private Button ff;
	@FXML
	private Button rr;
	 @FXML
	public Button check;
	
	public ListView<String> list;

    @FXML
    private Button nextbutton;

    @FXML
    private Button prevbutton;

    @FXML
    private Button addbutton;

    @FXML
    private Button delbutton;

    @FXML
    private Button searchbutton;
    
    @FXML
    void loadnext(ActionEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("Search.fxml"));
    	mainpane.getChildren().setAll(pane);
    }
 

    @FXML
    void add(MouseEvent event) throws IOException {
 
    	FileChooser fc = new FileChooser();
    	FileChooser.ExtensionFilter songFilter = new FileChooser.ExtensionFilter("Song Files", "*.mp3");
    	fc.getExtensionFilters().add(songFilter);
    
    	File sf = fc.showOpenDialog(null);
    	
    	if(sf!=null)
    	{	String name=sf.getName();
    		if(name.endsWith(".mp3"))
    		{
    			name= name.substring(0,name.length()-4);
    		}
    		String path=sf.getAbsolutePath();
    		String entry = name+"|"+path+"\n";
    		PrintWriter pw = new PrintWriter(new FileWriter(f,true));
    		pw.write(entry);
    		PrintWriter pw1 = new PrintWriter(new FileWriter(f1,true));
    		pw1.write(name+"\n");
    		pw1.close();
    		pw.close();
    		list.getItems().add(name);
    	}
    	else
    	{
    		System.out.println("File not found");
    	}
    	sc = new Scanner(f);
    	ArrayList<String> arr = new ArrayList<String>();
    	ArrayList<String> arr1 = new ArrayList<String>();
    	
    	while(sc.hasNextLine())
    	{
    		String s2=sc.nextLine();
    		String s3 = s2.substring(0,s2.indexOf('|') );
    		 arr.add(s3);
    	}
    		arr1 = bubblesort(arr);
    	PrintWriter pw2 = new PrintWriter(new FileWriter(f1,false));
    	for(int i=0;i<arr1.size();i++)
    	{
    		pw2.write(arr1.get(i)+"\n");	
    	}
    	pw2.close();
    }

    @FXML
     void delete(MouseEvent event) throws IOException {
    	String delsong = list.getSelectionModel().getSelectedItem();
    	Scanner sc1 = new Scanner(f);
    	Scanner sc2 = new Scanner(f1);
		ArrayList<String> delarray = new ArrayList<String>();
		ArrayList<String> delarray1 = new ArrayList<String>();
    	while(sc2.hasNext())
    	{
    		String ds = sc2.nextLine();
    		if(ds.equalsIgnoreCase(delsong))
    		{
    		continue;
    		}
    		delarray.add(ds);
    	}
    	PrintWriter pw1 = new PrintWriter(new FileWriter(f1,false));
    	for(int d=0;d<delarray.size();d++)
    	pw1.write(delarray.get(d)+"\n");
    	pw1.close();
    	while(sc1.hasNext())
    	{
    		String ds1 = sc1.nextLine();
    		String deletesong = ds1.substring(0,ds1.indexOf("|"));
    		if(deletesong.equalsIgnoreCase(delsong))
    		{
    		continue;
    		}
    		delarray1.add(ds1);
    	}
    	PrintWriter pw2 = new PrintWriter(new FileWriter(f,false));
    	for(int j=0;j<delarray1.size();j++)
    	pw2.write(delarray1.get(j)+"\n");
    	pw2.close();
    	sc1.close();
    	sc2.close();
    	update();
   }

    @FXML
    void next(MouseEvent event) {
    }

    @FXML
  void playpause(MouseEvent event) throws IOException {
    	
    	if(!playing)
    	{
    		
    		String song=list.getSelectionModel().getSelectedItem();
   
    		String path=getPath(song);
    		

    		Main.playmusic(path);
    		pp.setText("Pause");
    		
    		playing=true;
    		
    	}
    	else
    	{
    		Main.mediaPlayer.pause();
    		pp.setText("Play");
    		playing=false;
    	}
    	

    }

    @FXML
    void prev(MouseEvent event) {
    }
    
    @FXML
    void fastforward(MouseEvent event) {
    	Main.mediaPlayer.seek(Main.mediaPlayer.getCurrentTime().multiply(1.5));
    }

    @FXML
    void rewind(MouseEvent event) {
    	Main.mediaPlayer.seek(Main.mediaPlayer.getCurrentTime().divide(1.5));
    }
    @FXML
    void search(MouseEvent event) throws IOException {
    	
    	
    	
    	Stage primaryStage=new Stage();
		Parent root=FXMLLoader.load(getClass().getResource("Search.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		primaryStage.show();

    }
    
    public static String getPath(String song) throws IOException
    {	
    	System.out.println(song);
    	String path="";
    	sc=new Scanner(f);
    	while(sc.hasNextLine())
    	{
    		String s=sc.nextLine();
    		if(s.substring(0,song.length()).equals(song))
    		{
    			path=s.substring(song.length()+1);
    			break;
    		}
    	}
    	System.out.println(path);
    	return path;

    }
    
    public static ArrayList<String> bubblesort(ArrayList<String> arr3)
    {
    	 for(int j=0;j<arr3.size();j++)
    	 {
    		 	
    	for(int i=j+1;i<arr3.size();i++)
     {
    	 if((arr3.get(i)).compareToIgnoreCase(arr3.get(j))<0)
     {  		
		   String t = arr3.get(j);
		   arr3.set(j,arr3.get(i));
		   arr3.set(i,t);
    		 
    }
  }
    }
    	 return arr3;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ObservableList<String> wordsList = FXCollections.observableArrayList();
    	
    	f=new File("C:\\Users\\amogh\\eclipse-workspace\\MusicPlayer\\src\\application\\songs");
    	f1 = new File("C:\\Users\\amogh\\eclipse-workspace\\MusicPlayer\\src\\application\\index");
    	try
    	{
			sc=new Scanner(f);
		} 
    	catch (FileNotFoundException e) 
    	{
			e.printStackTrace();
		}
    	while(sc.hasNextLine())
    	{
    		String s=sc.nextLine();
    		wordsList.add(s.substring(0,s.indexOf('|') ));
    	}
    	
    	list.setItems(wordsList);
    	if(Main.ss.isEmpty())
    	{
    		list.getSelectionModel().select(0);
    	}
    	list.getSelectionModel().select(Main.ss);
    	
	}
	public void update()
	{
		ObservableList<String> wordsList = FXCollections.observableArrayList();
		f=new File("C:\\Users\\amogh\\eclipse-workspace\\MusicPlayer\\src\\application\\songs");
		try
    	{
			sc=new Scanner(f);
		} 
    	catch (FileNotFoundException e) 
    	{
			e.printStackTrace();
		}
    	while(sc.hasNextLine())
    	{
    		String s=sc.nextLine();
    		wordsList.add(s.substring(0,s.indexOf('|') ));
    	}
    	
    	list.setItems(wordsList);
    	list.getSelectionModel().select(0);
	}
	public void upd(String s)
	{
		list.getSelectionModel().select(s);
	}
}
