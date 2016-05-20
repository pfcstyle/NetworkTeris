package Teris;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * 道具类
 *@author PfC
 */
public class Tools {
	//炸弹道具
	public int boom=1;//炸弹数量
	public boolean isBoom=false;//使用开关
	public void boomLogic(Player self){//炸弹的逻辑：消除己方顶部3行
		self.teris.gamestate=Teris.STATE_TOOL;
		boom--;
		isBoom=false;
		for(int j=0;j<3;j++){
			for(int i=0;i<self.teris.body_W;i++){
				self.teris.body[i][self.teris.body_top]=0;
			}
			if(self.teris.body_top<Teris.body_H-1){
				self.teris.body_top++;
			}
		}
	}
	public void drawBoom(Graphics g,Player self){//绘制炸弹道具释放效果
		
	}
	
	//加倍积分道具
	public int scores2=1;//数量
	public boolean isScores2=false;//使用开关
	public boolean isNewScores2=false;//判断是否 是刚使用此道具以决定是否时间叠加
	public int scores2Time;//加倍时间
	public void scores2Logic(Player self){//逻辑
		if(isNewScores2){
			scores2Time+=600;
			isNewScores2=false;
			scores2--;
			self.teris.score1=200;
			self.teris.score2=600;
			self.teris.score3=1400;
			self.teris.score4=3000;
		}else{
			scores2Time--;
			if(scores2Time==0){
				isScores2=false;
				self.teris.score1=100;
				self.teris.score2=300;
				self.teris.score3=700;
				self.teris.score4=1500;
			}
		}
		
	}
	public void drawScores2(Graphics g,Player self){
		
	}
	
	//怜悯炸弹
	public int pitiBoom;//数量
	public boolean ispitiBoom=false;//开关
	public void pitiBoomLogic(Player enemy){//逻辑
		enemy.teris.gamestate=Teris.STATE_TOOL;
		boom--;
		isBoom=false;
		for(int j=0;j<5;j++){
			for(int i=0;i<enemy.teris.body_W;i++){
				enemy.teris.body[i][enemy.teris.body_top]=0;
			}
			if(enemy.teris.body_top<Teris.body_H-1){
				enemy.teris.body_top++;
			}
		}
	}
	public void drawPitiBoom(Graphics g,Player enemy){
		
	}
	
	//加速道具
	public int speedUp;//数量
	public boolean isSpeedUp=false;//开关
	public int speedUpTime;
	public boolean isNewSpeedUp=false;
	public void speedUpLogic(Player enemy){//逻辑
		if(isNewSpeedUp){
			enemy.teris.isLevelJudge=false;
			speedUpTime+=600;
			isNewSpeedUp=false;
			speedUp--;
			enemy.teris.speedTime=2;
			enemy.teris.level=5;
		}else{
			speedUpTime--;
			if(speedUpTime==0){
				isSpeedUp=false;
				enemy.teris.isLevelJudge=true;//速度还原
			}
		}
	}
	public void drawSpeedUp(Graphics g,Player self){
		
	}
	
	//反向道具
	public int reverseControl;//数量
	public boolean isReverseControl=false;//开关
	public int reverseTime;//计时器
	public boolean isNewReverseControl=false;
	public void reverseControlLogic(Player enemy){
		if(isNewReverseControl){
			reverseTime+=600;
			isNewSpeedUp=false;
			reverseControl--;
			enemy.teris.isReverseControl=true;
		}else{
			reverseTime--;
			if(reverseTime==0){
				isReverseControl=false;
				enemy.teris.isReverseControl=false;
			}
		}
	}
	public void drawReverseContorl(Graphics g,Player enemy){
		
	}
	//加行道具
	public int linesAdd;//数量
	public int linesAddCount=0;//计时器，实现渐变效果
	public boolean isLinesAdd=false;//开关
	public void linesAddLogic(Player enemy){//逻辑
		enemy.teris.gamestate=Teris.STATE_TOOL;
		linesAddCount++;
		for (int j = enemy.teris.body_top-1; j <Teris.body_H-1; j++) {
			for (int k = 0; k < Teris.body_W; k++) {
				enemy.teris.body[k][j] = enemy.teris.body[k][j + 1];
			}
		}
		for(int k=0;k<Teris.body_W;k++){
			enemy.teris.body[k][Teris.body_H-1] = (new Random()).nextInt(2);
		}
		enemy.teris.body[(new Random()).nextInt(11)][Teris.body_H-1]=0;
		if(linesAddCount==5){
			enemy.teris.gamestate=Teris.STATE_PLAY;
			isLinesAdd=false;
		}
	}
	public void drawlinesAdd(Graphics g,Player enemy){
		
	}
	
	public void drawSingle(Graphics g,Player self){//绘制单人道具效果
		if(isBoom){
			
		}
		else if(isScores2){
			
		}
	}
	
	public void drawDouble(Graphics g,Player enemy){//绘制双人道具效果
		if(ispitiBoom){
			
		}
		else if(isSpeedUp){
			
		}
		else if(isReverseControl){
			
		}
		else if(isLinesAdd){
			
		}
	}
	public void singleLogic(Player self){//单人道具逻辑
		if(isBoom){
			boomLogic(self);
		}
		else if(isScores2){
			scores2Logic(self);
		}
	}
	public void doubleLogic(Player enemy){//双人道具逻辑
		if(ispitiBoom){
			pitiBoomLogic(enemy);
		}
		else if(isSpeedUp){
			speedUpLogic(enemy);
		}
		else if(isReverseControl){
			
		}
		else if(isLinesAdd){
			linesAddLogic(enemy);
		}
	}
	public void onKeyDown(KeyEvent e){
		switch(e.getKeyChar())
		   {  
			  case '1':
				  if(boom>0){
				  isBoom=true;
				  }
				  break; 
			  case '2':
				  if(scores2>0){
					  isNewScores2=true;
					  isScores2=true;
				  }
				  break;
			  case '3':
				  if(pitiBoom>0){
					  ispitiBoom=true;
				  }
				  break;
			  case '4':
				  if(speedUp>0){
					  isNewSpeedUp=true;
					  isSpeedUp=true;
				  }
				  break;  
			  case '5':
				  if(reverseControl>0){
					  isNewReverseControl=true;
					  isReverseControl=true;
				  }
				  break; 
			  case '6':
				  if(linesAdd>0){
					  isLinesAdd=true;
				  }
				  break;
			  default:return;//用户按错键时结束方法
		   }
	}
}
