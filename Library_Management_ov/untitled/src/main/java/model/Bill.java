package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private long id;
    private long idCard;
    private long idListBookToBorrow;

    @Override
    public String toString() {
        return  id +","+ idCard +","+ idListBookToBorrow + "\n";
    }
}
