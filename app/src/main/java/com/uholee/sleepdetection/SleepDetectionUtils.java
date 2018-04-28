package com.uholee.sleepdetection;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by uhole on 2018-04-28.
 */

public class SleepDetectionUtils {
    private static SleepDetectionUtils mObject;

    public static boolean checkSleep(ArrayList<Point> face) {
        return mObject.checkSleep_method(face);
    }
    private boolean checkSleep_method(ArrayList<Point> face) {
        ArrayList<Point> leftEye = new ArrayList<Point>();  // 왼쪽 눈 포인트들의 좌표
        ArrayList<Point> rightEye = new ArrayList<Point>(); // 오른쪽 눈 포인트들의 좌표

        double leftEar, rightEar;   //

        // leftEye 초기화
        for (int i = 36; i < 42; ++i) {
            Point p = face.get(i);
            leftEye.add(p);
        }
        // rightEye 초기화
        for (int i = 42; i < 48; ++i) {
            Point p = face.get(i);
            rightEye.add(p);
        }

        rightEar = computeEar(rightEye);
        leftEar = computeEar(leftEye);

        if ((rightEar + leftEar) / 2 < 0.2) {
            // 잔다
            return true;
        }
        else {
            // 안 잔다
            return false;
        }
    }

    double computeEar(ArrayList<Point> eye) {
        double a = (eye.get(1).x - eye.get(5).x)*(eye.get(1).x - eye.get(5).x) + (eye.get(1).y - eye.get(5).y)*(eye.get(1).y - eye.get(5).y);
        double b = (eye.get(2).x - eye.get(4).x)*(eye.get(2).x - eye.get(4).x) + (eye.get(2).y - eye.get(4).y)*(eye.get(2).y - eye.get(4).y);
        double c = (eye.get(0).x - eye.get(3).x)*(eye.get(0).x - eye.get(3).x) + (eye.get(0).y - eye.get(3).y)*(eye.get(0).y - eye.get(3).y);

        double ear = (Math.sqrt(a) + Math.sqrt(b)) / (2.0 + Math.sqrt(c));
        return ear;
    }
}
