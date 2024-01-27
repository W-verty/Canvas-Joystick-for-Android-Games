# Canvas Joystick for Android Games
O Canvas Joystick for Android Games é uma Class Android que oferece um joystick personalizável para controle de movimento em aplicativos Android. Projetado para ser integrado facilmente em jogos e aplicativos, o joystick é renderizado em um objeto Canvas, permitindo um controle preciso e responsivo em dispositivos móveis.

# Recursos:
- Interface intuitiva de joystick móvel para controle de movimento.
- Personalização de tamanho, cor e comportamento do joystick.
- Funcionalidade de orientação para determinar a direção do joystick.
- Fácil integração com um objeto Canvas para desenho e atualização na tela.

# Como Usar:
```java
public void init() {
	player = new TPlayer(characterImage);
	joystick = new TJoystick(0, 0, 100);
	joystick.setEnableCenter();
}

@Override
protected void onDraw(Canvas canvas) {
	onUpdate();

	player.draw(canvas);
	// Desenha é ajusta posição do joystick
	joystick.setPosition(canvas.getWidth() + 10, canvas.getHeigth() - joystick.getSize() - 10);
	joystick.onDraw(canvas);
}

protected void onUpdate() {
	player.update();

	// Evento de movimento do jogador usando o joystick
	if(joystick.getOrientation() == 0){ player.setY(-1); } // UP
	if(joystick.getOrientation() == 1){ player.setX(-1); } // LEFT
	if(joystick.getOrientation() == 2){ player.setY(1); } // DOWN
	if(joystick.getOrientation() == 3){ player.setX(1); } // RIGHT
}
```

# Screenshot
<img src="https://github.com/W-verty/Canvas-Joystick-for-Android-Games/blob/images/Screenshot_20240127-002034_canvas.jpg" width=600>

# Licença:
Este projeto é licenciado sob a Licença MIT.

# 
- Autor: W. Verty
- Discord: w.verty
- Atualização recente: 27/01/2024.
