package calendario;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalendarModel {
    
	private YearMonth currentYearMonth;
    private LocalDate selectedDate;
    private Map<LocalDate, List<Appointment>> appointments;
    
    public CalendarModel() {
        currentYearMonth = YearMonth.now();
        selectedDate = LocalDate.now();
    }
    
    public void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
    }
    
    public void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
    }
    
    public YearMonth getCurrentYearMonth() {
        return currentYearMonth;
    }
    
    public String getMonthYearString() {
        return currentYearMonth.getMonth().toString() + " " + currentYearMonth.getYear();
    }
    
    public String getDateString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d");
        return date.format(formatter);
    }
    
    public void setSelectedDate(LocalDate date) {
        selectedDate = date;
    }
    
    public LocalDate getSelectedDate() {
        return selectedDate;
    }
    
    public void addAppointment(LocalDate date, String description) {
        // Create a new Appointment object with the given date and description
        Appointment appointment = new Appointment(date, description);
        
        // Add the appointment to the list of appointments for that date
        appointments.computeIfAbsent(date, k -> new ArrayList<>()).add(appointment);
    }
    public List<Appointment> getAppointments(LocalDate date) {
        return appointments.getOrDefault(date, new ArrayList<>());
    }
}
