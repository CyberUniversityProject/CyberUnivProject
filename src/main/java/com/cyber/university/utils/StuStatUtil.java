package com.cyber.university.utils;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.cyber.university.handler.exception.CustomRestfullException;
import com.cyber.university.repository.model.Break;
import com.cyber.university.repository.model.StuStat;

public class StuStatUtil {


    public static void checkStuStat(String type, StuStat stuStatEntity, List<Break> breakAppList) {
        if (isGraduatedOrWithdrawn(stuStatEntity)) {
            throwError(type, Define.REGISTRATION_ERROR_MESSAGE, Define.ENROLLMENT_ERROR_MESSAGE);
        }

        for (Break b : breakAppList) {
            if (isApprovedBreak(b)) {
                if (isFutureBreak(b) || isCurrentYearAndSemester(b)) {
                    throwError(type, Define.REGISTRATION_ERROR_MESSAGE, Define.ENROLLMENT_ERROR_MESSAGE);
                }
            }
        }
    }

    private static boolean isGraduatedOrWithdrawn(StuStat stuStatEntity) {
        String status = stuStatEntity.getStatus();
        return status.equals(Define.GRADUATED_STATUS) || status.equals(Define.WITHDRAWN_STATUS);
    }

    private static boolean isApprovedBreak(Break b) {
        return b.getStatus().equals("승인");
    }

    private static boolean isFutureBreak(Break b) {
        return b.getToYear() > Define.CURRENT_YEAR;
    }

    private static boolean isCurrentYearAndSemester(Break b) {
        return b.getToYear() == Define.CURRENT_YEAR && b.getToSemester() >= Define.CURRENT_SEMESTER;
    }

    private static void throwError(String type, String registrationErrorMessage, String enrollmentErrorMessage) {
        if (type.equals("등록금")) {
            throw new CustomRestfullException(registrationErrorMessage, HttpStatus.BAD_REQUEST);
        } else if (type.equals("수강신청")) {
            throw new CustomRestfullException(enrollmentErrorMessage, HttpStatus.BAD_REQUEST);
        }
    }
}
