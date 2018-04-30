package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

public class DtAnswerText extends DtString implements JIntIs{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	public DtAnswerText(PtString str) {
		super(str);
	}
	
	public PtBoolean is() {
		if(this.value.getValue().length() <= 80)
			return new PtBoolean(true);
		return new PtBoolean(false);
	}
}
