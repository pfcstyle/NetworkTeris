package Teris;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

import GameConnect.TerisCapture;
import Sql.UserSql;
import UI.MenuFrame;
import UI.UsernameFrame;

import com.sun.org.apache.bcel.internal.generic.NEW;
/**
 * 游戏主类
 * @author pfc
 *
 */
public class Teris{
	//记录是敌人还是自己
	public boolean isEnemy=false;
	//游戏主界面的宽和高
	public static int SCREEN_W = 275;
	public static int SCREEN_H = 500;
	
	public static int beginDrawX=0;
	public static int beginDrawY=0;
	public static final int caseWidth = 25; // 小格子边长
	public static final int case_L = 4; // 块格子个数（4*4）
	public static int body_W = 11;//游戏池宽（以小方块为单位）
	public static int body_H = SCREEN_H / caseWidth+3;//游戏池高（以小方块为单位）
	
	//消行获得分数
	public int score1=100;
	public int score2=300;
	public int score3=700;
	public int score4=1500;
	
	public  int[][] body = new int[body_W][body_H]; // 游戏池
	public  boolean[] isReCase = new boolean[body_H]; // 消去的行
	public int body_top=0;//记录游戏池的 顶端
	
	public int[][] currentCase = new int[case_L][case_L]; // 当前活动图形
	public int[][] tempCase = new int[case_L][case_L]; // 中间活动图形（假想的旋转后的图形）
	public int[][] nextCase = new int[case_L][case_L]; // 下一个活动图形
	public int[] indexrnd=new int[]{5,9,4,0,2,5,7,1,0,0,27,5,5,6,24,0,0,26,15,17,9,0,0,15,10,13,17,25,24,0,26,1,5,6,4,12,19,18,17,26,27,14,13,15,16,2,8,7,1,14};
	public int[] nextColornd=new int[]{0,5,4,3,5,4,0,5,2,1,4,2,0,1,3,5,4,1,0,2,1,4,2,0,3,4,1,0,5,1,1,3,5,4,1,0,2,0,5,2};
	public int indexCount=0;
	public int nextColorCount=0;
	public int currentX; // 当前块坐标（数组内）
	public int currentY; // 当前块坐标
	
	public int currentColor; // 当前块颜色
	public int nextColor; // 下一块颜色
	
	public int index; // 生成块的下标
	public int nowIndex; // 当前块的下标
	
	public static int[][][] store = new int[][][] { {//存储所有的方块，并且按照顺时针排序，便于旋转
		{ 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 } }, {// I
		{ 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } }, {// I
		{ 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 1 } }, {// I
		{ 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 1, 1, 1 }, { 0, 0, 0, 0 } }, {// I
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// O
		{ 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// L
		{ 0, 0, 1, 0 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 0, 0, 0 }, { 1, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// J
		{ 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 0, 0, 0 }, { 1, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// T
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 0, 0, 0 }, { 0, 1, 1, 0 }, { 1, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } }, {// S
		{ 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// Z
		{ 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } }, {// Z
		{ 0, 0, 0, 0 }, { 1, 1, 0, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } }, {// Z
		{ 0, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 } } };// Z
	
	public int score = 0;//记录分数
	public int showScore = 0;//显示分数
	public int reLine = 0;//消去的行数
	public int level=1;//等级
	public int speedTime=20;//下降速度（下降一格需要的时间）
	public int speedCount=0;//下降速度计时器
	
	public static int iStep = body_H; // 快速落下 步数
	public static int frame = 0;
	public static int comboFrame = 0;
	public static int addFrame = 0;
	public static int frame5 = 5;
	public static int frame10 = 10;
	public static int frame20 = 20;
	public static int removeFrame = 5;
	public static int fastFrame = 1;
	public static int overFrame = 10;
	public static int createdFrame = 280;
	// 开关
	public boolean isNewCase = false;
	public boolean isCombo = false;//是否到底了
	public boolean isRemove = false;
	public boolean isMoveDown=false;
	public boolean isLevelJudge=true;//是否开启等级功能
	public boolean isReverseControl=false;//是否开启反向控制功能
	
	public Random rand;
	
	public static final int STATE_START = 4;//游戏准备开始
	public static final int STATE_PLAY = 5;// 正在玩
	public static final int STATE_GAMEOVER = 7;// 游戏结束
	public static final int STATE_REMOVE = 8;// 消去
	public static final int STATE_PAUSE = 9;// 暂停
	public static final int STATE_TOOL=3;//使用即时道具
	public static final int STATE_USERPRINT=6;//用户输入姓名状态
	public int gamestate = STATE_START;//记录游戏状态
	
	public static Image[] blockImages=new Image[6];
	
	public Teris(boolean isEnemy){
		this.isEnemy=isEnemy;
	}
	public void startGame() {
		// 清空游戏池
		for (int i = 0; i < body.length; i++) {
			for (int j = 0; j < body[i].length; j++) {
				if (body[i][j] != 0) {
					body[i][j] = 0;
				}
			}
		}
		rand=new Random();//实例化随机变量
		beginDrawY=SCREEN_H - caseWidth * body_H;
		// 分数归位
		score = 0;
		score1=100;
		score2=300;
		score3=700;
		score4=1500;
		showScore = 0;
		// 消去行数归零
		reLine = 0;
		//等级归1
		level=1;

		// 要连续产生2块
		newCase();
		isNewCase = true;
		gamestate = STATE_PLAY;
	}
	
	public void logic(){//游戏逻辑
		switch (gamestate) {
		case STATE_START:
			startGame();
			break;
		case STATE_PLAY:
			//判断分数，并实现分数的渐变
			if (showScore < score) {
				showScore += (score - showScore) >> 1;
			}
			if (showScore < score) {
				showScore++;
				if(isLevelJudge){
					if(showScore>5000){
						level=2;
					}else if(showScore>10000){
						level=3;
					}else if(showScore>20000){
						level=4;
					}
				}
			}
			//根据等级确定下落速度
			switch(level){
			case 1:speedTime=20;break;
			case 2:speedTime=15;break;
			case 3:speedTime=10;break;
			case 4:speedTime=5;break;
			default:break;
			}
			speedCount++;
			if (speedCount > speedTime) {
				speedCount = 0;
				isMoveDown = true;
			}
			if (isNewCase) {
				newCase();
			} else if (isCombo) {
				comboFrame++;
				if (comboFrame > frame5) {
					comboFrame = 0;
					doCombo();
				}
			} else if (isRemove) {
				gamestate = STATE_REMOVE;
			} else if (isMoveDown) {
				moveDown();
			}
			break;
		case STATE_REMOVE:
			doRemove();
			gamestate=STATE_PLAY;
			break;
		case STATE_TOOL:
			isMoveDown=false;
			break;
		case STATE_GAMEOVER:
//			if(MenuFrame.gamemode==MenuFrame.MODE_SINGLE){
//				toGameOver();
//			}
			toGameOver();
			break;
		case STATE_PAUSE:
			break;
		case STATE_USERPRINT:
			break;
		}
	}
	
	public void draw(Graphics g){//游戏绘图
		switch (gamestate) {
		case STATE_PLAY:
			drawBody(g);
			drawData(g);
			drawCurrentCase(g);
			drawNextCase(g);
			break;
		case STATE_REMOVE:
			break;
		case STATE_GAMEOVER:
			break;
		case STATE_TOOL:
			drawBody(g);
			drawData(g);
			drawCurrentCase(g);
			drawNextCase(g);
			break;
		default:
			break;
		}
	}
	private void drawData(Graphics g){//画游戏数据
		g.setColor(Color.white);
		g.fillRect(SCREEN_W, 0, Player.panel_W-SCREEN_W, Player.panel_H);
		g.setColor(Color.RED);
		g.drawString("Level", caseWidth * body_W+20, 8 * caseWidth);
		g.drawString("Line", caseWidth * body_W+20, 12 * caseWidth);
		g.drawString("Score", caseWidth * body_W+20, 16 * caseWidth);
		g.drawString("" + level, caseWidth * body_W+20, 10 * caseWidth);
		g.drawString("" + reLine, caseWidth * body_W+20, 14 * caseWidth);
		g.drawString("" + showScore, caseWidth * body_W+20, 18 * caseWidth);
	}
	private void drawBody(Graphics g){//画游戏池
		for(int i=0;i<body_W;i++){
			for (int j = 0; j < body_H; j++) {
				if (body[i][j] != 0) {
					g.drawImage(blockImages[body[i][j]-1],beginDrawX + i
							* caseWidth, beginDrawY + j * caseWidth,null);
				}
			}
		}
	}
	private void drawCurrentCase(Graphics g){//画当前方块
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					g.drawImage(blockImages[currentColor], beginDrawX
							+ (i + currentX) * caseWidth, beginDrawY
							+ (j + currentY) * caseWidth, null);
				}
			}
		}
	}
	public void drawNextCase(Graphics g){//画下一个方块
		for (int i = 0; i < nextCase.length; i++) {
			for (int j = 0; j < nextCase[i].length; j++) {
				if (nextCase[i][j] != 0) {
					g.drawImage(blockImages[nextColor], SCREEN_W+ i
							* caseWidth + caseWidth / 2, j * caseWidth+caseWidth / 2, null);
				}
			}
		}
	}
	public void drawRemove(){
		for (int i = 0; i < body_H; i++) {
			if (isReCase[i]) {
				
			}
		}
	}
	public boolean isSpace(int x, int y) {//判断是否为空
		if (x < 0 || x > body.length - 1) {
			return false;
		}
		if (y < 0 || y > body[0].length - 1) {
			return false;
		}

		if (body[x][y] == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void moveLeft() {//左移
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i - 1, currentY + j)) {
						return;
					}
				}
			}
		}
		currentX--;
		isCombo = false;
		comboFrame = 0;
	}
	
	public void moveRight() {//右移
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i + 1, currentY + j)) {
						return;
					}
				}
			}
		}
		currentX++;
		isCombo = false;
		comboFrame = 0;
	}
	
	public void moveDown() {//下移
		speedCount = 0;
		isMoveDown = false;
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i, currentY + j + 1)) {
						isCombo = true;
						return;
					}
				}
			}
		}
		currentY++;
	}
	
	public void fastDrop() {//快速下移
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					for (int k = currentY + j; k < body[0].length; k++) {
						if (!isSpace(currentX + i, k + 1)
								|| k == body[0].length - 1) {
							if (iStep > k - currentY - j) {
								iStep = k - currentY - j;
							}
						}
					}
				}
			}
		}
		currentY += iStep;
		if (iStep > 0) {
			comboFrame = frame5 >> 1;
			isCombo = true;
		}
		iStep = body_H;
	}
	
	public void doCombo() {//把活动块加到游戏池
		isCombo = false;
		// 加入
		for (int i = currentCase.length-1; i >=0; i--) {
			for (int j = currentCase.length-1; j >=0; j--) {
				if (currentCase[i][j] != 0) {
					body[currentX + i][currentY + j] = currentColor +1;
					body_top=currentY+j;
				}
			}
		}

		// 处理能消去的行
		boolean remove = false;
		for (int i = 0; i < body_H; i++) {
			remove = true;
			for (int j = 0; j < body_W; j++) {
				if (isSpace(j, i)) {
					remove = false;
					break;
				}
			}
			isReCase[i] = remove;
			if (isReCase[i]) {
				reLine++;
				isRemove = true;
			}
		}
		isNewCase = !isRemove; // 不能消去 就生产新块
	}
	
	public void doRemove() {//消行
		int removeLine = 0;
		for (int i = 0; i < body_H; i++) {
			if (isReCase[i]) {
				removeLine++;
				for (int j = i; j > 0; j--) {
					for (int k = 0; k < body_W; k++) {
						body[k][j] = body[k][j - 1];
					}
				}
			}
		}
		// 记分
		switch (removeLine) {
		case 1:
			score += score1;
			break;
		case 2:
			score += score2;
			break;
		case 3:
			score += score3;
			break;
		case 4:
			score += score4;
			break;
		}
		isRemove = false;
		isNewCase = true; // 不能消去 就生产新块
	}
	
	public void turn() {//旋转
		boolean canTurn = false;
		int tempX = 0, tempY = 0;
		int tempIndex;

		// 顺时针转
		tempIndex = nowIndex;
		if (tempIndex % 4 > 0) {
			tempIndex--;
		} else {
			tempIndex += 3;
		}
		for (int i = 0; i < tempCase.length; i++) {
			for (int j = 0; j < tempCase[i].length; j++) {
				tempCase[i][j] = store[tempIndex][i][j];
			}
		}

		tempX = currentX;
		tempY = currentY;

		canTurn = turnCheck(tempX, tempY);
		// 加强判断
		while (!canTurn) {
			// 下移1
			canTurn = turnCheck(tempX, tempY + 1);
			if (canTurn) {
				tempY++;
				break;
			}
			// 下左移1
			canTurn = turnCheck(tempX - 1, tempY + 1);
			if (canTurn) {
				tempX--;
				tempY++;
				break;
			}
			// 下右移1
			canTurn = turnCheck(tempX + 1, tempY + 1);
			if (canTurn) {
				tempX++;
				tempY++;
				break;
			}
			// 下移2
			canTurn = turnCheck(tempX, tempY + 2);
			if (canTurn) {
				tempY += 2;
				break;
			}
			// 左移1
			canTurn = turnCheck(tempX - 1, tempY);
			if (canTurn) {
				tempX--;
				break;
			}
			// 右移1
			canTurn = turnCheck(tempX + 1, tempY);
			if (canTurn) {
				tempX++;
				break;
			}
			// 上移1
			canTurn = turnCheck(tempX, tempY - 1);
			if (canTurn) {
				tempY--;
				break;
			}
			// 上左移1
			canTurn = turnCheck(tempX - 1, tempY - 1);
			if (canTurn) {
				tempX--;
				tempY--;
				break;
			}
			// 上右移1
			canTurn = turnCheck(tempX + 1, tempY - 1);
			if (canTurn) {
				tempX++;
				tempY--;
				break;
			}
			// 左移2
			canTurn = turnCheck(tempX - 2, tempY);
			if (canTurn) {
				tempX -= 2;
				break;
			}
			// 右移2
			canTurn = turnCheck(tempX + 2, tempY);
			if (canTurn) {
				tempX += 2;
				break;
			}
			// 上移2
			canTurn = turnCheck(tempX, tempY - 2);
			if (canTurn) {
				tempY -= 2;
				break;
			}
			break;
		}
		// 转换后
		if (canTurn) {
			nowIndex = tempIndex;
			currentX = tempX;
			currentY = tempY;
			for (int i = 0; i < currentCase.length; i++) {
				for (int j = 0; j < currentCase[i].length; j++) {
					currentCase[i][j] = tempCase[i][j];
				}
			}

			isCombo = false;
			comboFrame = 0;
		}
	}
	
	public boolean turnCheck(int tempX, int tempY) {//检测是否可以旋转
		for (int i = 0; i < tempCase.length; i++) {
			for (int j = 0; j < tempCase[i].length; j++) {
				if (tempCase[i][j] != 0) {
					if (!isSpace(tempX + i, tempY + j)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void newCase() {//随机产生新方块
		nowIndex = index;
		if(MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_CLIENT||MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_SERVER){
			index=indexrnd[indexCount];
			if(indexCount==49){
				indexCount=0;
			}else{
				indexCount++;
			}
		}else{
			index = Math.abs(rand.nextInt() % 28);
		}
		currentColor = nextColor;
		if(MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_CLIENT||MenuFrame.gamemode==MenuFrame.MODE_DOUBLE_CON_SERVER){
			nextColor=nextColornd[nextColorCount];
			if(nextColorCount==39){
				nextColorCount=0;
			}else{
				nextColorCount++;
			}
		}else{
			index = Math.abs(rand.nextInt() % 28);
		}
		for (int i = 0; i < nextCase.length; i++) {
			for (int j = 0; j < nextCase[i].length; j++) {
				// 把下一块提上来
				currentCase[i][j] = nextCase[i][j];

				// 下一块刷新
				nextCase[i][j] = store[index][i][j];
			}
		}
		for (int j = 0; j < currentCase.length; j++) {
			for (int i = 0; i < currentCase[j].length; i++) {
				if (currentCase[i][j] != 0) {
					currentY = currentCase.length - 1 - j;
				}
			}
		}

		int width = 0;//用于计算currentX;
		int start = 0;
		int end = 0;
		boolean isBreak;//用于判断外层的for循环是否跳出

		isBreak = false;
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase.length; j++) {
				if (currentCase[i][j] != 0) {
					start = i;
					isBreak = true;
					break;
				}
			}
			if (isBreak) {
				break;
			}
		}

		isBreak = false;
		for (int i = currentCase.length - 1; i >= 0; i--) {
			for (int j = 0; j < currentCase.length; j++) {

				if (currentCase[i][j] != 0) {
					end = i + 1;
					isBreak = true;
					break;
				}

			}

			if (isBreak) {
				break;
			}
		}

		width = end - start;

		currentX = (body_W - width) / 2 - start;

		isNewCase = false;
		isGameOver();
	}
	
	public void isGameOver() {
		for (int i = 0; i < currentCase.length; i++) {
			for (int j = 0; j < currentCase[i].length; j++) {
				if (currentCase[i][j] != 0) {
					if (!isSpace(currentX + i, currentY+j)) {
						// 游戏结束
						gamestate = STATE_GAMEOVER;
						//System.out.println(gamestate);
						// startGame();
						return;
					}
				}
			}
		}
	}
	
	public void toGameOver(){
		UserSql sqlcon=new UserSql();
		String sqlStr=new String("select *from Player where rank<11");
		sqlcon.executeQuery(sqlStr);
		int rank=1;
		try {
			while(sqlcon.rs.next()){
//				System.out.println(sqlcon.rs.getInt(2));
				if(showScore<sqlcon.rs.getInt(2)){
					rank++;
				}
//				Vector hang=new Vector();
//				hang.add(sqlcon.rs.getString(1));
//				hang.add(sqlcon.rs.getInt(2));
//				hang.add(sqlcon.rs.getInt(3));
//				//System.out.println(sqlcon.rs.getString(2));
//				jilu.add(hang);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(rank<=10){
			sqlcon.close();
			//System.out.println("出来");
			UsernameFrame usernameFrame=new UsernameFrame(MenuFrame.sgf,rank,showScore);
			gamestate=STATE_USERPRINT;
		}else{
			sqlcon.close();
			JOptionPane.showMessageDialog(MenuFrame.sgf, "对不起，冲击风云榜失败！请再接再厉！");
			
		}
	}
	
	public void action(char e){//用于联机动作及电脑AI的控制
		switch(e){
		case 'L':
		case 'l':
			moveLeft();
			break;
		case 'R':
		case 'r':
			moveRight();
			break;
		case 'D':
		case 'd':
			moveDown();
			break;
		case 'f':
		case 'F':
			fastDrop();
			break;
		case 'T':
		case 't':
			turn();
			break;
//		case 'i':
//			nextColor=TerisCapture.ein[1]-48;
//			index=TerisCapture.ein[2]-48;
//			int t=TerisCapture.ein[3]-48;
//			if(t>=0&&t<=9){
//				index=index*10+t;
//			}
//			break;
		}
	}
	
	public void onKeyDown(KeyEvent e){
		switch(e.getKeyChar())
		   {  
			  case 'w':
			  case 'W':
				  turn(); 
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("t");
				  break; 
			  case 's':
			  case 'S':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("f");
				  fastDrop();break;  
			  case 'a':
			  case 'A':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("l");
				  moveLeft();break;  
			  case 'd':
			  case 'D':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("r");
				  moveRight();break;
			  default:return;//用户按错键时结束方法
		   }
	}
	
	public void onReverseKeyDown(KeyEvent e){
		switch(e.getKeyChar())
		   {  
			  case 's':
			  case 'S':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("t");
				  turn(); break; 
			  case 'w':
			  case 'W':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("f");
				  fastDrop();break;  
			  case 'd':
			  case 'D':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("l");
				  moveLeft();break;  
			  case 'a':
			  case 'A':
				  if(MenuFrame.gamemode > MenuFrame.MODE_DOUBLE_AI)
				  TerisCapture.outstream("r");
				  moveRight();break;
			  default:return;//用户按错键时结束方法
		   }
	}

}
