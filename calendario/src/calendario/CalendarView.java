package calendario;

import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


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

        // Add a change listener to the width and height properties of the calendar view
        // to adjust the font size of the labels accordingly
        widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldWidth, Number newWidth) {
                double fontSize = newWidth.doubleValue() / 25;
                calendarGrid.getChildren().forEach(node -> {
                    Label label = (Label) node;
                    label.setFont(new Font(fontSize));
                });
            }
        });

        heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldHeight, Number newHeight) {
                double fontSize = newHeight.doubleValue() / 15;
                calendarGrid.getChildren().forEach(node -> {
                    Label label = (Label) node;
                    label.setFont(new Font(fontSize));
                });
            }
        });
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
            label.setMaxWidth(Double.MAX_VALUE);
            label.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(label, Priority.ALWAYS);
            GridPane.setHgrow(label, Priority.ALWAYS);
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