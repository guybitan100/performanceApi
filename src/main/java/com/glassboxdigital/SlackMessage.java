package com.glassboxdigital;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.Slack;

import java.io.IOException;

public class SlackMessage {
    private static String myToken = "xoxb-41937843010-1223376955201-0JU9b879II949BUWR8VyWS3t";
    private static String channel = "@guy.bitan";


    public static void sendMessage(String msg) throws IOException, SlackApiException {
        Slack slack = Slack.getInstance();
        MethodsClient methods = slack.methods(myToken);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel(channel).
                        text(msg).
                        build();
        ChatPostMessageResponse response = methods.chatPostMessage(request);
    }

    public static void main(String args[]) throws Exception {
        SlackMessage sm = new SlackMessage();
    }
}
