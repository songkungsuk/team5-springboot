<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Team 5 테트리스</title>
  <link rel="stylesheet" href="/resources/css/bg.css" />
</head>

<body>
  <h1>Team 5</h1>
  <h2>TETRIS</h2>
  <div id="game-container">
    <!-- 게임 요소를 감싸는 메인 컨테이너 -->
    <div class="container" id="canvas-container">
      <!-- Tetris 게임 캔버스를 포함하는 컨테이너 -->
      <canvas id="tetrisCanvas"></canvas>
      <!-- Tetris 게임 캔버스 요소 -->
      <div class="container" id="score-container">
        <!-- 설명문 -->
        <p>BGM정지는 B버튼 </p>
        <!-- 점수 및 캔버스를 포함하는 컨테이너 -->
        <canvas class="container" id="block-container"></canvas>
        <h2>Score</h2>
        <!-- "Score" 텍스트 헤더 (하얀색) -->
        <h2 id="score">0</h2>
        <!-- 점수를 표시하는 단락 요소 (초기값 0, 하얀색) -->
        <br />
        <br />
        <div class="direction-keys">
          <!-- 조작 방향키를 포함하는 컨테이너 -->
          <div></div>
          <div class="rotate-key" id="upKey">
            <span>↑</span>
            <!-- 시계방향 화살표로 보이는 회전 키 -->
          </div>
          <div></div>
          <div class="direction-key" id="leftKey">←</div>
          <!-- 왼쪽 방향키 -->
          <div class="direction-key" id="downKey">↓</div>
          <!-- 아래쪽 방향키 -->
          <div class="direction-key" id="rightKey">→</div>
          <!-- 오른쪽 방향키 -->
          <div></div>
        </div>

        <br>
        <div class="direction-keys">
          <!-- 조작 방향키를 포함하는 컨테이너 -->
          <div></div>
          <div></div>
          <div></div>
          <!-- Z키 -->
          <div class="direction-key" id="zKey">Z</div>
          <div></div>
          <!-- C키 -->
          <div class="direction-key" id="cKey">C</div>
          <div></div>
        </div>
        <div class="direction-key-space" id="spaceKey">SPACE BAR</div>
        <div id="startBtn" class="startBtn" onclick="main()">시작</div>
      </div>

    </div>

  </div>

  <div style="display: none;">
    <!-- bgm은 b버튼으로 재생 -->
    <audio src="/resources/bgm/ 99  Main Theme 1 hour.mp3" id="bgm"></audio>
    <audio src="/resources/soundEffect/clear_line.mp3" id="clear_line"></audio>
    <audio src="/resources/soundEffect/drop.mp3" id="drop"></audio>
    <audio src="/resources/soundEffect/game_over.mp3" id="game_over"></audio>
  </div>
  <script src="/resources/js/audio.js"></script>
  <script src="/resources/js/function.js"></script>
  <script src="/resources/js/block.js"></script>
  <script src="/resources/js/canvas.js"></script>
  <script src="/resources/js/main.js"></script>
</body>

</html>