package com.shihaiyang.world.effective_java.exception;

import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/*
69:不要使用异常来作为控制流
比如一个集合
 */
@Slf4j
public class Effective69 {
    public static void main(String[] args) {
        int[] range = new int[100];
        printArray(range);
        printArrayAccept(range);
        List<String> strings = List.of("a", "b", "c");
        iterList(strings);
        iterListAccept(strings);
    }

    private static void printArray(int[] range) {
        try {
            int i = 0;
            while (true) {
                System.out.println(range[i++]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.error("ArrayIndexOutOfBoundsException");
        }
    }
    private static void printArrayAccept(int[] range) {
        for (int i : range) {
            System.out.println(i);
        }
    }

    private static void iterList(List<String> strings) {
        try {
            Iterator<String> iterator = strings.iterator();
            while (true) {
                System.out.println(iterator.next());
            }
        } catch (NoSuchElementException e) {
            log.error("NoSuchElementException");
        }
    }

    private static void iterListAccept(List<String> strings) {
        for (Iterator<String> i = strings.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
        }
    }
}
