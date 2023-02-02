//package kr.co.jay.session.service;
//
//import com.google.auth.oauth2.GoogleCredentials;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.FirebaseOptions;
//import com.google.firebase.messaging.*;
//import jakarta.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class FCMService {
//
//    @Value("${fcm.key.path}")
//    private String FCM_PRIVATE_KEY_PATH;
//
//    @Value("${fcm.key.scope}")
//    private String fireBaseScope;
//
//    // fcm init 필수사항은 아님
//    @PostConstruct
//    public void init() {
//        try {
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(
//                            GoogleCredentials
//                                    .fromStream(new ClassPathResource(FCM_PRIVATE_KEY_PATH).getInputStream())
//                                    .createScoped(List.of(fireBaseScope)))
//                    .build();
//            if (FirebaseApp.getApps().isEmpty()) {
//                FirebaseApp.initializeApp(options);
//                log.info("Firebase application has been initialized");
//            }
//        } catch (IOException e) {
//            log.error(e.getMessage());
//
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//
//    // 알림 보내기
//    public void sendByTokenList(List<String> tokenList) {
//
//        // 'Notification(com.google.firebase.messaging.Notification.Builder)' has private access in 'com.google.firebase.messaging.Notification'
//
//        List<Message> messages = tokenList.stream().map(token -> Message.builder()
//                .putData("time", LocalDateTime.now().toString())
//                //.setNotification(new Notification("제목", "알림 내용"))
//                .setToken(token)
//                .build()).collect(Collectors.toList());
//
//        // 요청에 대한 응답을 받을 response
//        BatchResponse response;
//        try {
//
//            // 알림 발송
//            response = FirebaseMessaging.getInstance().sendAll(messages);
//
//            // 요청에 대한 응답 처리
//            if (response.getFailureCount() > 0) {
//                List<SendResponse> responses = response.getResponses();
//                List<String> failedTokens = new ArrayList<>();
//
//                for (int i = 0; i < responses.size(); i++) {
//                    if (!responses.get(i).isSuccessful()) {
//                        failedTokens.add(tokenList.get(i));
//                    }
//                }
//                log.error("List of tokens are not valid FCM token : " + failedTokens);
//            }
//        } catch (FirebaseMessagingException e) {
//            log.error("cannot send to memberList push message. error info : {}", e.getMessage());
//        }
//    }
//}