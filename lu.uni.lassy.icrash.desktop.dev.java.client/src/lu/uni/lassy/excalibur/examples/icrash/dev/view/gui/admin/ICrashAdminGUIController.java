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
package lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.admin;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
/*
 * This is the import section to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
 */
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.AdminController;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.SystemStateController;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectActorException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.IncorrectFormatException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerNotBoundException;
import lu.uni.lassy.excalibur.examples.icrash.dev.controller.exceptions.ServerOfflineException;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.environment.actors.ActAdministrator;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIsActor;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtCoordinatorID;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtString;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.utils.Log4JUtils;
import lu.uni.lassy.excalibur.examples.icrash.dev.model.Message;
import lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController;
import lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.coordinator.CreateICrashCoordGUI;
/*
 * This is the end of the import section to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
 */
/**
 * The Class ICrashGUIController, which deals with handling the GUI and it's functions for the Administrator.
 */
public class ICrashAdminGUIController extends AbstractAuthGUIController {
	
	/*
	* This section of controls and methods is to be replaced by modifications in the ICrash.fxml document from the sample skeleton controller
	* When replacing, remember to reassign the correct methods to the button event methods and set the correct types for the tableviews
	*/
	
    /** The pane containing the logon controls. */
	@FXML
    private Pane pnAdminLogon;

    /** The textfield that allows input of a username for logon. */
    @FXML
    private TextField txtfldAdminUserName;

    /** The passwordfield that allows input of a password for logon. */
    @FXML
    private PasswordField psswrdfldAdminPassword;

    /** The button that initiates the login function. */
    @FXML
    private Button bttnAdminLogin;

    /** The borderpane that contains the normal controls the user will use. */
    @FXML
    private BorderPane brdpnAdmin;

    /** The anchorpane that will have the add or delete coordinator controls added/removed from it */
    @FXML
    private AnchorPane anchrpnCoordinatorDetails;

    /** The anchorpane that will have the add or delete question controls added/removed from it */
    @FXML
    private AnchorPane anchrpnQuestionDetails;

    /** The button that shows the controls for adding a coordinator */
    @FXML
    private Button bttnBottomAdminCoordinatorAddACoordinator;

    /** The button that shows the controls for deleting a coordinator */
    @FXML
    private Button bttnBottomAdminCoordinatorDeleteACoordinator;
    
    /** The button that shows the controls for adding a question */
    @FXML
    private Button bttnBottomAdminCoordinatorAddAQuestion;

    /** The button that shows the controls for deleting a question */
    @FXML
    private Button bttnBottomAdminCoordinatorDeleteAQuestion;

    /** The tableview of the recieved messages from the system */
    @FXML
    private TableView<Message> tblvwAdminMessages;

    /** The button that allows a user to logoff */
    @FXML
    private Button bttnAdminLogoff;

	/** The textfield that allows input of a name for symmetric login. */
	@FXML
	private TextField txtfldAdminSymmetricField1;

	/** The textfield that allows input of a nonce for symmetric login. */
	@FXML
	private TextField txtfldAdminSymmetricField2;
	
	/** The textfield that allows input of the encrypted user login. */
	@FXML
	private TextField txtfldAdminSymmetricField3;
	
	/** The textfield that allows input of the encrypted system nonce. */
	@FXML
	private TextField txtfldAdminSymmetricField4;

	/** The button that switches to the symmetric login. */
	@FXML
	private Button bttnAdminSwitchToSymmetricLogin;

	/** The button that switches to the biometric login. */
	@FXML
	private Button bttnAdminSwitchToBiometricLogin;

	/** The button that switches to the standard login. */
	@FXML
	private Button bttnAdminSwitchToStandardLogin;

	/** The button that is utilized for sending login and nonce used by the symmetric login to the icrash System. */
	@FXML
	private Button bttnAdminSymmetricLogin;
	
	/** The button that is utilized for sending encrypted system nonce and login used by the symmetric login to the icrash System. */
	@FXML
	private Button bttnAdminSymmetricLogin2;
	
	/** The button that scans the biometric data. */
	@FXML
	private Button bttnAdminScan;
	
	/** The progress indicator that shows the progress of the biometric scan. */
	@FXML
	private ProgressIndicator progressIndicatorAdminBiometricScan;
	
	/** The TabPane holding the tab after the admin has log on */
	@FXML
	private TabPane adminTabPane;
	
	/** The Pane containing the coordinator administration interface */
	@FXML
	private Tab coordinatorTab;
	
	/** The pane containing the survey administration interface */
	@FXML
	private Tab surveyTab;
	
    /**
     * The button event that will show the controls for adding a coordinator
     *
     * @param event The event type thrown, we do not need this, but it must be specified
     */
    @FXML
    void bttnBottomAdminCoordinatorAddACoordinator_OnClick(ActionEvent event) {
    	showCoordinatorScreen(TypeOfEdit.Add);
    }

    /**
     * The button event that will show the controls for deleting a coordinator
     *
     * @param event The event type thrown, we do not need this, but it must be specified
     */
    @FXML
    void bttnBottomAdminCoordinatorDeleteACoordinator_OnClick(ActionEvent event) {
    	showCoordinatorScreen(TypeOfEdit.Delete);
    }
	
    /**
     * The button event that will show the controls for adding a question
     *
     * @param event The event type thrown, we do not need this, but it must be specified
     */
    @FXML
    void bttnBottomAdminCoordinatorAddAQuestion_OnClick(ActionEvent event) {
    	showQuestionScreen(TypeOfEdit.Add);
    }

    /**
     * The button event that will show the controls for deleting a question
     *
     * @param event The event type thrown, we do not need this, but it must be specified
     */
    @FXML
    void bttnBottomAdminCoordinatorDeleteAQuestion_OnClick(ActionEvent event) {
    	showQuestionScreen(TypeOfEdit.Delete);
    }

    /**
     * The button event that will initiate the logging on of a user
     *
     * @param event The event type thrown, we do not need this, but it must be specified
     */
    @FXML
    void bttnBottomLoginPaneLogin_OnClick(ActionEvent event) {
    	logon();
    }

    /**
     * The button event that will initiate the logging off of a user
     *
     * @param event The event type thrown, we do not need this, but it must be specified
     */
    @FXML
    void bttnTopLogoff_OnClick(ActionEvent event) {
    	logoff();
    }

	/**
	 * The button event that reveals the symmetric login view elements
	 *
	 * @param event The event type thrown, we do not need this, but it must be specified
	 */
	@FXML
	void bttnBottomLoginPaneSwitchToSymmetricLogin_OnClick(ActionEvent event) {
		txtfldAdminSymmetricField1.setVisible(true);
		txtfldAdminSymmetricField2.setVisible(true);
		bttnAdminSymmetricLogin.setVisible(true);
		
		txtfldAdminSymmetricField3.setVisible(false);
		txtfldAdminSymmetricField4.setVisible(false);
	    bttnAdminSymmetricLogin2.setVisible(false);

		txtfldAdminUserName.setVisible(false);
		psswrdfldAdminPassword.setVisible(false);
		bttnAdminLogin.setVisible(false);
		bttnAdminScan.setVisible(false);
		progressIndicatorAdminBiometricScan.setVisible(false);

		bttnAdminSwitchToSymmetricLogin.setDisable(true);
		bttnAdminSwitchToBiometricLogin.setDisable(false);
		bttnAdminSwitchToStandardLogin.setDisable(false);

	}

	/**
	 * The button event that reveals the biometric login view elements
	 *
	 * @param event The event type thrown, we do not need this, but it must be specified
	 */
	@FXML
	void bttnBottomLoginPaneSwitchToBiometricLogin_OnClick(ActionEvent event) {
		bttnAdminScan.setVisible(true);
		progressIndicatorAdminBiometricScan.setVisible(true);

		txtfldAdminUserName.setVisible(false);
		psswrdfldAdminPassword.setVisible(false);
		bttnAdminLogin.setVisible(false);
		txtfldAdminSymmetricField1.setVisible(false);
		txtfldAdminSymmetricField2.setVisible(false);
		bttnAdminSymmetricLogin.setVisible(false);
		txtfldAdminSymmetricField3.setVisible(false);
		txtfldAdminSymmetricField4.setVisible(false);
	    bttnAdminSymmetricLogin2.setVisible(false);

		bttnAdminSwitchToSymmetricLogin.setDisable(false);
		bttnAdminSwitchToBiometricLogin.setDisable(true);
		bttnAdminSwitchToStandardLogin.setDisable(false);

	}

	/**
	 * The button event that reveals the standard login view elements
	 *
	 * @param event The event type thrown, we do not need this, but it must be specified
	 */
	@FXML
	void bttnBottomLoginPaneSwitchToStandardLogin_OnClick(ActionEvent event) {
		txtfldAdminUserName.setVisible(true);
		psswrdfldAdminPassword.setVisible(true);
		bttnAdminLogin.setVisible(true);
		
		txtfldAdminSymmetricField1.setVisible(false);
		txtfldAdminSymmetricField2.setVisible(false);
		bttnAdminSymmetricLogin.setVisible(false);
		bttnAdminScan.setVisible(false);
		progressIndicatorAdminBiometricScan.setVisible(false);
		txtfldAdminSymmetricField3.setVisible(false);
		txtfldAdminSymmetricField4.setVisible(false);
	    bttnAdminSymmetricLogin2.setVisible(false);

		bttnAdminSwitchToSymmetricLogin.setDisable(false);
		bttnAdminSwitchToBiometricLogin.setDisable(false);
		bttnAdminSwitchToStandardLogin.setDisable(true);

	}

	/**
	 * The button event that submit user input (login and chosen nonce) for symmetric login
	 *
	 * @param event The event type thrown, we do not need this, but it must be specified
	 */
	@FXML
	void bttnBottomLoginPaneSymmetricLogin_OnClick(ActionEvent event) {
		sendLoginAndNonceAndReceiveEncryptedNonceAndSystemNameForSymmetricLogin();

	}
	
	/**
	 * The button event that submit user input (encrypted login and system nonce) for symmetric login
	 *
	 * @param event The event type thrown, we do not need this, but it must be specified
	 */
	@FXML
	void bttnBottomLoginPaneSymmetricLogin2_OnClick(ActionEvent event) {
		sendEncryptedLoginAndSystemsNonceAndReceiveConfirmationMessageForSymmetricLogin();

	}
	
	/**
	 * The button event that scan the user's biometric data
	 * 
	 * @param event The event type thrown, we do not need this, but it must be specified
	 */
	@FXML
	void bttnBottomLoginPaneAdminScan_OnClick(ActionEvent event) {
		// Handle scan of the biometric data here
		bttnAdminScan.setDisable(true);
		IntegerProperty milliSeconds = new SimpleIntegerProperty();
		progressIndicatorAdminBiometricScan.progressProperty().bind(milliSeconds.divide(1000.0));
		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(milliSeconds, 0.0)),
				new KeyFrame(Duration.seconds(10.0), e -> {
					bttnAdminScan.setDisable(false);
					try {
						if (userController.oeLoginUsingBiometric("55534552534f444f5552").getValue())
							logonShowPanes(true);
							progressIndicatorAdminBiometricScan.progressProperty().unbind();
							progressIndicatorAdminBiometricScan.setProgress(0.0);
					}
					catch (ServerOfflineException | ServerNotBoundException exception) {
						showExceptionErrorMessage(exception);
					}	
				}, new KeyValue(milliSeconds, 1000.0)));
		timeline.play();
	}

    /*
     * These are other classes accessed by this controller
     */
    /** The user controller, for this GUI it's the admin controller and allows access to admin functions like adding a coordinator. */
	private AdminController userController;
	
	/** Used to get the actor coordinator that was created by the admin, for creating the new window with. */
	private SystemStateController systemstateController;
	
	/*
	 * Other things created for this controller
	 */
	/**
	 * The enumeration dictating the type of edit an admin is doing to a coordinator.
	 */
	private enum TypeOfEdit{
		
		/** Adding a coordinator. */
		Add,
		
		/** Deleting a coordinator. */
		Delete
	}
	
	/**
	 * The list of open windows in the system.
	 * We open a new window when a coordinator is created, so we also should close the window if the coordinator is deleted 
	 */
	private ArrayList<CreateICrashCoordGUI> listOfOpenWindows = new ArrayList<CreateICrashCoordGUI>();
	/*
	 * Methods used within the GUI
	 */

	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logonShowPanes(boolean)
	 */
	protected void logonShowPanes(boolean loggedOn) {
		pnAdminLogon.setVisible(!loggedOn);
		brdpnAdmin.setVisible(loggedOn);
		bttnAdminLogoff.setDisable(!loggedOn);
		bttnAdminLogin.setDefaultButton(!loggedOn);
		if (!loggedOn){
			txtfldAdminUserName.setText("");
			psswrdfldAdminPassword.setText("");
			txtfldAdminUserName.requestFocus();
			for (int i = anchrpnCoordinatorDetails.getChildren().size() -1; i >= 0; i--)
				anchrpnCoordinatorDetails.getChildren().remove(i);
		}
		
	}	
	
	/**
	 * Server has gone down.
	 */
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.AbstractGUIController#serverHasGoneDown()
	 */
	protected void serverHasGoneDown(){
		logoff();
	}
	
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.HasTables#setUpTables()
	 */
	public void setUpTables(){
		setUpMessageTables(tblvwAdminMessages);
	}

	/**
	 * Shows the modify coordinator screen.
	 *
	 * @param type The type of edit to be done, this could be add or delete
	 */
	private void showCoordinatorScreen(TypeOfEdit type){
		for(int i = anchrpnCoordinatorDetails.getChildren().size() -1; i >= 0; i--)
			anchrpnCoordinatorDetails.getChildren().remove(i);
		TextField txtfldUserID = new TextField();
		TextField txtfldUserName = new TextField();
		PasswordField psswrdfldPassword = new PasswordField();
		TextField txtfldBiometricData = new TextField();
		txtfldUserID.setPromptText("User ID");
		Button bttntypOK = null;
		GridPane grdpn = new GridPane();
		grdpn.add(txtfldUserID, 1, 1);
		switch(type){
		case Add:
			bttntypOK = new Button("Create");
			txtfldUserName.setPromptText("User name");
			psswrdfldPassword.setPromptText("Password");
			txtfldBiometricData.setPromptText("55534552534f444f5552");
			grdpn.add(txtfldUserName, 1, 2);
			grdpn.add(psswrdfldPassword, 1, 3);
			grdpn.add(txtfldBiometricData, 1, 4);
			grdpn.add(bttntypOK, 1, 5);
			break;
		case Delete:
			bttntypOK = new Button("Delete");
			grdpn.add(bttntypOK, 1, 2);
			break;		
		}
		bttntypOK.setDefaultButton(true);
		bttntypOK.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!checkIfAllDialogHasBeenFilledIn(grdpn))
					showWarningNoDataEntered();
				else{
					try {
						DtCoordinatorID coordID = new DtCoordinatorID(new PtString(txtfldUserID.getText()));
						switch(type){
						case Add:
							if (userController.oeAddCoordinator(txtfldUserID.getText(), txtfldUserName.getText(), psswrdfldPassword.getText(), txtfldBiometricData.getText()).getValue()){
								listOfOpenWindows.add(new CreateICrashCoordGUI(coordID, systemstateController.getActCoordinator(txtfldUserName.getText())));
								anchrpnCoordinatorDetails.getChildren().remove(grdpn);
							}
							else
								showErrorMessage("Unable to add coordinator", "An error occured when adding the coordinator");
							break;
						case Delete:
							if (userController.oeDeleteCoordinator(txtfldUserID.getText()).getValue()){
								for(CreateICrashCoordGUI window : listOfOpenWindows){
									if (window.getDtCoordinatorID().value.getValue().equals(coordID.value.getValue()))
										window.closeWindow();
								}
								anchrpnCoordinatorDetails.getChildren().remove(grdpn);
							}
							else
								showErrorMessage("Unable to delete coordinator", "An error occured when deleting the coordinator");
							break;
						}
					} catch (ServerOfflineException | ServerNotBoundException | IncorrectFormatException e) {
						showExceptionErrorMessage(e);
					}					
				}
			}
		});
		anchrpnCoordinatorDetails.getChildren().add(grdpn);
		AnchorPane.setTopAnchor(grdpn, 0.0);
		AnchorPane.setLeftAnchor(grdpn, 0.0);
		AnchorPane.setBottomAnchor(grdpn, 0.0);
		AnchorPane.setRightAnchor(grdpn, 0.0);
		txtfldUserID.requestFocus();
	}

	/**
	 * Shows the modify survey/question screen.
	 *
	 * @param type The type of edit to be done, this could be add or delete
	 */
	private void showQuestionScreen(TypeOfEdit type){
		String disclaimer = "Disclaimer:\n"
				+ "Answer 1 has a value of -2 and should be considered as very bad\n"
				+ "Answer 2 has a value of -1 and should be considered as bad\n"
				+ "Answer 3 has a value of 1 and should be considered as good\n"
				+ "Answer 4 has a value of 2 and should be considered as very good";
		for(int i = anchrpnQuestionDetails.getChildren().size() -1; i >= 0; i--)
			anchrpnQuestionDetails.getChildren().remove(i);
		
		TextField txtfldQuestionName = new TextField();
		TextField txtfldAnswer1 = new TextField();
		TextField txtfldAnswer2 = new TextField();
		TextField txtfldAnswer3 = new TextField();
		TextField txtfldAnswer4 = new TextField();
		Text distxt = new Text(disclaimer);
		Rectangle border = new Rectangle(0, 0, Color.TRANSPARENT);
		StackPane sp = new StackPane();
		
		Button bttntypOK = null;
		GridPane grdpn = new GridPane();
		grdpn.setMinWidth(500);
		switch(type){
		case Add:
			bttntypOK = new Button("Create");
			txtfldQuestionName.setPromptText("Question name");
			txtfldAnswer1.setPromptText("Answer 1 name");
			txtfldAnswer2.setPromptText("Answer 2 name");
			txtfldAnswer3.setPromptText("Answer 3 name");
			txtfldAnswer4.setPromptText("Answer 4 name");
			grdpn.add(txtfldQuestionName, 1, 2);
			grdpn.add(txtfldAnswer1, 1, 3);
			grdpn.add(txtfldAnswer2, 1, 4);
			grdpn.add(txtfldAnswer3, 1, 5);
			grdpn.add(txtfldAnswer4, 1, 6);

			grdpn.add(bttntypOK, 1, 8);
			sp.getChildren().addAll(distxt, border);
			grdpn.add(sp, 1, 9);
			txtfldQuestionName.requestFocus();
			break;
		case Delete:
			TextField txtfldQuestionsID = new TextField();
			txtfldQuestionsID.setPromptText("Question ID");
			bttntypOK = new Button("Delete");
			grdpn.add(txtfldQuestionsID, 1, 1);
			grdpn.add(bttntypOK, 1, 2);
			txtfldQuestionsID.requestFocus();
			
			break;		
		}
		bttntypOK.setDefaultButton(true);
		bttntypOK.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!checkIfAllDialogHasBeenFilledIn(grdpn))
					showWarningNoDataEntered();
				else{
					try {
						switch(type){
						case Add: //replace biometricData here
							if (userController.oeAddQuestion(txtfldQuestionName.getText(), 
									txtfldAnswer1.getText(), 
									txtfldAnswer2.getText(), 
									txtfldAnswer3.getText(), 
									txtfldAnswer4.getText()).getValue())
								anchrpnQuestionDetails.getChildren().remove(grdpn);
							else
								showErrorMessage("Unable to add question", "An error occured when adding the question");
							break;
						case Delete:
//							if (userController.oeDeleteCoordinator(txtfldUserID.getText()).getValue()){
//								for(CreateICrashCoordGUI window : listOfOpenWindows){
//									if (window.getDtCoordinatorID().value.getValue().equals(coordID.value.getValue()))
//										window.closeWindow();
//								}
//								anchrpnCoordinatorDetails.getChildren().remove(grdpn);
//							}
							//Do nothing for the moment
							if(true)
								anchrpnQuestionDetails.getChildren().remove(grdpn);
							else
								showErrorMessage("Unable to delete question", "An error occured when deleting the question");
							break;
						}
					} catch (Exception e) {
						showExceptionErrorMessage(e);
					}					
				}
			}
		});
		anchrpnQuestionDetails.getChildren().add(grdpn);
		AnchorPane.setTopAnchor(grdpn, 0.0);
		AnchorPane.setLeftAnchor(grdpn, 0.0);
		AnchorPane.setBottomAnchor(grdpn, 0.0);
		AnchorPane.setRightAnchor(grdpn, 0.0);
	}

	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logon()
	 */
	@Override
	public void logon() {
		if(txtfldAdminUserName.getText().length() > 0 && psswrdfldAdminPassword.getText().length() > 0){
			try {
				if (userController.oeLogin(txtfldAdminUserName.getText(), psswrdfldAdminPassword.getText()).getValue())
					logonShowPanes(true);
			}
			catch (ServerOfflineException | ServerNotBoundException e) {
				showExceptionErrorMessage(e);
			}	
    	}
    	else
    		showWarningNoDataEntered();
	}

	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractAuthGUIController#logoff()
	 */
	@Override
	public void logoff() {
		try {
			if (userController.oeLogout().getValue()){
				logonShowPanes(false);
			}
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
		}
	}
	/* (non-Javadoc)
	 * @see lu.uni.lassy.excalibur.examples.icrash.dev.view.gui.abstractgui.AbstractGUIController#closeForm()
	 */
	@Override
	public void closeForm() {
		try {
			userController.removeAllListeners();
			systemstateController.closeServerConnection();
		} catch (ServerOfflineException | ServerNotBoundException e) {
			showExceptionErrorMessage(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		systemstateController = new SystemStateController();
		logonShowPanes(false);
		setUpTables();
		
	}

	@Override
	public PtBoolean setActor(JIntIsActor actor) {
		try {
			if (actor instanceof ActAdministrator)
				try{
					userController = new AdminController((ActAdministrator)actor);
					try{
						userController.getAuthImpl().listOfMessages.addListener(new ListChangeListener<Message>() {
							@Override
							public void onChanged(ListChangeListener.Change<? extends Message> c) {
								addMessageToTableView(tblvwAdminMessages, c.getList());
							}
						});
					} catch (Exception e){
						showExceptionErrorMessage(e);
					}
				}catch (RemoteException e){
					Log4JUtils.getInstance().getLogger().error(e);
					throw new ServerOfflineException();
				} catch (NotBoundException e) {
					Log4JUtils.getInstance().getLogger().error(e);
					throw new ServerNotBoundException();
				}
			else
				throw new IncorrectActorException(actor, ActAdministrator.class);
		} catch (ServerOfflineException | ServerNotBoundException | IncorrectActorException e) {
			showExceptionErrorMessage(e);
			return new PtBoolean(false);
		}
		return new PtBoolean(false);
	}	
	
	@Override
	public void sendLoginAndNonceAndReceiveEncryptedNonceAndSystemNameForSymmetricLogin() {
		if(txtfldAdminSymmetricField1.getText().length() > 0 && txtfldAdminSymmetricField2.getText().length() > 0){
			try {
				int nonce = Integer.parseInt((txtfldAdminSymmetricField2.getText()));
				if (userController.oeSendLoginAndNonceAndReceiveEncryptedNonceAndSystemNameForSymmetricLogin(txtfldAdminSymmetricField1.getText(), nonce).getValue())
					txtfldAdminSymmetricField3.setVisible(true);
				    txtfldAdminSymmetricField4.setVisible(true);
				    bttnAdminSymmetricLogin2.setVisible(true);
					
					txtfldAdminSymmetricField1.setVisible(false);
				    txtfldAdminSymmetricField2.setVisible(false);
				    bttnAdminSymmetricLogin.setVisible(false);
					System.out.println("First step of symmetric login successful");
			}
			catch (ServerOfflineException | ServerNotBoundException e) {
				showExceptionErrorMessage(e);
			}	
    	}
    	else
    		showWarningNoDataEntered();
	} 
	@Override
	public void sendEncryptedLoginAndSystemsNonceAndReceiveConfirmationMessageForSymmetricLogin() {
		if(txtfldAdminSymmetricField3.getText().length() > 0 && txtfldAdminSymmetricField4.getText().length() > 0){
			try {
				if (userController.oeSendEncryptedLoginAndSystemsNonceAndReceiveConfirmationMessageForSymmetricLogin(txtfldAdminSymmetricField3.getText(), txtfldAdminSymmetricField4.getText()).getValue() == true)
					txtfldAdminSymmetricField3.setVisible(false);
				    txtfldAdminSymmetricField4.setVisible(false);
				    bttnAdminSymmetricLogin2.setVisible(false);
				    
				    txtfldAdminSymmetricField1.setVisible(true);
				    txtfldAdminSymmetricField2.setVisible(true);
				    bttnAdminSymmetricLogin.setVisible(true);
					
				    logonShowPanes(true);
					System.out.println("Second step of symmetric login successful");
			}
			catch (ServerOfflineException | ServerNotBoundException e) {
				showExceptionErrorMessage(e);
			}	
    	}
    	else
    		showWarningNoDataEntered();
	}
}
