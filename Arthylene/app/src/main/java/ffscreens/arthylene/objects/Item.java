package ffscreens.arthylene.objects;

/**
 * Created by Thibault on 27/06/2017.
 */

public class Item {

    private Long id;
    private String title;
    private String content;
    private boolean important;
    private boolean checked;
    //private long idPhoto;

    public Item(String title, String content, boolean important, boolean checked) {
        this.title = title;
        this.content = content;
        this.important = important;
        this.checked = checked;
    }

    public Item(Long id, String title, String content, boolean important) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.important = important;
    }

    public Item(Long id, String title, String content, boolean important, boolean checked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.important = important;
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String toString() {
        return "id : " + getId() + ", title : " + getTitle() + ", content : " + getContent() +
                ", important : " + isImportant() + ", checked : " + isChecked();
    }
}
