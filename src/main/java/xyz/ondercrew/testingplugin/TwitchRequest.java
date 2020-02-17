package xyz.ondercrew.testingplugin;

import org.bukkit.ChatColor;
import org.json.JSONObject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bukkit.command.CommandSender;

public class TwitchRequest {
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    public boolean TwitchCommand (CommandSender sender, String[] args) {
        SuccessAndResult twitchId = getUserInfoByUserName(args[0]);
        if (twitchId.success) {
            SuccessAndResult channelInfo = getInfoById("channels", twitchId.result.getString("_id"));
            SuccessAndResult streamInfo = getInfoById("streams", twitchId.result.getString("_id"));
            if (channelInfo.success && streamInfo.success) {
                boolean isStreaming = !streamInfo.result.isNull("stream");
                if (isStreaming) streamInfo = new SuccessAndResult(streamInfo.result.getJSONObject("stream"));
                String str = ChatColor.LIGHT_PURPLE + "\n" + ChatColor.BOLD + channelInfo.result.getString("display_name") + "\n"
                            + ChatColor.RESET + ChatColor.WHITE + channelInfo.result.getString("description") + "\n====================\n"
                            + (isStreaming ? ChatColor.GREEN + "방송중: " : ChatColor.GRAY + "방송 오프라인: ") + (channelInfo.result.isNull("status") ?
                                "제목 미설정" : channelInfo.result.getString("status")) + "\n"
                            + ChatColor.RESET + ChatColor.WHITE + "게임/소통: " + ChatColor.BOLD + (channelInfo.result.isNull("game") ?
                                "게임/소통 미선택" : (isStreaming ? streamInfo.result.getString("game") : channelInfo.result.getString("game"))) + "\n"
                            + ChatColor.RESET + "팔로워: " + ChatColor.BOLD + channelInfo.result.getNumber("followers") + "명\n"
                            + ChatColor.RESET + "총 시청수: " + ChatColor.BOLD + channelInfo.result.getNumber("views")
                            + (isStreaming ? ChatColor.RESET + ", 현재 시청자: " + ChatColor.BOLD + streamInfo.result.getNumber("viewers") + "명" : "") + "\n"
                            + (isStreaming ? ChatColor.RESET + "방송 평균 FPS: " + ChatColor.BOLD + streamInfo.result.getNumber("average_fps") + ChatColor.RESET
                            + ", 방송 딜레이: " + ChatColor.BOLD + streamInfo.result.getNumber("delay") : "");
                sender.sendMessage(str);
            } else {
                sender.sendMessage(ChatColor.RED + args[0] + "(" + twitchId.result.getString("id") + ")의 정보를 구하려고 했는데, 트위치가 삐져서 안해줍니다");
            }
        } else sender.sendMessage(ChatColor.RED + args[0] + "이라는 계정을 찾을 수 없습니다! 손가락이 존재하는지 확인해보세요");
        return true;
    }

    private SuccessAndResult getUserInfoByUserName (String username) {
        HttpGet username2idRequest = new HttpGet("https://api.twitch.tv/kraken/users?login=" + username);
        username2idRequest.addHeader("Accept", "application/vnd.twitchtv.v5+json");
        username2idRequest.addHeader("Client-ID", System.getenv("twitchClient")); // TODO config 파일로 트위치 클라ID 받게 바꾸기 = [1]

        try (CloseableHttpResponse res = httpClient.execute(username2idRequest)) {
            JSONObject resObj = new JSONObject(EntityUtils.toString(res.getEntity()));
            if (resObj.getJSONArray("users").length() > 0) {
                JSONObject result = resObj.getJSONArray("users").optJSONObject(0);
                return new SuccessAndResult(result);
            } else return new SuccessAndResult();
        } catch (Exception e) {
            return new SuccessAndResult();
        }
    }

    private SuccessAndResult getInfoById (String type, String id) {
        HttpGet username2idRequest = new HttpGet("https://api.twitch.tv/kraken/" + type + "/" + id);
        username2idRequest.addHeader("Accept", "application/vnd.twitchtv.v5+json");
        username2idRequest.addHeader("Client-ID", System.getenv("twitchClient")); // TODO [1]

        try (CloseableHttpResponse res = httpClient.execute(username2idRequest)) {
            JSONObject result = new JSONObject(EntityUtils.toString(res.getEntity()));
            return new SuccessAndResult(result);
        } catch (Exception e) {
            return new SuccessAndResult();
        }
    }
}