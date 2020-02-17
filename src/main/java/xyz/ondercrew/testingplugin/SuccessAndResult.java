package xyz.ondercrew.testingplugin;

public class SuccessAndResult {
    public boolean success;
    public String result;

    public SuccessAndResult (String result) {
        this.success = true;
        this.result = result;
    }

    public SuccessAndResult () {
        this.success = false;
        this.result = "Failed";
    }

    public SuccessAndResult (boolean success, String result) {
        this.success = success;
        this.result = result;
    }
}
