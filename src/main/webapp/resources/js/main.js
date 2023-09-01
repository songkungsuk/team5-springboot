//--------------여기가 테트리스 실행부 main.js
let isUpKeyPressed = false;
document.addEventListener("keydown", handleKeyPress);
document.addEventListener("keyup", handleKeyUp);
// 위 방향키에서 손을 뗐을때 false로 바꿔서 정상 속도로 내려가게함
document.addEventListener("keyup", function (e) {
    isUpKeyPressed = false;
});
let randomBlock = chooseBlock(blockIndex[0]); //function.js에서 블럭랜덤으로 선택
function main() {
    player.play(bgm);
    const test = setInterval(function () {
        tetrisBoardArray(); //배열초기화
        initCanvas(); //배경덮어씀
        initNextBlockCanvas(); // 블럭미리보여주는곳 검은색으로 칠함
        showNextBlock(); // 다음블럭보여줌
        getBlockScore(); //블럭이 한줄이 채워져있는지 확인하기 채워졌으면 지움
        check_stacked(); ////제일 높이 쌓여있는 블럭의 높이를 저장하는 함수
        checkGameOverEvent(); // 맨위 블럭이 쌓이면 게임오버
        rotate_num = Math.abs(rotate) % 4; //블록회전시키는것 0~3값만나옵니다
        controll_x = 0; // 오른쪽으로 빠져나가지 않게하는 변수
        controll_y = 0; // 높이가 높은 블럭을 내렸을때 맨밑바닥인경우 조절해줌
        blockLocationArray_x = []; //블럭의 x좌표 값들 저장하는배열
        blockLocationArray_y = []; //블럭의 y좌표 값들 저장하는배열

        // 블럭을 그릴때마다 체크해야함 블럭이 겹치는지 안겹치는지 - 블럭한개가 닿앗을때
        for (let i = 0; i < randomBlock[0].length; i++) {
            let x = randomBlock[rotate_num][i][0] + move_x; // x 좌표
            let y = randomBlock[rotate_num][i][1] + move_y; // y 좌표
            drawBlock(x, y); //블럭에 있는 좌표들 칠함
            blockLocationArray_x.push(x - move_x); //블럭의오른쪽 최대길이
            blockLocationArray_y.push(y - move_y); //블럭의아래쪽 최대길이
            //이부분때문에 함수로 뺄수가없엇어요
            if (stackedBoard[x][y] == tetrisBoard[x][y]) {  //이게 제일중요 그리는거하고 쌓인거하고 겹쳤을때 로직
                for (let i = 0; i < randomBlock[0].length; i++) {  //그자리에있는 블럭의 좌표 뽑아냄
                    let x = randomBlock[rotate_num][i][0] + move_x; // x 좌표
                    let y = randomBlock[rotate_num][i][1] + move_y; // y 좌표
                    //바로위에다가 그려야하니까 y-1 // y가 증가할수록 내려감 // 감소할수록 올라감
                    if(!(stackedBoard[x][y - 1])){
                        stackedBoard[x][y - 1] = true; // 회색블럭으로 그리는것 
                    }
                    

                }
                //이건 블럭높이 초기화해주고 랜덤블럭 배열에 밀어넣어주는역할함
                getRandAndInitMoveY();
            }
        }

        //x와 y의 최대값 뽑는 것 Min, Max 구하는거
        for (let num = 0; num < blockLocationArray_x.length; num++) {
            if (controll_x < blockLocationArray_x[num]) { //블록의 오른쪽 최대좌표
                controll_x = blockLocationArray_x[num];
            }
            if (controll_y < blockLocationArray_y[num]) { // 블록의 아래 최대 좌표
                controll_y = blockLocationArray_y[num];
            }
        }
        // 블럭이 회전햇을때 오른쪽으로 빠져나왔으면 블럭을 왼쪽으로 밀어냄
        if (move_x > widthBlockCount - (controll_x + 1)) {
            move_x -= controll_x - 1;
        }

        //-----------------------------------------------------
        drawBlocks(); //이게 쌓인블록과 내가 조종할 블록을 그려주는 함수 funtion.js에 빼놨음 //함수는 싹다 function에 넣어놨어요
        //-----------------------------------------------------

        // 맨밑으로 갔을때
        if (move_y == heightBlockCount - (controll_y + 1)) {//controll_y는 배열의 좌표라 +1해줫음
            //블럭의 높낮이에 따라서 블럭을올려주거나 낮춰줌 controll_y 값이 달라짐
            for (let i = 0; i < randomBlock[0].length; i++) {
                let x = randomBlock[rotate_num][i][0] + move_x; // x 좌표
                let y = randomBlock[rotate_num][i][1] + move_y; // y 좌표
                stackedBoard[x][y] = true;
            }
            //이건 블럭높이 초기화해주고 랜덤블럭 배열에 밀어넣어주는역할함
            getRandAndInitMoveY();
        }
        move_y++; //블럭내림
        tetrisBoard.length = 0; //배열값삭제
    }, 100); //100 이숫자를 바꾸면 속도가변함

    //맨위의 블럭이 채워졌을경우 게임오버
    function checkGameOverEvent() { 
        for (let x = 0; x < widthBlockCount; x++) {
            if (stackedBoard[x][0]) {
                player._stop(bgm);//전부 초기화 시킴
                player.play(gameOver);//전부 초기화 시킴
                clearInterval(test);//전부 초기화 시킴
                stackedBoard.length = 0;//전부 초기화 시킴
                stackedBoardArray();//전부 초기화 시킴
                stacked_height = 0;//전부 초기화 시킴
                tetrisBoard.length = 0; //배열값삭제
                tetrisBoardArray(); //배열초기화
                initCanvas(); //배경덮어씀
                initNextBlockCanvas(); // 블럭미리보여주는곳 검은색으로 칠함
            }
        }
    }
}