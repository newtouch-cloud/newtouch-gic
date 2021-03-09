package com.newtouch.utils.uniqueseq;

public class UniqueSeq {
	public static Long getUniqueSeq(String seqName) {
		return UniqueSeqInfo.getInstance().getUniqueSeq(seqName.toUpperCase());
	}

	public static Long getUniqueSeq(String seqName, String tableName) {
		return UniqueSeqInfo.getInstance().getUniqueSeq(seqName.toUpperCase(),
				tableName.toUpperCase());
	}
}
