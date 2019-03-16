/*
 * Copyright 2006-2010 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package orange;

/**
 * Created 3/16/2019
 *
 * @author sjkumar
 */
class Modulo{
    private static final int MOD = 1000_000_007;
    private int current = 0;

    private Modulo(int current) {
        current = current % MOD;
        this.current = current;
    }

    public static Modulo createModulo(int current){
        return new Modulo(current);
    }
    public Modulo add(int number){
        current = (current + number) % MOD;
        return this;
    }

    public int get(){
        return current;
    }
}