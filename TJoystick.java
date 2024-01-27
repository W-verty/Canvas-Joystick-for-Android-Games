package [you_package_here];

import android.graphics.*;

/*
 Canvas Joystick for Android Games

 Esta classe implementa um joystick personalizável para uso em aplicativos Android.
 O joystick é projetado para ser usado em conjunto com um objeto Canvas para controle de movimento e direção.

 O joystick pode ser facilmente integrado em jogos ou aplicativos que exigem entrada de joystick para controlar personagens,
 veículos ou outros elementos móveis na tela.

 Recursos principais:
 - Interface intuitiva de joystick móvel para controle de movimento.
 - Personalização de tamanho, cor e comportamento do joystick.
 - Funcionalidade de orientação para determinar a direção do joystick.
 - Fácil integração com um objeto Canvas para desenho e atualização na tela.
 
 Criado por: W. Verty
 Discord: w.verty
 Atualização recente: 27/01/2024.
 */

public class TJoystick {
	private int x;
	private int y;
	private int pointerID;
	private float centerX;
	private float centerY;
	private int centerSize;
	private boolean isMove = false;
	
	private Paint joystick;
	private Paint control;
	
	private float angleDegrees;
	private float angle;
	
	// JOYSTICK CONFIG
	private int JOYSTICK_SIZE;
	private final int JOYSTICK_ALPHA = 50;
	private final float CONTROL_BORDER = 20;
	private final int CONTROL_ALPHA = 40;
	private final int CONTRPL_STROKE_WIDTH = 5;
	private Boolean CONTROL_BOLL_CENTER = false;
	
	public TJoystick(int x, int y, int size){
		super();
		
		this.x = x;
		this.y = y;
		this.JOYSTICK_SIZE = size;
		this.centerX = this.x;
		this.centerY = this.y;
		this.centerSize = size/2;
		
		joystick = new Paint();
		joystick.setColor(Color.WHITE);
		joystick.setAlpha(JOYSTICK_ALPHA);
		
		control = new Paint();
		control.setColor(Color.WHITE);
		control.setAlpha(CONTROL_ALPHA);
		control.setStyle(Paint.Style.STROKE);
		control.setStrokeWidth(CONTRPL_STROKE_WIDTH);
	}

	public void onDraw(Canvas canvas){
		onUpdate();
		
		canvas.drawCircle(x, y, JOYSTICK_SIZE, joystick);
		canvas.drawCircle(x, y, JOYSTICK_SIZE, control);
		if(CONTROL_BOLL_CENTER) canvas.drawCircle(centerX, centerY, centerSize, joystick);
	}
	
	public void onUpdate() {
		if(!isMove){
			centerX = x;
			centerY = y;
			
			float angleR = (float) Math.atan2(centerY - y,  centerX - x);
			float angleD = (float) Math.toDegrees(angleR);
			this.angle = angleR;
			this.angleDegrees = angleD;
		}
	}
	
	public void setEnableCenter(){
		this.CONTROL_BOLL_CENTER = true;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getSize(){
		return JOYSTICK_SIZE;
	}
	
	public float getAngleDegrees(){
		return angleDegrees;
	}
	
	public float getAngle(){
		return this.angle;
	}
	
	public void click(int touchx, int touchy, int pointerID){
		if(isMove) return;
		
		boolean currentX = (touchx > (centerX - JOYSTICK_SIZE)) && (touchx < (centerX + JOYSTICK_SIZE));
		boolean currentY = (touchy > (centerY - JOYSTICK_SIZE)) && (touchy < (centerY + JOYSTICK_SIZE));
		
		if(currentX && currentY){
			isMove = true;
			this.pointerID = pointerID;
		}
	}
	
	public void move(float touchx, float touchy, int pointerID){
		if(isMove && this.pointerID == pointerID){
			float distance = (float) Math.sqrt(Math.pow(touchx - x, 2) + Math.pow(touchy - y, 2));
			
			if(distance < JOYSTICK_SIZE){
				centerX = touchx;
				centerY = touchy;
			}else{
				float angle = (float) Math.atan2(touchy - y, touchx - x);
				centerX = x + JOYSTICK_SIZE * (float) Math.cos(angle);
				centerY = y + JOYSTICK_SIZE * (float) Math.sin(angle);
			}
			
			float angleR = (float) Math.atan2(centerY - y,  centerX - x);
			float angleD = (float) Math.toDegrees(angleR);
			this.angleDegrees = angleD;
		}
	}
	
	public void clickup(int pointerID){
		if(this.pointerID == pointerID){
			isMove = false;
			centerX = x;
			centerY = y;
		}
	}
	
	public boolean isAction(){
		return this.isMove;
	}
	
	public int getOrientation(){
		int status = -1;
		
		if(isMove) {
			if (angleDegrees >= -(135 + CONTROL_BORDER) && angleDegrees <= -(45 - CONTROL_BORDER)) {
				// UP
				status = 0;
			} else if (angleDegrees >= (45 - CONTROL_BORDER) && angleDegrees <= (135 + CONTROL_BORDER)) {
				// DOWN
				status = 2;
			}

			if ((angleDegrees >= -(180 + CONTROL_BORDER) && angleDegrees < -(135 - CONTROL_BORDER)) || (angleDegrees >= (135 - CONTROL_BORDER) && angleDegrees <= (180 + CONTROL_BORDER))) {
				// LEFT
				status = 1;
			} else if((angleDegrees >= -(45 + CONTROL_BORDER) && angleDegrees < (45 + CONTROL_BORDER))) {
				// RIGHT
				status = 3;
			}
		}
		
		return status;
	}
}
