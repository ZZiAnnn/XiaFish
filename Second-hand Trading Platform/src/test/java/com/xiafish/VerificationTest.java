package com.xiafish;

import com.fasterxml.jackson.core.util.VersionUtil;
import com.xiafish.util.ValidationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VerificationTest{
    @Test
    public void genVerifaction(){
        for(int i=0;i<100;++i){
            System.out.println(ValidationUtils.generateVerificationCode());
        }
    }
}
