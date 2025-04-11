package com.hanhwa_tae.gulhan.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

/* 임시 비밀번호 생성기*/
@Component
public class RandomStringGenerator {

    public String randomStringGenerator(int size){
        String possible = "abcdefghijklmnoqprstyvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i =0 ; i < size; i++){
            int idx = random.nextInt(possible.length());
            sb.append(possible.charAt(idx));
        }

        return sb.toString();
    }
}
