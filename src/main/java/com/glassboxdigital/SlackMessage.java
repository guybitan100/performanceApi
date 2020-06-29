package com.glassboxdigital;

import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.Slack;
import com.slack.api.model.Attachment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SlackMessage {

    public SlackMessage() throws IOException, SlackApiException {
        Slack slack = Slack.getInstance();
        String token = "xoxb-41937843010-1223376955201-0JU9b879II949BUWR8VyWS3t";
        MethodsClient methods = slack.methods(token);
        Attachment attachment = new Attachment();
        attachment.setTitle("test attachment title");
        attachment.setColor("good");
        attachment.setText("test attachment text");
        attachment.setTitle("Guy");
        List<Attachment> listOfAttachment = new ArrayList<Attachment>();
        listOfAttachment.add(attachment);
        ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                .channel("@guy.bitan").
                        attachments(listOfAttachment).
                        build();
        ChatPostMessageResponse response = methods.chatPostMessage(request);
        System.out.println(response.getError());

    }

    public static void main(String args[]) throws Exception {
        SlackMessage sm = new SlackMessage();
    }
}
