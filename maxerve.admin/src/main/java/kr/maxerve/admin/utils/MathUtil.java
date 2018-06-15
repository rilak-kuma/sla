package kr.maxerve.admin.utils;

import java.util.Random;

public class MathUtil {
	public static int fnRandom(int min, int max) throws Exception
	{
		Random random = new Random(System.currentTimeMillis());
        return random.nextInt(max - min) + min;
	}
}