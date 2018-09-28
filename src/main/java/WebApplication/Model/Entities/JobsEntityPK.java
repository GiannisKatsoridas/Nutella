package WebApplication.Model.Entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.sql.Date;

public class JobsEntityPK implements Serializable {

    private long id;
    private Date date;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @Column(name = "Date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobsEntityPK that = (JobsEntityPK) o;
        return id == that.id &&
                date == that.date;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date);
    }


}
