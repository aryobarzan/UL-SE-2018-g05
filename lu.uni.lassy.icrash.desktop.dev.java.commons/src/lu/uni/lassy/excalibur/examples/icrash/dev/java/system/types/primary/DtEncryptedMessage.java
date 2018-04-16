package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class DtEncryptedMessage extends DtString  implements JIntIs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8363919872387601536L;

	public DtEncryptedMessage(PtString s) {
		super(s);
	}

	@Override
	public PtBoolean is() {
		return null;
	}

}
