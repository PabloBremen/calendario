package calendario;
import java.time.LocalDate;

public class Appointment {
    
    private LocalDate date;
    private String description;
    
    public Appointment(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public String getDescription() {
        return description;
    }
    
}
