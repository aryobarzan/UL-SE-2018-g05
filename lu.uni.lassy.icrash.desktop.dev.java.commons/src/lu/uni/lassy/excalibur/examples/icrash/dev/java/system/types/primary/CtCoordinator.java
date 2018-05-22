/*******************************************************************************
 * Copyright (c) 2014-2015 University of Luxembourg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Alfredo Capozucca - initial API and implementation
 *     Christophe Kamphaus - Remote implementation of Actors
 *     Thomas Mortimer - Updated client to MVC and added new design patterns
 ******************************************************************************/
package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary;

import java.util.Random;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class CtCoordinator which extends the class of CtAuthenticated
 * This is the class that stores details about the coordinator.
 */
public class CtCoordinator extends CtAuthenticated {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	/** The id of the coordinator. */
	public DtCoordinatorID id;
		
	/**
	 * Initialises the coordinator.
	 *
	 * @param aId The ID of the coordinator
	 * @param aLogin The username of the coordinator
	 * @param aPwd The password of the coordinator
	 * @return The success of the initialisation
	 */
	public PtBoolean init(DtCoordinatorID aId,DtLogin aLogin,DtPassword aPwd, DtBiometricData aBioData){
			super.init(aLogin, aPwd, aBioData);
			id = aId;
			Random random = new Random(System.currentTimeMillis());
			// Generate random symmetric key for the new coordinator. Note that this is not conform to the Messir specification, as a key exchange has to happen to establish the key. However, for the implementation we assume the exchange to have been done.
			String randomGeneratedSymmetricKey = "";
			for(int i = 0; i < 4; i++) {
				char character = (char) (random.nextInt(26) + 65);
				randomGeneratedSymmetricKey += character;
			}
			this.symmetricKey = new DtSymmetricKey(new PtString(randomGeneratedSymmetricKey));
			return new PtBoolean(true); 
	}
	
	/**
	 * Updates the user details, used when the administrator does an update on the coordinator.
	 *
	 * @param aLogin The value to change the login to
	 * @param aPwd the value to change the password to 
	 * @return the success of the update method
	 */
	public PtBoolean update(DtLogin aLogin,DtPassword aPwd){
		login = aLogin;
		pwd = aPwd;
		return new PtBoolean(true);
	}
	
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAuthenticated#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof CtCoordinator))
			return false;
		CtCoordinator aCtCoordinator = (CtCoordinator)obj;
		if (!aCtCoordinator.id.value.getValue().equals(this.id.value.getValue()))
			return false;
		return true;
	}
	
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.CtAuthenticated#hashCode()
	 */
	@Override
	public int hashCode(){
		return this.id.value.getValue().length() + super.hashCode();
	}
}
