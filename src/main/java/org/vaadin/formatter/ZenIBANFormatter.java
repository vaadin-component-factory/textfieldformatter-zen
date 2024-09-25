package org.vaadin.formatter;

import org.vaadin.formatter.conf.FormatGeneralOptions;

import java.util.Arrays;

public class ZenIBANFormatter extends ZenCustomStringBlockFormatter {

    public ZenIBANFormatter(int[] blocks) {
        super(blocks, null, ZenCustomStringBlockFormatter.ForceCase.UPPER, null, false);

        // delimiter must be specified otherwise blocking won't work
        this.getConfiguration().delimiters = new String[] {" "};
    }

    public static ZenIBANFormatter fromIBANLength(int length) {
        int num4s = length / 4;
        int lastBlock = length % 4;
        int[] blocks = null;
        if (lastBlock != 0) {
            blocks = new int[num4s + 1];
            Arrays.fill(blocks, 4);
            blocks[blocks.length - 1] = lastBlock;
        } else {
            blocks = new int[num4s];
            Arrays.fill(blocks, 4);
        }
        return new ZenIBANFormatter(blocks) ;
    }
}