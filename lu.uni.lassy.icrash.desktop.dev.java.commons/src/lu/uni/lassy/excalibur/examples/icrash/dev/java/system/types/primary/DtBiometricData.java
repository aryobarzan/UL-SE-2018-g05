package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class DtBiometricData, which holds a datatype of the biometricData.
 */
public class DtBiometricData extends DtString implements JIntIs{

		/**
		 * Instantiates a new datatype BiometricData.
		 *
		 * @param s The primitive type of the string to create the datatype 
		 */
		public DtBiometricData(PtString s) {
			super(s);

		}
		
		/* (non-Javadoc)
		 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.DtIs#is()
		 */
		public PtBoolean is(){
			return new PtBoolean(true);
		}
}
