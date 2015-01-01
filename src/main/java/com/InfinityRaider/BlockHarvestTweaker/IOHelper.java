package com.InfinityRaider.BlockHarvestTweaker;

import java.util.ArrayList;

public class IOHelper {
    //turns the raw data string into an array (each array element is a line from the string)
    public static String[] getLinesArrayFromData(String input) {
        int count = 0;
        String unprocessed = input;
        for (int i=0;i<unprocessed.length();i++) {
            if (unprocessed.charAt(i) == '\n') {
                count++;
            }
        }
        ArrayList<String> data = new ArrayList<String>();
        if (unprocessed.length()>0) {
            for (int i=0;i<count;i++) {
                String line = (unprocessed.substring(0,unprocessed.indexOf('\n'))).trim();
                if (line.length() > 0 && line.charAt(0) != '#') {
                    data.add(line);
                }
                unprocessed = unprocessed.substring(unprocessed.indexOf('\n')+1);
            }
        }
        if ((unprocessed.trim()).length()>0 && unprocessed.charAt(0)!='#') {
            data.add(unprocessed.trim());
        }
        return data.toArray(new String[data.size()]);
    }

    public static String[] getData(String input) {
        String dummy = input;
        String[] output = new String[4];
        String block = dummy .substring(0, dummy.indexOf(','));
        String meta = "-1";
        int colonIndex = dummy.indexOf(':',dummy.indexOf(':')+1);
        if(colonIndex>0) {
            meta = block.substring(colonIndex+1);
            block = block.substring(0,colonIndex);
        }
        output[0] = block;
        output[1] = meta;
        dummy = dummy.substring(dummy.indexOf(',')+1);
        output[2] = dummy.substring(0,dummy.indexOf(','));
        output[3] = dummy.substring(dummy.indexOf(',')+1);
        return output;
    }

    public static String getInstructions() {
        return instructions;
    }

    private static final String instructions =
            "#Put a list of blocks to define a harvestlevel for, in the following fashion: <blockName>:<blockMeta>,<tool>,<harvestlevel>\n" +
            "#The blockName should be the name NEI gives you, the stacksize is the maximum stacksize\n" +
            "#tool is axe, pickaxe or shovel" +
            "#Harvestlevel means: 0 = wood, 1 = stone, 2 = iron, 3 = diamond (you can go higher, for instance if you have tconstruct installed)\n" +
            "#Note that a harvested block dropping its item depends on the material of it, for example sand will always drop its item, but stone will not\n" +
            "#Only define one entry per line, meta is optional (if you don't specify meta, it will change it for all the damga values). Example: minecraft:stone,pickaxe,2";
}
