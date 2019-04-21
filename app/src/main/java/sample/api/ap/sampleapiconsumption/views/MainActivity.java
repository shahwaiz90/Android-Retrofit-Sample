package sample.api.ap.sampleapiconsumption.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Response;
import sample.api.ap.sampleapiconsumption.R;
import sample.api.ap.sampleapiconsumption.model.dashboard.DashboardResponse;
import sample.api.ap.sampleapiconsumption.model.login.LoginRequest;
import sample.api.ap.sampleapiconsumption.model.login.LoginResponse;
import sample.api.ap.sampleapiconsumption.model.verifyemail.VerifyEmailRequest;
import sample.api.ap.sampleapiconsumption.model.verifyemail.VerifyEmailResponse;
import sample.api.ap.sampleapiconsumption.network.CentralApis;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://webhook.site/ Create Custom Responses, Check Your Post or Body requests.

    }

    public void Login(View view) {

        final LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("ahmadshahwaiz");
        loginRequest.setPassword("123");

        String url              = "https://webhook.site/d3e10c5c-5b56-45c3-8ace-21da97edb687";
        String headerLangInfo   = "us";

        Log.i(TAG, "Thread Id Before Calling: "+Thread.currentThread().getId());


        CentralApis.getInstance().getAPIS().loginRequest(url, headerLangInfo, loginRequest).enqueue(new retrofit2.Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(TAG, "Response HTTP Code: "+response.code());
                Log.e(TAG, "Response Whole Body: "+response.body());
                Log.e(TAG, "Thread Id on Response: "+Thread.currentThread().getId());

                if(response.code() == 200) {
                    //Manual Mapping via Gson Library....We can also mention in the API callback 'LoginResponse' for auto mapping.
                    Gson gson = new Gson();
                    //That's it, response is mapped in the LoginResponse class
                    LoginResponse loginResponse = gson.fromJson(response.body(), LoginResponse.class);

                    Log.e(TAG, "\nStatus_message: " + loginResponse.getStatus_message());
                    Log.e(TAG, "\nToken: " + loginResponse.getToken());
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "Response: Failure");
                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });

    }

//  DASHBOARD RESPONSE
//  {
//        "status_code": 200,
//            "status_message": "Loading...",
//            "user_data":[{
//        "username": "ahmad",
//                "age": 24,
//                "university": "fast"
//    },{
//        "username": "shahwaiz",
//                "age": 26,
//                "university": "pucit"
//    }]
//    }


    public void loadDashboard(View view) {
        String url              = "https://webhook.site/843fa19d-ee93-4a00-b108-be3bcd486cb6";
        String headerLangInfo   = "us";
        String token            = "askndlasndlasmd";

        Log.i(TAG, "Thread Id Before Calling: "+Thread.currentThread().getId());

        CentralApis.getInstance().getAPIS().loadDashboard(url, "Bearer "+token, headerLangInfo).enqueue(new retrofit2.Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(TAG, "Response HTTP Code: "+response.code());
                Log.e(TAG, "Response Whole Body: "+response.body());
                Log.e(TAG, "Thread Id on Response: "+Thread.currentThread().getId());

                if(response.code() == 200) {
                    //Manual Mapping via Gson Library....We can also mention in the API callback 'LoginResponse' for auto mapping.
                    Gson gson = new Gson();
                    //That's it, response is mapped in the LoginResponse class
                    DashboardResponse dashboardResponse = gson.fromJson(response.body(), DashboardResponse.class);

                    Log.e(TAG, "\nStatus_message: " + dashboardResponse.getStatus_message());
                    for(int i=0; i < dashboardResponse.getUser_data().size(); i++){
                        Log.e(TAG, "UserName: "+dashboardResponse.getUser_data().get(i).getUsername());
                        Log.e(TAG, "University: "+dashboardResponse.getUser_data().get(i).getUniversity());
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "Response: Failure");
                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }



    //Verify Email JSON Response
//    {
//        "status_code": 200,
//            "status_message": "Email Verified!"
//    }
    public void verifyEmail(View view) {
        final VerifyEmailRequest verifyEmailRequest = new VerifyEmailRequest();
        verifyEmailRequest.setEmail("ahmadshahwaiz@gmail.com");

        String url              = "https://webhook.site/6056e8fa-c239-436f-9b98-fca81d32d744";
        String headerLangInfo   = "us";

        Log.i(TAG, "Thread Id Before Calling: "+Thread.currentThread().getId());

        CentralApis.getInstance().getAPIS().verifyEmail(url, headerLangInfo, verifyEmailRequest).enqueue(new retrofit2.Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.e(TAG, "Response HTTP Code: "+response.code());
                Log.e(TAG, "Response Whole Body: "+response.body());
                Log.e(TAG, "Thread Id on Response: "+Thread.currentThread().getId());

                if(response.code() == 200) {
                    //Manual Mapping via Gson Library....We can also mention in the API callback 'LoginResponse' for auto mapping.
                    Gson gson = new Gson();
                    //That's it, response is mapped in the LoginResponse class
                    VerifyEmailResponse verifyEmailResponse = gson.fromJson(response.body(), VerifyEmailResponse.class);

                    Log.e(TAG, "\nStatus_message: " + verifyEmailResponse.getStatus_message());
                    Log.e(TAG, "\nToken: " + verifyEmailResponse.getStatus_message());
                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e(TAG, "Response: Failure");
                Toast.makeText(getApplicationContext(), "Something went wrong.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
