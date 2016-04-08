package application;
	
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Main extends Application {
	
	/*
	 * Author: Patrick W. Gulrajani
	 * 4/7/16
	 */
	
	public void generateRectangles(Rectangle rec, Rectangle rec2)
	{
		//Take the two defined rectangles and place them in random locations on the scene.
		//Perform detections based on intersection, containment, and adjacency
		
			//Randomized positions on the window.
			Random rec1X = new Random(); 
			Random rec1Y = new Random();
			int rec1_xposition = rec1X.nextInt(100) + 1;
			int rec1_yposition = rec1Y.nextInt(300) + 1;
			
			
			rec.setFill(Color.BLUE);
			rec.setX(rec1_xposition);
			rec.setY(rec1_yposition);
			
			//Randomly sized rectangles.
			Random rec_width = new Random();
			Random rec_height = new Random();
			int rec1_width = rec_width.nextInt(200) + 1; 
			int rec1_height = rec_height.nextInt(200) + 1;
		
			
			
			rec.setWidth(rec1_width);
			rec.setHeight(rec1_height);
			
			
			Random rec2X = new Random();
			Random rec2Y = new Random();
			
			int rec2_xposition = rec2X.nextInt(100) + 1;
			int rec2_yposition = rec2Y.nextInt(300) + 1;
			
			Random rec2_width = new Random();
			Random rec2_height = new Random();
			int rec2width = rec2_width.nextInt(200) +1;
			int rec2height = rec2_height.nextInt(200)+1;
			
			rec2.setFill(Color.RED);
			rec2.setX(rec2_xposition);
			rec2.setY(rec2_yposition);
			rec2.setWidth(rec2width);
			rec2.setHeight(rec2height);
			
			
			//Detections:
			calculateDetections(rec, rec2);
					
	}
	
	
	 
	 public void containment(Rectangle rec, Rectangle rec2)
	 {
		 /*
		  * Needs to fit inside the x bounds of rec.getX() to rec_xbound
		  * same for Y, in order to have containment.
		  * 
		  * As for the position, X can be larger, but the width cannot exceed the xbound.
		  */
		 
		 double rec_xbound = rec.getX() + rec.getWidth();
		 double rec_ybound = rec.getY() + rec.getHeight();
		 
		 double rec2_xbound = rec2.getX() + rec2.getWidth();
		 double rec2_ybound = rec2.getY() + rec2.getHeight();
		 
		 //This ensures that they can fit inside one another.
		 if(rec.getX() == rec2.getX() || (rec.getX() > rec2.getX()))
		 {
			 if((rec_xbound < rec2_xbound) || (rec_xbound == rec2_xbound)) //If we are within the x bounds
			{
				
				 if((rec_ybound < rec2_ybound) || (rec_ybound == rec2_ybound)) //If we are within the y bounds
				{ 
					if(rec.getY() > rec2.getY() && rec_ybound < rec2_ybound) //check x for containment
					{
						if(rec.getX() > rec2.getX() && rec_xbound < rec2_xbound) //check y for containment
						{
							
							//we have containment.
							System.out.println(">>Containment detected<< \n");
							
						}	
					}
					
				}
			}
		 }
		 
		 
		
		 else if(rec2.getX() == rec.getX() || (rec2.getX() > rec.getX()))
		 {
			 if((rec2_xbound < rec_xbound) || (rec2_xbound == rec_xbound))//If we are within the x bounds.
			 {
				 if((rec2_ybound < rec_ybound) || (rec2_ybound == rec_ybound))
				 {//they can fit inside one another.
					 
					 
					 if(rec2.getY() > rec.getY() && rec2_ybound < rec_ybound)
					 { 
						 if(rec2.getX() > rec.getX() && rec2_xbound < rec_xbound)
						 {
							 //We have containment.
							System.out.println(">>Containment detected<< \n");
						 }
						 
					 }
						
						 
						 
				 }
			 }
			 
		 }
		 
		 
		 
		 
		 
	 }
	
	 /*
	  * Intersection is API driven, containment is not.
	  */
	 public void checkIntersection(Rectangle rec, Rectangle rec2)
	 {
		
		 if(rec.intersects(rec2.getX(), rec2.getY(), rec2.getWidth(), rec2.getHeight())||        
		   (rec2.intersects(rec.getX(), rec.getY(), rec.getWidth(), rec.getHeight())))
		 {
			 System.out.println(">>Intersection detected<< \n");
			 System.out.println("Rectangle 1 specs: \n");
			 System.out.println( "rec.getX(): "+ rec.getX() + " rec.getY(): " +rec.getY()+ " rec.getWidth: " + rec.getWidth()+ " rec.getHeight(): "+ rec.getHeight() + "\n \n" );
			 
			 System.out.println("Rectangle 2 specs: \n");
			 System.out.println( "rec2.getX(): "+ rec2.getX() + " rec2.getY(): " +rec2.getY()+ " rec2.getWidth: " + rec2.getWidth()+ " rec2.getHeight(): "+ rec2.getHeight());
			 
			 System.out.println("---------------------------------------------------------------------------- \n");
		 }
		
		 
	 }
	 
	 
	 /*
	  * The values must match up exactly in order for adjacency to occur. 
	  */
	 public void checkAdjacency(Rectangle rec, Rectangle rec2)
	 {
		 double rec_xbound = rec.getX() + rec.getWidth();
		 double rec_ybound = rec.getY() + rec.getHeight();
		 
		 double rec2_xbound = rec2.getX() + rec2.getWidth();
		 double rec2_ybound = rec2.getY() + rec2.getHeight();
		 
		 
		if((rec.getX() == rec2_xbound) || (rec2.getX() == rec_xbound))
		{
			System.out.println(" >> Adjacency detected <<\n");
		}
		
		if((rec_ybound == rec2.getY()) || (rec2_ybound == rec.getY()))
		{
			System.out.println(">> Adjacancy detected << \n");
		}
		
		
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	
	public void calculateDetections(Rectangle rec, Rectangle rec2)
	{ 
		
		containment(rec, rec2);
		checkIntersection(rec, rec2);
		checkAdjacency(rec, rec2);
		
		}
	
	
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			Group root = new Group();
			Scene scene = new Scene(root,700,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Button generate = new Button();
			Rectangle rec = new Rectangle();
			Rectangle rec2 = new Rectangle();
			
			
			generate.setLayoutX(550);
			generate.setLayoutY(10);
			
			generate.setText("Generate Rectangles");
			
			root.getChildren().add(generate);
			root.getChildren().add(rec);
			root.getChildren().add(rec2);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
			generate.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override public void handle(ActionEvent e) 
				{
					generateRectangles(rec, rec2);
				}
				});
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
