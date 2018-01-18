package com.ociweb.aws;

import com.ociweb.gl.api.*;
import com.ociweb.pronghorn.network.TLSCertificates;
public class GreenLightningAWS implements GreenApp
{

    String brokerHost="a235s0ler27298.iot.us-east-1.amazonaws.com";

    @Override
    public void declareConfiguration(Builder builder)
    {
        builder.enableTelemetry();
        builder.useNetClient(new TLSCertificates() {
            @Override
            public String keyStoreResourceName() {
                return "/certificate/cacertthencertwithkey.jks";
            }

            @Override
            public String trustStroreResourceName() {
                return "/certificate/myTrustStore.jks";
            }

            @Override
            public String keyStorePassword() {
                return "nopassword";
            }

            @Override
            public String keyPassword() {
                return "nopassword";
            }

            @Override
            public boolean trustAllCerts() {
                return false;
            }
        });

        System.out.println("**************Exit declare configuration***************\n");
    }
    @Override
    public void declareBehavior(GreenRuntime runtime)
    {

        System.out.println("*****************Enter declare behavior****************");
        Behavior behavior=new Behavior(runtime);
        runtime.addResponseListener(behavior);
       System.out.println("*****************Exit declare behavior****************");
    }
}
