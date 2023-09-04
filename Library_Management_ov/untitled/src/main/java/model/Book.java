package model;

import Enum.EShelf;
import Enum.ETypeBook;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    protected long id;
    protected String bookName;
    protected String author;
    protected String publisher;
    protected boolean status;
    protected EShelf shelf;
    protected ETypeBook eTypeBook;
    private double price;
    private String description;
    private LocalDate dateAdd;

    @Override
    public String toString() {
        return this.getId() + "," + this.getBookName() + "," + this.getAuthor()
                + "," + this.getPublisher()+ "," + this.isStatus()+","
                + this.getShelf().getName() + "," + this.getETypeBook().getName() + "," + this.getPrice()
                + "," + this.getDescription() + "," + this.getDateAdd() + "\n";
    }
}
