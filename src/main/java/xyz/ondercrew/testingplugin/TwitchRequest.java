package xyz.ondercrew.testingplugin;

import org.json.JSONObject;
import net.md_5.bungee.api.ChatColor;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bukkit.command.CommandSender;

public class TwitchRequest {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    public boolean TwitchCommand (CommandSender sender, String[] args) {
        SuccessAndResult twitchId = username2id(args[0]);
        if (twitchId.success) {
            sender.sendMessage(ChatColor.GREEN + "트위치 계정을 찾았습니다! : " + twitchId.result);
        } else sender.sendMessage(ChatColor.RED + args[0] + "이라는 계정을 찾을 수 없습니다!");
        return true;
    }

    private SuccessAndResult username2id (String username) {
        HttpGet username2idRequest = new HttpGet("https://api.twitch.tv/kraken/users?login=" + username);
        username2idRequest.addHeader("Accept", "application/vnd.twitchtv.v5+json");
        username2idRequest.addHeader("Client-ID", System.getenv("twitchClient")); // TODO config 파일로 트위치 클라ID 받게 바꾸기

        try (CloseableHttpResponse res = httpClient.execute(username2idRequest)) {
            JSONObject resObj = new JSONObject(EntityUtils.toString(res.getEntity()));
            if (resObj.getJSONArray("users").length() > 0) {
                String id = resObj.getJSONArray("users").optJSONObject(0).getString("_id");
                return new SuccessAndResult(id);
            } else return new SuccessAndResult();
        } catch (Exception e) {
            System.err.println(e);
            return new SuccessAndResult();
        }
    }
}