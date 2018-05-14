package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;


import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;

public class DtQuestionID extends DtInteger implements JIntIs {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	public DtQuestionID(PtInteger ptInteger) {
		super(ptInteger);
	}

	public PtBoolean is() {
		return new PtBoolean(true);	
	}
}
