package xyz.ondercrew.testingplugin;

import net.dv8tion.jda.api.JDA;
import org.json.JSONObject;

public class SuccessAndResult {
    public boolean success;
    public JSONObject jsonObject;
    public JDA jda;

    public SuccessAndResult () {
        this.success = false;
    }

    public SuccessAndResult (boolean success) {
        this.success = success;
    }

    public SuccessAndResult (boolean success, JSONObject object) {
        this.success = success;
        this.jsonObject = object;
    }

    public SuccessAndResult (JSONObject object) {
        this.success = true;
        this.jsonObject = object;
    }

    public SuccessAndResult (boolean success, JDA jda) {
        this.success = success;
        this.jda = jda;
    }

    public SuccessAndResult (JDA jda) {
        this.success = true;
        this.jda = jda;
    }
}
