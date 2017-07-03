package ffscreens.arthylene.objects;

import android.content.Context;

/**
 * Created by Thibault on 30/06/2017.
 */

public class ItemPlacement {

    private String title;
    private int image;
    private boolean selected;

    public ItemPlacement(String title, int image, boolean selected, Context context) {
        this.title = title;
        this.image = image;
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
