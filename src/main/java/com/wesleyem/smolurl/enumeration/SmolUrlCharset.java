package com.wesleyem.smolurl.enumeration;

public enum SmolUrlCharset {
    DEFAULT_ALPHABET("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_");

    private final char[] charArray;

    SmolUrlCharset(String chars) {
        this.charArray = chars.toCharArray();
    }

    public char[] getCharArray() {
        return charArray.clone();
    }
}