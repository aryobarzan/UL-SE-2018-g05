package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.*;

public class DtQuestionText extends DtString implements JIntIs{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	public DtQuestionText(PtString str) {
		super(str);
	}
	
	public PtBoolean is() {
		if(this.value.getValue().length() <= 160)
			return new PtBoolean(true);
		return new PtBoolean(false);
	}
}
