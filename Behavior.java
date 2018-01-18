package com.ociweb.aws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ociweb.gl.api.*;
import com.ociweb.pronghorn.pipe.ChannelReader;

public class Behavior implements StartupListener, HTTPResponseListener {

    GreenCommandChannel cmd;
    HTTPSession session=new HTTPSession("a235s0ler27298.iot.us-east-1.amazonaws.com",8443);
    String path="/things/GreenLightningThing/shadow";

    public Behavior(GreenRuntime runtime)
    {
        cmd=runtime.newCommandChannel(NET_REQUESTER|DYNAMIC_MESSAGING);
    }

    @Override
    public boolean responseHTTP(HTTPResponseReader reader) {

        System.out.println("Status: "+reader.statusCode());
        System.out.println("type: "+reader.contentType());

        Payloadable payload=new Payloadable() {
            @Override
            public void read(ChannelReader reader) {
                System.out.println(reader.readUTFOfLength(reader.available()));
            }
        };

        reader.openPayloadData(payload);
        return true;
    }

    @Override
    public void startup() {
        cmd.httpGet(session,path);

    }
}