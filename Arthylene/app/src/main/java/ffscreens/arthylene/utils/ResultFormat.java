package ffscreens.arthylene.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ffscreens.arthylene.objects.ScanResult;

public abstract class ResultFormat
{

    /**
     * This function format the string from the CVC lib to an array of ScanResult object
     *
     * @param message : the String that the CVC lib return
     * @return A nice array with the different result from the lib
     */
    public static ArrayList<ScanResult> stringToScanResultArray(String message)
    {
        ArrayList<ScanResult> results = new ArrayList<>();
        ScanResult result;

        try
        {
            JSONArray array = new JSONArray(message);
            StringBuilder resBuilder = new StringBuilder();
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                resBuilder.append(object.opt("value")).append(" : ").append(object.opt("confidence")).append("\n");

                //select and put the result result array
                String name;
                Integer mat;

                if(!object.opt("value").toString().equals("UNKNOWN"))
                {
                    String concatResult = object.opt("value").toString();
                    String[] separated = concatResult.split(" ");

                    name = separated[0];
                    mat = Integer.parseInt(separated[1].replaceAll("[^0-9]", ""));
                }
                else
                {
                    name = "UNKNOWN";
                    mat = 0;
                }

                //can set here the product name with resources file but link with arthylene web will be break
                result = new ScanResult(name, mat, Float.parseFloat(object.opt("confidence").toString()));
                results.add(result);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return results;
    }
}