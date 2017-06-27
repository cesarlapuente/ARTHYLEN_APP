package ffscreens.arthylene.adapter;

/**
 * Created by Thibault on 27/06/2017.
 */

public class Item {

    private String title;
    private String content;
    private boolean important;
    private boolean checked;

    public Item(String title, String content, boolean important, boolean checked) {
        this.title = title;
        this.content = content;
        this.important = important;
        this.checked = checked;
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
}
