package whereabout.mrunal_sonal.com.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> picNameArr;
        ArrayList<String> picArr;

        Intent intent=getIntent();
        picNameArr=(ArrayList<String>)getIntent().getSerializableExtra("title");
        picArr=(ArrayList<String>)getIntent().getSerializableExtra("imgurl");

        ListView listViewPicText=(ListView) findViewById(R.id.listViewPicText);

        General_SelectMenu_WithPicsName_Adapter adapter = new General_SelectMenu_WithPicsName_Adapter(
                MainActivity.this, picNameArr,picArr);

        listViewPicText.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

