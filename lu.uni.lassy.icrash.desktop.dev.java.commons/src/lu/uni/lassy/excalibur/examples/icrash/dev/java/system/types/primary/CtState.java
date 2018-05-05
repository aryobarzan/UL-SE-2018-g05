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

import java.io.Serializable;
import java.util.Random;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtDateAndTime;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtSecond;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.DtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtInteger;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;

/**
 * The Class CtState which has the systems's status.
 */
public class CtState implements Serializable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	/** Random generator used for the symmetric login system nonce creation. */
	private Random random;

	/** The next available value for alert id, this is retrieved from the database at moment of system and environment creation. */
	public DtInteger nextValueForAlertID;
	
	/** The next available value for crisis id, this is retrieved from the database at moment of system and environment creation. */
	public DtInteger nextValueForCrisisID;
	
	/** The current system date and time. */
	public DtDateAndTime clock;
	
	/** The set crisis reminder period, if a crisis hasn't been handled within the period, coordinators will be warned. */
	public DtSecond crisisReminderPeriod;
	
	/** The max crisis reminder period, if a crisis hasn't been handled within the period, coordinators will be warned. */
	public DtSecond maxCrisisReminderPeriod;
	
	/** The last time the coordinators have been reminded about outstanding crisises. */
	public DtDateAndTime vpLastReminder;
	
	/**  If the system has been started or not. */
	public PtBoolean vpStarted;
	
	/**  The current login name of the authenticating actor attempting a symmetric login. */
	public DtLogin currentLoginForSymmetricLogin;
	
	/**  The current symmetric key being used by an authenticating actor to log in. */
	public DtSymmetricKey currentSymmetricKeyForAuthenticatingActor;
	
	/**  The current system nonce sent to an authenticating actor and to be re-sent to the icrash system in an encrypted message by the authenticating actor. */
	public DtNonce currentNonceForAuthenticatingActor;
			
	/**
	 * Initialises the system's state.
	 *
	 * @param aNextValueForAlertID The next index value available for the alert ID
	 * @param aNextValueForCrisisID The next index value available for the crisis ID
	 * @param aClock The current system date and time
	 * @param aCrisisReminderPeriod The set crisis reminder period, if a crisis hasn't been handled within the period, coordinators will be warned.
	 * @param aMaxCrisisReminderPeriod The max crisis reminder period, if a crisis hasn't been handled within the period, coordinators will be warned.
	 * @param aVpLastReminder The last time the coordinators have been reminded about outstanding crisises.
	 * @param aVpStarted If the system has been started or not
	 * @return Success of the initialisation
	 */
	public PtBoolean init(DtInteger aNextValueForAlertID, DtInteger aNextValueForCrisisID, 
						DtDateAndTime aClock, DtSecond aCrisisReminderPeriod, 
						DtSecond aMaxCrisisReminderPeriod, DtDateAndTime aVpLastReminder, 
						PtBoolean aVpStarted, DtLogin aCurrentLoginForSymmetricLogin, DtSymmetricKey aCurrentSymmetricKeyForAuthenticatingActor, DtNonce aCurrentNonceForAuthenticatingActor){
				random = new Random(System.currentTimeMillis());
	
				nextValueForAlertID = aNextValueForAlertID;
				nextValueForCrisisID = aNextValueForCrisisID;
				clock = aClock;
				crisisReminderPeriod = aCrisisReminderPeriod;
				maxCrisisReminderPeriod = aMaxCrisisReminderPeriod;
				vpLastReminder = aVpLastReminder;
				vpStarted = aVpStarted;
				currentLoginForSymmetricLogin = aCurrentLoginForSymmetricLogin;
				currentSymmetricKeyForAuthenticatingActor = aCurrentSymmetricKeyForAuthenticatingActor;
				currentNonceForAuthenticatingActor = aCurrentNonceForAuthenticatingActor;
				
				return new PtBoolean(true);
	}
	
	public PtInteger generateRandomIntegerBetween0And50000() {
		return new PtInteger(Math.abs(random.nextInt(50000)));
	}
	
	public DtEncryptedMessage encryptLoginAndNonce(DtLogin ALogin, DtNonce ANonce, DtSymmetricKey ASymmetricKey) {
		String loginToEncrypt = ALogin.value.getValue().toUpperCase();
		int nonceToEncrypt = ANonce.value.getValue();
		String symmetricKeyString = ASymmetricKey.value.getValue();
		String encryptedLogin = "";
		for(int i = 0, j = 0; i < loginToEncrypt.length(); i++) {
			char character = loginToEncrypt.charAt(i);
			if(!(character < 'A' || character > 'Z')) {
				char newCharacter = (char) (((character + symmetricKeyString.charAt(j)) % 26) + 'A');
				j++;
				j = j % symmetricKeyString.length();
				encryptedLogin += newCharacter;
			}
			else {
				encryptedLogin += character;
			}
		}
		String encryptedNonce = "";
		int keyForEncryptingNonce = 0;
		for(int i = 0; i < symmetricKeyString.length(); i++) {
			keyForEncryptingNonce += symmetricKeyString.charAt(i);
			}
		encryptedNonce = String.valueOf(nonceToEncrypt+keyForEncryptingNonce);
		DtEncryptedMessage aDtEncryptedMessage = new DtEncryptedMessage(new DtString(new PtString(encryptedLogin)), new DtString(new PtString(encryptedNonce)));
		return aDtEncryptedMessage;
	}
		
	
	public DtLogin decryptLogin(DtEncryptedMessage AEncryptedMessage, DtSymmetricKey ASymmetricKey) {
		String loginToDecrypt = AEncryptedMessage.encryptedLogin.value.getValue().toUpperCase();
		String keyToUseForDecryption = ASymmetricKey.value.getValue();
		String decryptedLogin = "";
		for(int i = 0, j = 0; i < loginToDecrypt.length(); i++) {
			char character = loginToDecrypt.charAt(i);
			if(!(character < 'A' || character > 'Z')) {
				char newCharacter = (char) (((character - keyToUseForDecryption.charAt(j)) % 26) + 'A');
				j++;
				j = j % keyToUseForDecryption.length();
				decryptedLogin += newCharacter;
			}
			else {
				decryptedLogin += character;
			}
		}
		DtLogin aDtDecryptedLogin = new DtLogin(new PtString(decryptedLogin));
		return aDtDecryptedLogin;
	}
	
	public DtNonce decryptNonce(DtEncryptedMessage AEncryptedMessage, DtSymmetricKey ASymmetricKey) {
		String keyToUseForDecryption = ASymmetricKey.value.getValue();
		String decryptedNonce = "";
		int keyForEncryptingNonce = 0;
		for(int i = 0; i < keyToUseForDecryption.length(); i++) {
			keyForEncryptingNonce += keyToUseForDecryption.charAt(i);
			}
		decryptedNonce = String.valueOf(Integer.parseInt(AEncryptedMessage.encryptedNonce.value.getValue())-keyForEncryptingNonce);
		DtNonce aDtDecryptedNonce = new DtNonce(new PtInteger(Integer.parseInt(decryptedNonce)));
		return aDtDecryptedNonce;
	}
}
