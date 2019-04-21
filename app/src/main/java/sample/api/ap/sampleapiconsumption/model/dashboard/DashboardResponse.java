package sample.api.ap.sampleapiconsumption.model.dashboard;

import java.util.ArrayList;

public class DashboardResponse {
    private String status_code;
    private String status_message;
    private ArrayList<UserData> user_data;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public ArrayList<UserData> getUser_data() {
        return user_data;
    }

    public void setUser_data(ArrayList<UserData> user_data) {
        this.user_data = user_data;
    }
}
