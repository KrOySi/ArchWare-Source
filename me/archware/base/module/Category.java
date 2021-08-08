/*
 * Decompiled with CFR 0.150.
 */
package me.archware.base.module;

import java.awt.Color;

public enum Category {
    COMBAT{

        @Override
        public int color() {
            return new Color(150, 236, 52).hashCode();
        }

        @Override
        public String getChar() {
            return "a";
        }
    }
    ,
    MOVEMENT{

        @Override
        public int color() {
            return new Color(35, 70, 121).hashCode();
        }

        @Override
        public String getChar() {
            return "b";
        }
    }
    ,
    PLAYER{

        @Override
        public int color() {
            return new Color(26, 115, 220).hashCode();
        }

        @Override
        public String getChar() {
            return "c";
        }
    }
    ,
    RENDER{

        @Override
        public int color() {
            return new Color(142, 50, 83).hashCode();
        }

        @Override
        public String getChar() {
            return "d";
        }
    }
    ,
    MISC{

        @Override
        public int color() {
            return new Color(164, 248, 150).hashCode();
        }

        @Override
        public String getChar() {
            return "e";
        }
    };


    public abstract int color();

    public abstract String getChar();
}

