package calendario;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    	 // Create the model and the view
        CalendarModel model = new CalendarModel();
        CalendarView view = new CalendarView(model);

        view.setPrefWidth(600);
        view.setPrefHeight(400);
        // Create the scene and add the view to it
        Scene scene = new Scene(view);

        // Set the stage title and scene, and show the stage
        stage.setTitle("Calendar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
