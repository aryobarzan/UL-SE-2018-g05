package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class DtSymmetricKey extends DtString  implements JIntIs{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5974825685936720585L;

	public DtSymmetricKey(PtString s) {
		super(s);
	}

	@Override
	public PtBoolean is() {
		return null;
	}

}
