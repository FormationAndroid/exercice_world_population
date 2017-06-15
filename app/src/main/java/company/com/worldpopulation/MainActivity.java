package company.com.worldpopulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ListView listView  = (ListView) findViewById(R.id.listView);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getApplicationContext(), "http://api.population.io:80/1.0/countries", new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                ArrayList<String> stringArray = new ArrayList<String>();
                for(int i = 0, count = response.optJSONArray("countries").length(); i< count; i++) {
                    stringArray.add(response.optJSONArray("countries").optString(i));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                        R.layout.item_select_country, R.id.textview_country, stringArray);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                        // CHANGER d'ACTIVITE, envoyer pays en extra

                    }
                });

            }

        });

    }
}
