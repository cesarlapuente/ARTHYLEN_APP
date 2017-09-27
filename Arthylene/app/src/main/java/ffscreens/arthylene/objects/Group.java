package ffscreens.arthylene.objects;


import java.util.ArrayList;
import java.util.List;

public class Group
{
    public String string;
//    public Object object = new Object();
    public final List<String> children = new ArrayList<>();

    public Group(String string)
    {
        this.string = string;
    }

//    public Object getobject() {
//        return object;
//    }
//
//    public void setObject(Object children) {
//        this.object = object;
//    }
}
