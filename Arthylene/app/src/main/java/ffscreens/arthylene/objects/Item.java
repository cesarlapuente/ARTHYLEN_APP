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
    private long idPhoto;

    public Item(String title, String content, boolean important, boolean checked, Long idPhoto) {
        this.title = title;
        this.content = content;
        this.important = important;
        this.checked = checked;
        this.idPhoto = idPhoto;
    }

    public Item(Long id, String title, String content, boolean important, Long idPhoto) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.important = important;
        this.idPhoto = idPhoto;
    }

    public Item(Long id, String title, String content, boolean important, boolean checked, Long idPhoto) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.important = important;
        this.checked = checked;
        this.idPhoto = idPhoto;
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

    public long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String toString() {
        return "id : " + getId() + ", title : " + getTitle() + ", content : " + getContent() +
                ", important : " + isImportant() + ", checked : " + isChecked() + ", idPhoto : " +
                getIdPhoto();
    }

    @Override
    public boolean equals(Object obj) {
        Item item = (Item) obj;
        return item != null && getId().equals(item.getId());
    }
}
