package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.io.Serializable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

public class DtEncryptedMessage implements Serializable, JIntIs{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4813847718687679770L;
	/**
	 * 
	 */
	
	public DtString encryptedLogin;
	public DtString encryptedNonce;

	public DtEncryptedMessage(DtString encryptedLogin, DtString encryptedNonce) {
		this.encryptedLogin = encryptedLogin;
		this.encryptedNonce = encryptedNonce;
	}

	@Override
	public PtBoolean is() {
		return null;
	}

}
