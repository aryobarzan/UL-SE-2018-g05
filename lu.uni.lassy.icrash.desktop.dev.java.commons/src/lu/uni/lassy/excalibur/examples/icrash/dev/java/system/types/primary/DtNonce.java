package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;

public class DtNonce extends DtInteger implements JIntIs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 876878159074782799L;

	public DtNonce(PtInteger v) {
		super(v);
	}

	@Override
	public PtBoolean is() {
		return null;
	}

}
