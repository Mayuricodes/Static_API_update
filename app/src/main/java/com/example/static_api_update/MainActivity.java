package com.example.static_api_update;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText id,email,phoneno;
    RequestQueue requestQueue;
    JsonObjectRequest request;
    String url = "http://192.168.0.185:3001/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.userid);
        email = findViewById(R.id.email);
        phoneno = findViewById(R.id.phoneno);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedata();
            }
        });

    }

    private void updatedata() {

        JSONObject obj = new JSONObject();
        try {
            obj.put("email","mngcmnm");
            obj.put("phoneno","mcmjhmcj");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PATCH, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                Toast.makeText(MainActivity.this, "Updated", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                email.setText(error.toString());
            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String>header=new HashMap<>();
                header.put("User-Agent","Mozilla/5.0");
                return header;
            }
        };

        requestQueue.add(request);

    }
}