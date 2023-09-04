package model;
import Enum.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCard {
    private long id;
    private long idUser;
    private ETypeCard typeCard;
    private LocalDate createDate;
    private EPeriod period;

    @Override
    public String toString() {
        return this.getId() +","+ this.getIdUser() +","+ this.getTypeCard().getName() +","+ this.getCreateDate() +","+ this.getPeriod().getPeriod()+"\n";
    }
}
