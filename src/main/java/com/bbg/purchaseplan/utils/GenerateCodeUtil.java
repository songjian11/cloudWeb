package com.bbg.purchaseplan.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 生成唯一编码
 * songjian
 * 20191107
 */
@Slf4j
@Component
public class GenerateCodeUtil {
//    @Value("${containId}")
//    private String containId;
    //容器码
    private static String containCode;
    private static final String STR_FORMAT = "000";
    private static int sequence = 0;
    private static DateFormat df = new SimpleDateFormat("yyMMdd");
    private static DecimalFormat dcf = new DecimalFormat(STR_FORMAT);
    private static long lastTimestamp = -1L;

    public static String generateBBgCode(String depotCode, String seq) {
        StringBuilder sb = new StringBuilder();
        sb.append(depotCode);
        int len = 7 - seq.length();
        for(int i = 1; i <= len; i++){
            sb.append("0");
        }
        sb.append(seq);
        return sb.toString();
    }

//    @PostConstruct
//    public void setContainCode() {
//        this.containCode = containId;
//    }

    /**
     * @Description: 编号统一生成方法,18位编码
     * @author songjian
     * @return
     */
//    public static synchronized String generateBBgCode() {
//        StringBuilder sb = new StringBuilder();
//        Long timestamp = System.currentTimeMillis() / 1000;
//        if (lastTimestamp == timestamp) {
//            // 当前秒内，则+1
//            sequence = sequence + 1;
//            if (sequence > 999) {
//                sequence = 0;
//                timestamp = tilNextMillis(lastTimestamp);
//            }
//        } else {
//            sequence = 0;
//        }
//        // 业务前缀：xxx
////        sb.append(subType);
//        // 时间戳：170422155201
//        sb.append(df.format(new Date()));
//        sb.append(containCode);
//        // 自增数：xxx
//        sb.append(dcf.format(sequence));
//        lastTimestamp = timestamp;
//        return sb.toString();
//    }

    /**
     * @Description: 循环等待至下一秒
     * @author songjian
     * @param lastTimestamp
     * @return
     */
//    private static Long tilNextMillis(final Long lastTimestamp) {
//        Long timestamp = System.currentTimeMillis() / 1000;
//        while (timestamp <= lastTimestamp) {
//            timestamp = System.currentTimeMillis() / 1000;
//        }
//        return timestamp;
//    }

    /**
     *
     * @Description: 获取唯一编号 (短) 时间戳转为36进制,13位
     * @author songjian
     * @return Q0LE1VP001002
     */
//    public static String getHexBBgCode(){
//        Long timestamp = System.currentTimeMillis() / 1000;
//        if (lastTimestamp == timestamp) {
//            // 当前秒内，则+1
//            sequence = sequence + 1;
//            if (sequence > 999) {
//                sequence = 0;
//                timestamp = tilNextMillis(lastTimestamp);
//            }
//        } else {
//            sequence = 0;
//        }
//
//        StringBuilder sb = new StringBuilder();
//        // 时间戳：170422155201
//        sb.append(Long.toString(timestamp, 36));
//        sb.append(containCode);
//        // 自增数：xxx
//        sb.append(dcf.format(sequence));
//
//        lastTimestamp = timestamp;
//        return sb.toString().toUpperCase();
//    }
}
