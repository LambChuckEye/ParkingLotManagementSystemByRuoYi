package com.ruoyi.custom;

/**
 * description
 *
 * @author Kamiu 2021/10/11 14:07
 */
public class Test {
    public static void main(String[] args) {
        String lineStr = "a b cb d";
        String [] arr = lineStr.split(" ");
        for (String i : arr){
            System.out.println(i);
        }
    }

}
