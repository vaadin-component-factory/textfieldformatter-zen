package org.vaadin.addons.componentfactory.cleavezenformatter;

import java.util.Arrays;

public class IBANFormatter extends CustomStringBlockFormatter {

    public IBANFormatter(int[] blocks) {
        super(blocks, null, CustomStringBlockFormatter.ForceCase.UPPER, null, false);

        // delimiter must be specified otherwise blocking won't work
        this.getConfiguration().delimiters = new String[] {" "};
    }

    public static IBANFormatter fromIBANLength(int length) {
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
        return new IBANFormatter(blocks) ;
    }
}