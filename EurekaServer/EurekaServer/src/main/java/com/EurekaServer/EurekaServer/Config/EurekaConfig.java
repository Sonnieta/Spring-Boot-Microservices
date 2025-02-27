//package com.EurekaServer.EurekaServer.Config;
//
//import com.netflix.appinfo.ApplicationInfoManager;
//import com.netflix.appinfo.InstanceInfo;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class EurekaConfig {
//
//    @Bean
//    public InstanceInfo instanceInfo() {
//        return InstanceInfo.Builder.newBuilder()
//                .setAppName("EurekaServer")
//                .setIPAddr("127.0.0.1")
//                .setPort(8761)
//                .build();
//    }
//
//    @Bean
//    public ApplicationInfoManager applicationInfoManager(InstanceInfo instanceInfo) {
//        return new ApplicationInfoManager(null, instanceInfo);
//    }
//}