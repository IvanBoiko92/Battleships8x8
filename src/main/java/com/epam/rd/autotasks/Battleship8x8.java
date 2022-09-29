package com.epam.rd.autotasks;
import  java.math.*;
public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public int findIndex(String shot) {     // метод для получения индекса выстрела
        int j = shot.charAt(0) - 'A' + 1;   // номер столбца
        int i = shot.charAt(1) -48;         // номер рядка
        return (64 - ((i-1)*8 + j));
    }

    public boolean shoot(String shot) {
        int index = findIndex(shot);
        BigInteger map = BigInteger.valueOf(ships);
        BigInteger aim = BigInteger.valueOf(shots);
        aim = aim.setBit(index);
        if(map.testBit(index)) {
            shots = aim.longValue();
            return true;
        }
        shots = aim.longValue();
        return false;
    }

    public String state() {
        StringBuilder s = new StringBuilder();
        BigInteger map = BigInteger.valueOf(ships);
        BigInteger aim = BigInteger.valueOf(shots);
        boolean m, a;
        // '.' '×' '☐' '☒'
        for (int i = 0; i < 64; i++) {
            m = map.testBit(63-i);
            a = aim.testBit(63-i);
            if (m && a) {
                s.append("☒");
            } else if (m) {
                s.append("☐");
            } else if (a) {
                s.append("×");
            } else {
                s.append(".");
            }
            if ((i % 9) == 0) {
                s.insert(i, "\n");
            }
        }

        return s.toString();
    }
}
