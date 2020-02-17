package xyz.ondercrew.testingplugin;

import org.json.JSONObject;

public class SuccessAndResult {
    public boolean success;
    public JSONObject result;

    public SuccessAndResult (JSONObject result) {
        this.success = true;
        this.result = result;
    }

    public SuccessAndResult () {
        this.success = false;
    }

    public SuccessAndResult (boolean success, JSONObject result) {
        this.success = success;
        this.result = result;
    }
}
