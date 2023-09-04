package Enum;

import lombok.Getter;
@Getter
public enum ETypeBook {
    Novels("Tiểu thuyết",1),
    ShortStories("Truyện ngắn",2),
    ScienceAndKnowledge("Kiến thức khoa học",3),
    SportsAndHealth("Sức khoẻ và thể thao",4),
    CultureAndHistory("Lịch sử Văn hoá",5),
    ArtsAndMusic("Âm nhạc nghệ thuật",6),
    Skills("Kỹ năng",7),
    ReligionAndSpirituality("Tôn giáo và tâm linh",8),
    TravelAndCuisine("Tôn giáo và tâm linh",9);

    private String name;
    private int id;

    ETypeBook(String name, int value) {
        this.name = name;
        this.id = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.id = value;
    }

    public static ETypeBook findByname(String name) {
        for (ETypeBook e : values()) {
            if ((e.getName().equals(name))) {
                return e;
            }
        }
        return null;
    }
}


