package com.ociweb.aws;

import com.ociweb.gl.api.*;
import com.ociweb.pronghorn.network.TLSCertificates;
public class GreenLightningAWS implements GreenApp
{

    String brokerHost="<your API Endpoint>";
    @Override
    public void declareConfiguration(Builder builder)
    {
        builder.enableTelemetry();
        builder.useNetClient(new TLSCertificates() {
        //put all your certs in the resources folder
            @Override
            public String keyStoreResourceName() {
                return "<path to your keystore>"; //Keystore has the cert and private key from AWS
            }

            @Override
            public String trustStroreResourceName() {
                return "<path to your truststore>"; //Truststore has the rootCA from AWS
            }

            @Override
            public String keyStorePassword() {
                return "< your keystore password>";
            }

            @Override
            public String keyPassword() {
                return "<your truststore password>";
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
