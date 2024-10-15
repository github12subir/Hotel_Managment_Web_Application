package com.AirBnb.service.Impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender {

    @Value("${twilio.accountId}")
    private String accountId;

    @Value("${twilio.authToken}")
    private String authToken;

    @Value("${twilio.twilioPhNumber}")
    private String twilioPhNumber;

    public void sendSms(String to, String message){
        Twilio.init(accountId,authToken);
        Message.creator(new PhoneNumber(to), new PhoneNumber(twilioPhNumber), message).create();
    }
}
