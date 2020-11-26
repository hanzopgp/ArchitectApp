package datamining;

import dataminingtests.*;

public class MainDatamining {
	
	public static void main (String[] args){
		boolean ok = true;
		ok = ok && AbstractItemsetMinerTests.testFrequency();
		ok = ok && AprioriTests.testFrequentSingletons();
		ok = ok && AprioriTests.testCombine();
		ok = ok && AprioriTests.testAllSubsetsFrequent();
		ok = ok && AprioriTests.testExtract();
		ok = ok && AbstractAssociationRuleMinerTests.testFrequency();
		ok = ok && AbstractAssociationRuleMinerTests.testConfidence();
		ok = ok && BruteForceAssociationRuleMinerTests.testAllCandidatePremises();
		ok = ok && BruteForceAssociationRuleMinerTests.testExtract();
		//ok = ok && DatabaseTests.testItemTable();
		//ok = ok && DatabaseTests.testPropositionalize();
		System.out.println(ok ? "All tests passed" : "At least one test failed");
	}
	
}

