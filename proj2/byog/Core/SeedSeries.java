package byog.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：FlyingRedPig
 * @description：TODO
 * @date ：10/6/2020 10:27 PM
 */
public class SeedSeries {

	/*very often, we need to generate several rooms etc. which means we need more than one seed,
	* seed1 -> [seed1, seed2, seed3, ... ]
	* from this generation algorithm, I got a series seeds from just one seed
	* seed: original seed
	* number: the number of the series of seeds you want
	* */
	public static List<Long> series(long seed ,int number){

		List<Long> seedContainer = new ArrayList<>();
		long seedRecorder = seed;
		seedContainer.add(seedRecorder);

		for(int i = 0; i < number - 1; i++){
			Random RANDOM = new Random(seedRecorder);
			// key point: it's similar to linked-list, seed1 -> seed2 -> seed3
			seedRecorder = RandomUtils.uniform(RANDOM, 1000000);
			seedContainer.add(seedRecorder);
		}

		return seedContainer;
	}


	public static void main(String[] args) {

		List<Long> t = SeedSeries.series(100,1);
		System.out.println(t);
	}
}
