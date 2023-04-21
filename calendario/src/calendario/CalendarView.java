package calendario;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;


public class CalendarView extends GridPane {

    private CalendarModel model;
    private Label monthLabel;
    private GridPane calendarGrid;
    private double fontSize;

    public CalendarView(CalendarModel model, double fontSize) {
        this.model = model;
        this.fontSize = fontSize;

        // Create the month label
        monthLabel = new Label();
        monthLabel.setFont(new Font(fontSize));
        monthLabel.setTextFill(Color.BLUE);
        monthLabel.setTextAlignment(TextAlignment.CENTER);
        GridPane.setHgrow(monthLabel, Priority.ALWAYS);

        // Create the grid pane that displays the days
        calendarGrid = new GridPane();
        calendarGrid.setPadding(new Insets(10));
        calendarGrid.setHgap(10);
        calendarGrid.setVgap(10);
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
        calendarGrid.getRowConstraints().clear();

        // Calculate the font size for the day labels
        double dayFontSize = 0.04 * Math.min(getWidth(), getHeight());

        // Add the days of the current month to the grid pane
        LocalDate date = model.getCurrentYearMonth().atDay(1);
        int row = 1;
        int col = date.getDayOfWeek().getValue() % 7;
        while (date.getMonth().equals(model.getCurrentYearMonth().getMonth())) {
            Label label = new Label(model.getDateString(date));
            label.setFont(new Font(dayFontSize));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setPrefWidth(Double.MAX_VALUE);
            label.setPrefHeight(Double.MAX_VALUE);
            label.setAlignment(Pos.CENTER);
            GridPane.setHgrow(label, Priority.ALWAYS);
            GridPane.setVgrow(label, Priority.ALWAYS);
            calendarGrid.add(label, col, row);
            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
            date = date.plusDays(1);
        }

        // Add row constraints to make all rows the same height
        for (int i = 0; i < row; i++) {
            RowConstraints constraints = new RowConstraints();
            constraints.setVgrow(Priority.ALWAYS);
            calendarGrid.getRowConstraints().add(constraints);
        }
    }

    public LocalDate getSelectedDate() {
        return model.getSelectedDate();
    }

}