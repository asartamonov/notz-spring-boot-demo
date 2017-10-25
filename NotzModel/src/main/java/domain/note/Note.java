package domain.note;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "content")
    private String text;

    @Column(name = "local_date_time")
    //@Convert(converter = LocalDateTimeConverter.class) don't need it if autoApply=true on a converter
    private LocalDateTime dateTime;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
