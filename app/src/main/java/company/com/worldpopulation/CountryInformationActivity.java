package company.com.worldpopulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class CountryInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_information);

        final TextView txtview = (TextView) findViewById(R.id.textview_country_info);

        String url = "http://api.population.io:80/1.0/population/2017/"
                + getIntent().getExtras().getString("country") + "/";

        txtview.setText("Wait please...");

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(getApplicationContext(), url, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                int population = 0;

                for(int i = 100; i >= 0; i--) {
                    population = population + Integer.parseInt(response.optJSONObject(i).optString("total"));
                }
                txtview.setText(getIntent().getExtras().getString("country") + " population : " + population);

            }
        });

    }
}
