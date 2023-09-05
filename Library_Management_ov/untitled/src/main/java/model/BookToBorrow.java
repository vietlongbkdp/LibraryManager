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
public class BookToBorrow {
    private long id;
    private long bookID;
    private long idUser;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private boolean status;

    @Override
    public String toString() {
        return id +","+ bookID +","+ idUser +","+ borrowDate +","+ returnDate +","+ status +"\n";
    }
}
