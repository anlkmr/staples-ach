package com.emagia.ach.utils;

public enum BU {
    STAPLESCONTRACT {
        public String toString() {
            return "SBA";
        }
    },

    HitouchBusiness {
        public String toString() {
            return "HiTouch";
        }
    }
}
