package calendario;

import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class CalendarView extends GridPane {
    
    private CalendarModel model;
    private Label monthLabel;
    private GridPane calendarGrid;
    
    public CalendarView(CalendarModel model) {
        this.model = model;
        
        // Create the month label
        monthLabel = new Label();
        monthLabel.setTextFill(Color.BLUE);
        
        // Create the grid pane that displays the days
        calendarGrid = new GridPane();
        calendarGrid.setHgap(5);
        calendarGrid.setVgap(5);
        calendarGrid.setAlignment(Pos.CENTER);
        
        // Add the month label and grid pane to this layout
        add(monthLabel, 0, 0);
        add(calendarGrid, 0, 1);
        
        // Update the view
        updateView();
    }
    
    public void updateView() {
        // Update the month label
        monthLabel.setText(model.getMonthYearString());
        
        // Clear all days from the grid pane
        calendarGrid.getChildren().clear();
        
        // Add the days of the current month to the grid pane
        LocalDate date = model.getCurrentYearMonth().atDay(1);
        int row = 1;
        int col = date.getDayOfWeek().getValue();
        while (date.getMonth().equals(model.getCurrentYearMonth().getMonth())) {
            Label label = new Label(model.getDateString(date));
            label.setAlignment(Pos.CENTER);
            calendarGrid.add(label, col, row);
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
            date = date.plusDays(1);
        }
    }
     public LocalDate getSelectedDate() {
            return model.getSelectedDate();
    }
    
}
