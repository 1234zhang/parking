package com.netplus.catpark.service.util.GeoHashUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Brandon.
 * @date 2019/8/18.
 * @time 13:29.
 */

public class GeoHashHelperUtil {
    private static final String[] BASE_32_LOOK_UP = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "b", "c", "d", "e", "f", "g", "h",
            "j", "k", "m", "n", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };
    private static double maxLat = 90;
    private static double minLat = -90;
    private static double maxLng = 180;
    private static double minLng = -180;

    private static int length = 20;

    private static double latUnit = (maxLat - minLat) / (1 << 20);
    private static double lngUnit = (maxLng - minLng) / (1 << 20);

    /**
     * @descript 将经度与纬度，根据范围进行划分，例如(90,-90),划分为(90,0),(0,-90)两个区间，
     *              如果在某个区间就设置为1，如果不在就设置为0。
     * @param min
     * @param max
     * @param value
     * @param list
     */
    private static void convert(double min, double max, double value, List<Character> list){
        // 判断是否已经划分了20次了
        if(list.size() > length - 1){
            return;
        }
        double mid = (max + min)/2;

        if(value >= mid){
            list.add('1');
            convert(mid, max, value, list);
        }else{
            list.add('0');
            convert(min, mid, value, list);
        }
    }

    /**
     * 将二进制字符串转换为base32字符串
     * @param str
     * @return
     */
    private static String base32Encode(final String str){
        String unit = "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i = i + 5) {
            unit = str.substring(i, i + 5);
            sb.append(BASE_32_LOOK_UP[convertToIndex(unit)]);
        }
        return sb.toString();
    }

    /**
     * 将5位二进制字符串转换为十进制数
     * @param unit
     * @return
     */
    private static int convertToIndex(String unit){
        int index = unit.length();
        int result = 0;
        for (int i = index - 1; i >= 0; i--) {
            result += unit.charAt(i) == '0' ? 0 : 1 << (index - 1 - i);
        }
        return result;
    }

    /**
     * 根据经度和纬度生成geohash值。
     * @param lat
     * @param lng
     * @return
     */
    public static String encode(double lat, double lng){
        List<Character> latList = new ArrayList<>();
        List<Character> lngList = new ArrayList<>();
        convert(minLat, maxLat, lat, latList);
        convert(minLng, maxLng, lng, lngList);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < latList.size(); i++) {
            //以奇数为纬度，偶数位为经度的顺序生成一个二进制字符串
            sb.append(lngList.get(i)).append(latList.get(i));
        }
        return base32Encode(sb.toString());
    }

    /**
     * 解决geohash边界问题。
     * @param lat
     * @param lng
     * @return
     */
    public static List<String> around(double lat, double lng){
        List<String> list = new ArrayList<String>();
        list.add(encode(lat, lng));
        list.add(encode(lat + latUnit, lng));
        list.add(encode(lat - latUnit, lng));
        list.add(encode(lat, lng + lngUnit));
        list.add(encode(lat, lng - lngUnit));
        list.add(encode(lat + latUnit, lng + lngUnit));
        list.add(encode(lat + latUnit, lng - lngUnit));
        list.add(encode(lat - latUnit, lng + lngUnit));
        list.add(encode(lat - latUnit, lng - lngUnit));
        return list;
    }

}
