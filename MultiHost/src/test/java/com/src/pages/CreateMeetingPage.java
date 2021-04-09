package com.src.pages;

import org.openqa.selenium.By;

public class CreateMeetingPage {

	public static By btn_switchJap = By.xpath("//input[@value='jap']");
	public static By img_Close = By.xpath("//div[@class='modal-content']//button[@class='close']");
	public static By btn_Share = By.xpath("//button[text()='Yes']");
	public static By Canvas_Clear = By
			.xpath("//button[@class='textSetDefault cursorPointer whiteOnClick btn btn-secondary']");
	public static By img_Eraser = By.xpath("//img[@alt='ERASER']");
	public static By btn_Yes = By.xpath("//button[text()='Yes']");
	public static By txt_Email = By.xpath("//input[@name='userName']");
	public static By txt_password = By.xpath("//input[@name='password']");
	public static By btn_signIn = By.xpath("//button[@class='customBtn btnSignIn whiteOnClick btn btn-primary']");
	public static By errorMsg = By.xpath("//div[contains(@class,'errors')]");
	public static By engLang = By.cssSelector("div>div:nth-child(1)>label>input");
	public static By creatMeeting = By.xpath("//img[contains(@src,'ic_meeting')]");
	public static By setMeetingTab = By.xpath("//div[@class='row']//div[2]");
	public static By startPasscodeMeeting = By.id("proceedBtn");
	public static By japnLang = By.cssSelector("div>div:nth-child(2)>label>input");
	public static By txt_SessionName = By.xpath("//div[@id='inviteeScrollView'][1]//input[contains(@class,'formControlName')]");
	public static By btn_Proceed = By.xpath("//button[@id='proceedBtn']");
	public static By btn_ProceedUser = By.xpath("//button[contains(@class,'proceedBtn')]");
	public static By img_AddInvitee = By.xpath("//span//div//img[contains(@src,'ic_participants')]");
	public static By btn_CopySessionLink = By
			.xpath("//div[@class='row']//div[3]//button[contains(@class,'admitButton')]");
	public static By img_Close_manual = By.xpath("//button[@class='close']//span");
	public static By img_Close_participant = By.xpath("//img[contains(@src,'ic_close')]");
	public static By txt_CopyURL = By.xpath("//form//div//input");
	public static By btn_ManualEntry = By.xpath("//div[@class='row']//div[1]//button[contains(@class,'admitButton')]");
	public static By txt_UserName = By.xpath("//input[@name='userName']");
	public static By waitingUsers = By.xpath("//div[contains(@class,'alignWaitingUser')]");
	public static By admit_btn = By
			.xpath("//div[contains(@class,'alignWaitingUser')]//div[3]//div//button[contains(@class,'admitButton')]");
	public static By img_Video = By.xpath("//div[contains(@class,'parentParticipants')]//img[contains(@src,'video')]");
	public static By btn_Confirm = By.xpath("//button[@class='buttons width-25 btn-accept customBtn btn btn-#FF8E01']");
	public static By accept_modal = By.xpath("//div[@class='modal-content']");
	public static By leaveSessionBtn = By
			.xpath("//div[contains(@class,'parentParticipants')]//button[contains(@class,'endSession')]");
	public static By leave_Session_Accept = By.xpath("//button[contains(@class,'btn-accept')]");
	public static By audio_icon = By.xpath("//div[contains(@class,'parentParticipants')]//img[contains(@src,'audio')]");
	public static By screenShare_icon = By
			.xpath("//div[contains(@class,'parentParticipants')]//img[contains(@src,'share')]");
	public static By img_ScrShareClose = By.xpath("//img[contains(@class,'iconScreenShareClose')]");
	// public static By btn_Share = By.xpath("//button[text()='Yes']");
	public static By screenshare_div = By.cssSelector("div[id='screenShareDiv']");
	// chat with participants
	public static By chat_icon = By.xpath("//div[contains(@class,'parentParticipants')]//img[@title='Chat']");
	public static By chatWindow = By.id("chatWindow");
	public static By msg_chatIcon = By.cssSelector("img[src='/static/media/ic_bubble_message.5cdb5734.svg']");

	// canvas elements
	public static By btn_AddCanvas = By.xpath("//span[@class='cardText ']");
	public static By btn_DelCanvas = By.xpath(
			"//div[@class='canvasListScrollX']//div[@class='card card']//img[@class='closeIcon cursorPointer card-img']");
	public static By btn_DelCanvasYes = By.xpath("//button[text()='Yes']");
	public static By img_CanvasPresent = By.xpath("//div[@class='canvasList paddingPoint2em cursorPointer']");
	public static By LOC_Canvas = By.xpath("//div[@class='canvasElement']//canvas[@class='upper-canvas ']");
	public static By img_Color = By.xpath("//div[@id='exampleSelect']");
	public static By btn_Purple = By.cssSelector("div>div>button:nth-child(15)>span>div");
	public static By btn_Green = By.cssSelector("div>div>button:nth-child(5)>span>div");
	public static By btn_Red = By.cssSelector("div>div>button:nth-child(3)>span>div");
	public static By btn_Yellow = By.cssSelector("div>div>button:nth-child(9)>span>div");

	public static By img_Annotations = By.xpath("//img[contains(@src,'annotation')]");
	public static By img_Circle = By.xpath("//img[contains(@src,'ellipse')]");
	public static By img_Rectangle = By.xpath("//img[contains(@src,'rectangle')]");
	public static By img_Line = By.xpath("//img[contains(@src,'line')]");
	public static By img_Draw = By.xpath("//img[contains(@src,'freeehand')]");
	public static By img_Download = By.xpath("//img[contains(@src,'download')]");

	// Image upload
	public static By img_UploadImage = By.xpath("//label//img[contains(@src,'image')]");
	public static By img_UploadPdf = By.xpath("//img[contains(@src,'pdf')]");

	// Share location
	//public static By img_Location = By.xpath("//span[@id='LOCATION']//img");
	public static By img_Location = By.xpath("//label[text()='Location']");
	public static By location_div = By.xpath("//div[@class='modal-content']");
	public static By btn_ShareLiveLoc = By.xpath("//div[@class='modal-content']/div[3]/button[2]");
	public static By btn_DnldToCanvas = By.xpath("//img[contains(@src,'ic_share_map')]");
	public static By btn_StopShareLiveLoc = By.xpath("//img[contains(@src,'ic_stop_share_my_location')]");
	public static By closeModal = By.xpath("//button[@class='close']//span");
	public static By endCall_icon = By.xpath("//img[contains(@src,'cut')]");
	public static By endSS_icon = By.xpath("//img[contains(@src,'close')]");
	// public static By btn_Yes = By.xpath("//button[text()='Yes']");

	// Snapshot
	public static By snapShot_icon = By.xpath("//span[@id='SNAPSHOT']//img");
	public static By snapShot_div = By.xpath("//video[@id='snapshotvideo']");
	public static By takeSnapshot_Btn = By.xpath("//button[@id='capture']//img");
	public static By close_snapShot_icon = By.xpath("//button[@id='close']");

	// Canvas Text decoration functionality
	public static By img_Text = By.xpath("//img[contains(@src,'text')]");
	public static By fontStyle_drpDown = By.cssSelector("span[id='DIVErase']>span:nth-child(1)>select");
	public static By fontSize_drpDown = By.cssSelector("span[id='DIVErase']>span:nth-child(3)>select");
	public static By fontSize_drpDownJap = By.cssSelector("span[id='DIVErase']>span:nth-child(3)>select");

	// Erase and close canvas
	public static By canvas_Erase = By.xpath("//img[contains(@src,'rubber')]");
	public static By canvas_Clear = By.xpath("//button[contains(@class,'textSetDefault')]");
	public static By Canvas_Close = By.xpath("//img[@class='closeIcon cursorPointer card-img']");

	// Make user presenter and share screen
	public static By userThumb = By.xpath("//div[contains(@title,'User1')]");
	public static By hostThumb = By.xpath("//div[contains(@class,'agentText')]");
	public static By makePresentorEng = By.xpath("//button[contains(text(),'Make Presenter')]");
	public static By makePresentorJap = By.xpath("//button[contains(text(),'プレゼンターを設定')]");

	public static By maximizeScreenS = By.xpath("//img[contains(@src,'zoom')]");
	public static By downloadScreenS = By.xpath("//a[@id='screenShareDownload']");
}
