/*
 * Decompiled with CFR 0.150.
 */
package me.archware.notifications;

public enum NotificationType {
    OK{

        @Override
        public char getChar() {
            return 'b';
        }
    }
    ,
    WARNING{

        @Override
        public char getChar() {
            return 'a';
        }
    }
    ,
    ERROR{

        @Override
        public char getChar() {
            return 'c';
        }
    };


    public abstract char getChar();
}

