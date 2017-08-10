package ffscreens.arthylene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Arthylene
 * Created by Thibault Nougues on 21/06/2017.
 */

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button shelving = findViewById(R.id.shelving);
        Button reception = findViewById(R.id.rec);

        shelving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ShelvingActivity.class));
            }
        });

        reception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, ReceptionActivity.class));
            }
        });
    }
}
