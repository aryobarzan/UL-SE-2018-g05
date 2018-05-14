package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;

public class DtAnswerID extends DtInteger implements JIntIs {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	public DtAnswerID(PtInteger ptInteger) {
		super(ptInteger);
	}
	@Override
	public PtBoolean is() {
		if(this.value.getValue() > 0 && this.value.getValue() < 5)
			return new PtBoolean(true);
		return new PtBoolean(false);
	}

}
