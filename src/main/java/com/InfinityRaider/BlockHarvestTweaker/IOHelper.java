package com.InfinityRaider.BlockHarvestTweaker;

public class IOHelper {
    //turns the raw data string into an array (each array element is a line from the string)
    public static String[] getLinesArrayFromData(String input) {
        int count = 0;
        for(int i=0;i<input.length();i++) {
            if(input.charAt(i)=='\n') {
                count++;
            }
            else if (input.charAt(i)=='#') {
                count--;
            }
        }
        count = count>=0?count+1:count;  //for the last line
        count = count<0?0:count;        //make sure it can't be negative
        String[] data = new String[count];
        if(input.length()>0) {
            int start = 0;
            int stop;
            for (int i = 0; i < data.length; i++) {
                stop = input.indexOf('\n', start);
                while (start<input.length() && input.charAt(start) == '#' && start<input.length()) {
                    start = stop + 1;
                    stop = input.indexOf('\n', start);
                }
                data[i] = i == data.length - 1 ? input.substring(start) : input.substring(start, stop);
                start = stop + 1;
            }
        }
        return data;
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
            "#Only define one entry per line, meta is optional. Example: minecraft:stone,pickaxe,2";
}
