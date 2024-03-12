package com.cyber.university.utils;

/**
 * 
  * @FileName : TempPassword.java
  * @Project : CyberUniversity
  * @Date : 2024. 3. 12. 
  * @작성자 : 이준혁
  * @변경이력 :
  * @프로그램 설명 : 임시비밀번호 생성기
 */
public class TempPassword {
    public String returnTempPassword() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            password.append((int) (Math.random() * 10));
        }
        return password.toString();
    }
}

