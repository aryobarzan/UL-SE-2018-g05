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
package lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtAlertID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtAnswerText;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtBiometricData;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtLogin;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPassword;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtQuestionID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtQuestionText;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

/**
 * The Interface ActAdministrator that allows RMI access to administrator functions.
 */
public interface ActAdministrator extends ActAuthenticated {

	/**
	 * Add a coordinator to the system, using the parameters passed.
	 *
	 * @param aDtCoordinatorID The ID to use when creating the coordinator
	 * @param aDtLogin The username to use when creating the coordinator
	 * @param aDtPassword The password to use when creating the coordinator
	 * @return The success of the method
	 * @throws RemoteException Thrown if the server is offline
	 * @throws NotBoundException Thrown if the server has not been bound correctly in RMI settings
	 */
	public PtBoolean oeAddCoordinator(DtCoordinatorID aDtCoordinatorID,
			DtLogin aDtLogin, DtPassword aDtPassword, DtBiometricData aDtBiometricData) throws RemoteException,
			NotBoundException;

	/**
	 * Delete a coordinator to the system, using the parameters passed.
	 *
	 * @param aDtCoordinatorID The ID to use when looking for the coordinator to delete
	 * @return The success of the method
	 * @throws RemoteException Thrown if the server is offline
	 * @throws NotBoundException Thrown if the server has not been bound correctly in RMI settings
	 */
	public PtBoolean oeDeleteCoordinator(DtCoordinatorID aDtCoordinatorID)
			throws RemoteException, NotBoundException;
	
	/**
	 * 	 
	 * Add a question to the system, using the parameters passed.
	 *
	 * @param aDtQuestionText
	 * @param aDtAnswerText1
	 * @param aDtAnswerText2
	 * @param aDtAnswerText3
	 * @param aDtAnswerText4
	 * @return The success of the operation
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
		public PtBoolean oeAddQuestion(DtQuestionText aDtQuestionText, 
														DtAnswerText aDtAnswerText1, 
														DtAnswerText aDtAnswerText2,
														DtAnswerText aDtAnswerText3, 
														DtAnswerText aDtAnswerText4) throws RemoteException, NotBoundException;	

		
	/**
	 * Delete a question to the system, using the parameters passed.
	 *
	 * @param aDtQuestionID The ID to use when looking for the question to delete
	 * @return The success of the method
	 * @throws RemoteException Thrown if the server is offline
	 * @throws NotBoundException Thrown if the server has not been bound correctly in RMI settings
	 */
	public PtBoolean oeDeleteQuestion(DtQuestionID aDtQuestionID)
			throws RemoteException, NotBoundException;
	
	
	/**
	 * Ask the system to get the statistic of a specific question of a specific Alert
	 * 
	 * @param aDtAlertID
	 * @param aDtQuestionID
	 * @return The sucess of the method
	 * @throws RemoteException
	 */
	public PtBoolean oeGetAnswerU(DtAlertID aDtAlertID, DtQuestionID aDtQuestionID) throws RemoteException;
	
	/**
	 * Ask the system to get the statistic for all the question of a specific Alert
	 * 
	 * @param aDtAlertID
	 * @return
	 * @throws RemoteException
	 */
	public PtBoolean oeGetAnswerAlert(DtAlertID aDtAlertID) throws RemoteException;

	/**
	 * Ask the system to get the statistic for a specific question for all the alerts
	 * 
	 * @param aDtQuestionID
	 * @return
	 * @throws RemoteException
	 */
	public PtBoolean oeGetAnswerQuestion(DtQuestionID aDtQuestionID) throws RemoteException;

	/**
	 * A message sent to the listening actor saying the coordinator was created .
	 *
	 * @return The success of the method
	 * @throws RemoteException Thrown if the server is offline
	 */
	public PtBoolean ieCoordinatorAdded() throws RemoteException;

	/**
	 * A message sent to the listening actor saying the coordinator was deleted.
	 *
	 * @return The success of the method
	 * @throws RemoteException Thrown if the server is offline
	 */
	public PtBoolean ieCoordinatorDeleted() throws RemoteException;
	
	/**
	 * A message sent to the listening actor saying the coordinator was updated.
	 *
	 * @return The success of the method
	 * @throws RemoteException Thrown if the server is offline
	 */
	public PtBoolean ieCoordinatorUpdated() throws RemoteException;


}
