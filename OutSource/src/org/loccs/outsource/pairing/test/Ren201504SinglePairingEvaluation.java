package org.loccs.outsource.pairing.test;

import org.loccs.outsource.pairing.BJNPairingOutsource;
import org.loccs.outsource.pairing.PairPairingOutsource;
import org.loccs.outsource.pairing.PairingArithmeticEvaluationOutsource;
import org.loccs.outsource.pairing.Ren201504PairingOutsource;
import org.loccs.outsource.pairing.SymmetricPrimeOrderPairingOutsource;
import org.loccs.outsource.pairing.TZR1PairingOutsource;
import org.loccs.outsource.pairing.TZR2PairingOutsource;

public class Ren201504SinglePairingEvaluation {
	
	private static int rbits = 160;
	private static int qbits = 512;

	public static void main(String[] args) {
		
		SymmetricPrimeOrderPairingOutsource outsource;
		
		System.out.println("Algorithm: " + args[0]);
		int round = Integer.parseInt(args[1]);
		System.out.println("Round: " + round);
		
		if (args[0].equals("VBP") || args[0].equals("direct"))
			outsource = new Ren201504PairingOutsource(rbits, qbits);
		else if (args[0].equals("BJN"))
			outsource = new BJNPairingOutsource(rbits, qbits);
		else if (args[0].equals("Pair"))
			outsource = new PairPairingOutsource(rbits, qbits);
		else if (args[0].equals("TZR1"))
			outsource = new TZR1PairingOutsource(rbits, qbits);
		else if (args[0].equals("TZR2"))
			outsource = new TZR2PairingOutsource(rbits, qbits);
		else if (args[0].equals("Simple"))
			outsource = new PairingArithmeticEvaluationOutsource(rbits, qbits);
		else 
			return;
		
		outsource.setRepeat(round);
		
		if (args[0].equals("direct"))
			outsource.direct();
		else 
			outsource.evaluate();
		
	}

}
