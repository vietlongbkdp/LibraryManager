package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookActive {
    private long id;
    private long bookID;
    private int quantity;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean status;

    @Override
    public String toString() {
        return id +","+ bookID +","+ quantity +","+ borrowDate +","+ returnDate +","+ status;
    }
}
