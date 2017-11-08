package sirichai.lab09.kmitl.lab09;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by student on 11/3/2017 AD.
 */
@Entity(tableName = "MESSAGE_INFO")
class MessageInfo {
    @Override
    public String toString() {
        return String.format("%s:%s", time, text);
    }

    private String line;

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "text")
    private String text;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}
