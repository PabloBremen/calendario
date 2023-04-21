
package calendario;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CalendarController {
    
    @FXML private VBox root;
    @FXML private Button previousMonthButton;
    @FXML private Button nextMonthButton;
    @FXML private CalendarView calendarView;
    
    private CalendarModel model;
    
    public void initialize() {
        model = new CalendarModel();
        calendarView = new CalendarView(model);
        root.getChildren().add(calendarView);
    }
    
    public void handlePreviousMonthButton(ActionEvent event) {
        model.previousMonth();
        calendarView.updateView();
    }
    
    public void handleNextMonthButton(ActionEvent event) {
        model.nextMonth();
        calendarView.updateView();
    }
    
    public LocalDate getSelectedDate() {
        // Get the currently selected date from the calendar
        return calendarView.getSelectedDate();
    }

    public void addAppointment(LocalDate date) {
        // Show a dialog to add an appointment for the selected date
        String title = "Add Appointment for " + date;
        String text = "Enter appointment description:";
        String defaultText = "";
        TextInputDialog dialog = new TextInputDialog(defaultText);
        dialog.setTitle(title);
        dialog.setHeaderText(text);
        dialog.setContentText("Appointment:");
        dialog.showAndWait().ifPresent(description -> {
            // Add the appointment to the model
            model.addAppointment(date, description);
            // Update the calendar view
            calendarView.updateView();
        });
    }
}
