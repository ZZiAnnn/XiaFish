package com.xiafish.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Random;

//该工具类用于校验用户的邮箱和电话号码是否正确
public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        // 邮箱正则表达式
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // 校验邮箱格式
        return email.matches(emailRegex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // 电话号码正则表达式
        String phoneRegex = "^(\\+\\d{1,3})?\\d{7,15}$";

        // 校验电话号码格式
        return phoneNumber.matches(phoneRegex);
    }

    public static String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000; // 生成一个100000到999999之间的随机数
        return String.valueOf(code);
    }
}
