package org.loccs.outsource.pairing.test;

import org.loccs.outsource.pairing.MultipleSymmetricPrimeOrderPairingOutsource;
import org.loccs.outsource.pairing.VMBPPairingOutsource;

public class Ren201504MultiplePairingEvaluation {

	private static int rbits = 160;
	private static int qbits = 512;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MultipleSymmetricPrimeOrderPairingOutsource outsource;
		
		System.out.println("Algorithm: " + args[0]);
		int pairingCount = Integer.parseInt(args[1]);
		System.out.println("Pairing Count: " + pairingCount);
		int round = Integer.parseInt(args[2]);
		System.out.println("Round: " + round);
		
		if (args[0].equals("VMBP") || args[0].equals("direct"))
			outsource = new VMBPPairingOutsource(rbits, qbits, pairingCount);
		else 
			return;
		
		outsource.setRepeat(round);
		
		if (args[0].equals("direct"))
			outsource.direct();
		else 
			outsource.evaluate();		
	}

}
